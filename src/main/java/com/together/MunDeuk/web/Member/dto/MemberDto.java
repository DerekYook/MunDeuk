package com.together.MunDeuk.web.Member.dto;

import com.together.MunDeuk.web.Member.entity.Member;
import com.together.MunDeuk.web.Member.entity.Member.MemberAuth;
import com.together.MunDeuk.web.Member.entity.Member.MemberStatus;
import lombok.Getter;

public class MemberDto {

  //    @Getter
//    public static class Response{
//        private long memberId;
//        private String nickName;
//        private String email;
//        private String password;
//        private String accTime;
//        private MemberStatus memberStatus;
//
//        public Response(long memberId, String nickName, String email, String password, String accTime, MemberStatus memberStatus) {
//            this.memberId = memberId;
//            this.nickName = nickName;
//            this.email = email;
//            this.password = password;
//            this.accTime = accTime;
//            this.memberStatus = memberStatus;
//        }
//    }
  @Getter
  public static class Response {

    private String nickName;
    private String email;
    private String accTime;
    private MemberAuth memberAuth;
    private MemberStatus memberStatus;

    public Response(String nickName, String email, String accTime, MemberAuth memberAuth,
        MemberStatus memberStatus) {
      this.nickName = nickName;
      this.email = email;
      this.accTime = accTime;
      this.memberAuth = memberAuth;
      this.memberStatus = memberStatus;
    }
  }
/*

  @Getter
  public static class transform {

    private String nickName;
    private String email;
    private MemberAuth memberAuth;
    private MemberStatus memberStatus;

    public transform(Member member) {
      this.nickName = member.getNickName();
      this.email = member.getEmail();
      this.memberAuth = member.getMemberAuth();
      this.memberStatus = member.getMemberStatus();
    }
  }

  @Getter
  public static class Response {

    private String nickName;
    private String email;
    private String accTime;
    private MemberAuth memberAuth;
    private MemberStatus memberStatus;

    public Response(String nickName, String email, String accTime, MemberAuth memberAuth,
        MemberStatus memberStatus) {
      this.nickName = nickName;
      this.email = email;
      this.accTime = accTime;
      this.memberAuth = memberAuth;
      this.memberStatus = memberStatus;
    }
  }
*/


}
