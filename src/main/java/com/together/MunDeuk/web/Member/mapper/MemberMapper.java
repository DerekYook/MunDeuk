package com.together.MunDeuk.web.Member.mapper;

import com.together.MunDeuk.web.Member.dto.MemberDto;
import com.together.MunDeuk.web.Member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    List<MemberDto.Response> membersToMemberResponseDtos(List<Member> members);

    MemberDto.Response memberToMemberResponseDto(Member member);
}
