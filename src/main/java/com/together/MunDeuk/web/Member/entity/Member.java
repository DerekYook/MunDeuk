package com.together.MunDeuk.web.Member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity//(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Column(length = 20, nullable = false)
    private String nickName;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String password;

    private String accTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private MemberAuth memberAuth;

    public enum MemberAuth{
        Admin,
        User;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus;

    public enum MemberStatus{
        Active,
        Inactive;
    }

    @Enumerated(EnumType.STRING)
    @Column
    private SocialType socialType;

    public enum SocialType{
        KAKAO,
        GOOGLE,
        NAVER,
        FACEBOOK,
        INSTAGRAM
    }

    @Column
    private String socialId;
}
