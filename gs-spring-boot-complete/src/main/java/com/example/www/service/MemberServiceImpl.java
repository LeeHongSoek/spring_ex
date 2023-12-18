package com.example.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.www.controller.MemberRepository;
import com.example.www.vo.Member;

@Service
public class MemberServiceImpl implements MemberService
{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers()
    {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id)
    {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public Member createMember(Member member)
    {
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long id, Member member)
    {
        if (memberRepository.existsById(id))
        {
            member.setId(id);
            return memberRepository.save(member);
        }
        return null;
    }

    @Override
    public void deleteMember(Long id)
    {
        memberRepository.deleteById(id);
    }
}
