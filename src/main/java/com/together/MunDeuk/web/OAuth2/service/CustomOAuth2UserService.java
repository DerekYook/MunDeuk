package com.together.MunDeuk.web.OAuth2.service;

import com.together.MunDeuk.web.OAuth2.domain.OAuth2PrincipalDetail;
import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.repository.MemberRepository;
import com.together.MunDeuk.web.OAuth2.entity.OAuth2UserInfo;
import java.util.Collections;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

//  private final MemberRepository memberRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info("--------------------------- OAuth2UserService ---------------------------");

    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();

    log.info("OAuth2 User = {}", oAuth2User);
    log.info("attributes = {}", attributes);

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
    Member member = null;
    if (registrationId.equals("kakao")) {
      OAuth2UserInfo kakaoUserInfo = new OAuth2UserInfo(attributes);
      String socialType = registrationId.toUpperCase();
      String socialId = kakaoUserInfo.getSocialId();
      String name = kakaoUserInfo.getName();
      String email = kakaoUserInfo.getEmail();

//      Optional<Member> bySocialId = memberRepository.findBySocialId(socialId);
//      // 일치 하는 회원이 없다면 새로 등록
//      member = bySocialId.orElseGet(() -> saveSocialMember(name, email, socialId, socialType));
      System.out.println(member);
//    } else if(registrationId.eqauls("")){
//      // 구글
    }
//    return new OAuth2PrincipalDetail(member,
//        Collections.singleton(new SimpleGrantedAuthority(member.getMemberAuth().name())),
//        attributes);
    return oAuth2User;
  }

//  public Member saveSocialMember(String name, String email, String socialId, String socialType) {
//    long memberId = memberRepository.selectMaxMemberIdx();
//    memberRepository.saveSocialMember(memberId, name, email, socialId, socialType);
//    return memberRepository.selectMember(email);
//  }

}

