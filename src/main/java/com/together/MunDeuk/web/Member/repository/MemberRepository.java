package com.together.MunDeuk.web.Member.repository;

import com.together.MunDeuk.web.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value="SELECT * FROM member WHERE MEMBER_STATUS = 'Active'", nativeQuery = true)
    List<Member> selectMembers();

    @Query(value = "SELECT * FROM member WHERE EMAIL = :email AND MEMBER_STATUS = 'Active'", nativeQuery = true)
    Member verifiedMember(String email);
}
