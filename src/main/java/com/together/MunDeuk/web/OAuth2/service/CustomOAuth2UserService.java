package com.together.MunDeuk.web.OAuth2.service;

import static com.together.MunDeuk.web.OAuth2.entity.OAuth2UserInfo.socialId;

import com.together.MunDeuk.utils.OAuth2PrincipalDetail;
import com.together.MunDeuk.utils.OAuth2Utils;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import com.together.MunDeuk.web.OAuth2.entity.OAuth2UserInfo;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();

    log.info("OAuth2 User = {}", oAuth2User);
    log.info("attributes = {}", attributes);

    // 제공받은 정보 확인
    String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
        .getUserInfoEndpoint().getUserNameAttributeName();
    System.out.println(userNameAttributeName);
    // 정보 제공 소셜 확인
    String registrationId = userRequest.getClientRegistration().getRegistrationId();
//    SocialType socialType = OAuth2Utils.getSocialType(registrationId);
    System.out.println(registrationId);
    // 카카오
    Member member = null;
    if (registrationId.equals("")) {
      OAuth2UserInfo kakaoUserInfo = new OAuth2UserInfo(attributes);
      System.out.println(kakaoUserInfo);
      String socialType = "KAKAO";
      String socialId = kakaoUserInfo.getSocialId();
      String name = kakaoUserInfo.getName();

      Optional<Member> bySocialId = memberRepository.findBySocialId(socialId);
      // 일치 하는 회원이 없다면 새로 등록
      member = bySocialId.orElseGet(() -> saveSocialMember(socialId, name, socialType));

//    } else if(registrationId.eqauls("")){
//      // 구글
    }
    return new OAuth2PrincipalDetail(member,
        Collections.singleton(new SimpleGrantedAuthority(member.getMemberAuth().name())), attributes);
//    return null;
  }

  public Member saveSocialMember(String socialId, String name, String socialType) {
    long memberId = memberRepository.selectMaxMemberIdx();
    return memberRepository.saveSocialMemeber(memberId, socialId, name, socialType);
//    return null;
  }

}

