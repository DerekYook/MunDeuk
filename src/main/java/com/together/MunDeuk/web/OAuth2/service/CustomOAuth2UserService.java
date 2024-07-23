package com.together.MunDeuk.web.OAuth2.service;

import com.together.MunDeuk.exception.CustomOAuth2Exception;
import com.together.MunDeuk.utils.JwtTokenizer2;
import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import com.together.MunDeuk.web.OAuth2.entity.OAuth2UserInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  @Value("${jwt.access-exp-time}")
  public int ACCESS_EXP_TIME;

  private final MemberRepository memberRepository;

  private final JwtTokenizer2 jwtTokenizer2;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("--------------------------- OAuth2UserService ---------------------------");

    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();

    log.info("OAuth2 User = {}", oAuth2User);
    log.info("attributes = {}", attributes);

    try{
      return oAuth2VerifyProcess(userRequest, oAuth2User);
    } catch (CustomOAuth2Exception e) {
//      throw new OAuth2AuthenticationException(new CustomOAuth2Exception(e.getMessage(), e));
      OAuth2Error oAuth2Error = new OAuth2Error("customError", e.getMessage(), null);
      throw new OAuth2AuthenticationException(oAuth2Error, e.getMessage(), e);
    }

  }

  public OAuth2User oAuth2VerifyProcess(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {

    Map<String, Object> attributes = oAuth2User.getAttributes();

    Member member = null;

    // 제공받은 정보 확인
    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails()
        .getUserInfoEndpoint()
        .getUserNameAttributeName();
    log.info("nameAttributeKey = {}", userNameAttributeName);
    // 정보 제공 소셜 확인
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    log.info("social Check = {}", registrationId);
    // 카카오

    if (registrationId.equals("kakao")) {
      Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
      String nickname = (String) properties.get("nickname");

      Map<String, Object> accountInfo = (Map<String, Object>) attributes.get("kakao_account");
      String userEmail = (String) accountInfo.get("email");

      OAuth2UserInfo kakaoUserInfo = new OAuth2UserInfo(attributes);

      log.info("kakao Login Info = {}", kakaoUserInfo);

      String socialType = registrationId.toUpperCase();
      String socialId = Long.toString(oAuth2User.getAttribute("id"));
      String name = nickname;
      String email = userEmail;

      // todo : 로그인 회원가입 분리
      Optional<Member> verifiedMemberBySocial = memberRepository.verifiedMember(socialId, socialType);
      if (verifiedMemberBySocial.isPresent()) {
        try {
          log.info("!!!! Login !!!!");
          member = memberRepository.findBySocial(socialId, socialType);
        } catch (Exception e) {
          log.info("!!!! Error during Login !!!!");
          log.error("Error finding user by social ID: " + socialId + ", " + socialType, e);
          throw e; // 또는 적절한 예외 처리
        }
      } else {
        log.debug("!!!! Need Registration !!!!");
        // 일치 하는 회원이 없다면 새로 등록
        Map<String, Object> memberMap = new HashMap<>();

        memberMap.put("socialType", socialType);
        memberMap.put("socialId", socialId);
        memberMap.put("name", name);
        memberMap.put("email", email);

//        // static 분리
//        int accessTokenLiveTime = JwtTokenizer2.ACCESS_EXP_TIME;
        int accessTokenLiveTime = ACCESS_EXP_TIME;

        String tempToken = jwtTokenizer2.generateToken(memberMap, accessTokenLiveTime);
        throw new CustomOAuth2Exception("NOT_FOUND", tempToken);
      }

//      member = verifiedMemberBySocial.orElseGet(() -> saveSocialMember(name, email, socialId, socialType));

//    } else if(registrationId.eqauls("")){
//      // 구글
    }

    return new OAuth2PrincipalDetail(member,
        Collections.singleton(new SimpleGrantedAuthority(member.getMemberAuth().name())),
        attributes);
  }

//  public Member saveSocialMember(String name, String email, String socialId, String socialType) {
//    long memberId = memberRepository.selectMaxMemberIdx();
//    memberRepository.saveSocialMember(memberId, name, email, socialId, socialType);
//    return memberRepository.selectMember(email);
//  }

}

