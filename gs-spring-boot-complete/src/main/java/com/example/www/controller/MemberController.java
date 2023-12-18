package com.example.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.www.vo.Member;
import com.example.www.vo.MemberRepository;

@RestController
public class MemberController
{

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> getMembers()
    {
        return memberRepository.findAll();
    }

    @PostMapping("/members")
    public Member createMember(@RequestBody Member member)
    {
        return memberRepository.save(member);
    }

    @PutMapping("/members/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member)
    {
        member.setId(id);
        return memberRepository.save(member);
    }

    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable Long id)
    {
        memberRepository.deleteById(id);
    }
}
