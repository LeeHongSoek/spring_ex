package com.example.www.service;

import java.util.List;

import com.example.www.entity.Member;

public interface MemberService
{
    List<Member> getAllMembers();

    Member getMemberById(Long id);

    Member createMember(Member member);

    Member updateMember(Long id, Member member);

    void deleteMember(Long id);
}
