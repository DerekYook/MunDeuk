package com.together.MunDeuk.web.Member.repository;

import com.together.MunDeuk.web.Member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value="SELECT * FROM member WHERE MEMBER_STATUS = 'Active'", nativeQuery = true)
    List<Member> selectMembers();

    @Query(value="SELECT * FROM member WHERE EMAIL = :email AND MEMBER_STATUS = 'Active'", nativeQuery = true)
    Member selectMember(String email);

    @Query(value = "SELECT * FROM member WHERE SOCIAL_ID = :socialId AND SOCIAL_TYPE = :socialType AND MEMBER_STATUS = 'Active'", nativeQuery = true)
    Optional<Member> verifiedMember(String socialId, String socialType);

    @Query(value = "SELECT MAX(MEMBER_ID) + 1 FROM member", nativeQuery = true)
    long selectMaxMemberIdx();

    @Modifying
    @Query(value = "INSERT INTO member (MEMBER_ID, NICK_NAME, EMAIL, PASSWORD, MEMBER_AUTH, MEMBER_STATUS) VALUES (:memberId, :nickName, :email, :password, 'User', 'Active')", nativeQuery = true)
    void registerMember(Long memberId, String nickName, String email, String password);

    @Query(value = "SELECT * FROM member WHERE SOCIAL_ID = :socialId AND SOCIAL_TYPE = :socialType AND MEMBER_STATUS = 'Active'", nativeQuery = true)
    Member findBySocial(String socialId, String socialType);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO member (MEMBER_ID, NICK_NAME, EMAIL, PASSWORD, MEMBER_AUTH, MEMBER_STATUS, SOCIAL_TYPE, SOCIAL_ID)"
        + "VALUES (:memberId, :nickName, :email, :nickName, 'User', 'Active', :socialType, :socialId)", nativeQuery = true)
    void saveSocialMember(Long memberId, String nickName, String email, String socialId, String socialType);

}
