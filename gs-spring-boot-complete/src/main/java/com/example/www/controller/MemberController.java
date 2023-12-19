package com.example.www.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.www.ClassUtils;
import com.example.www.service.MemberService;
import com.example.www.vo.Member;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/members")
public class MemberController
{
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private final MemberService memberService;

    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers(HttpServletRequest request )
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        return memberService.getMemberById(id);
    }

    @PostMapping
    public Member createMember(@RequestBody Member member)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        return memberService.createMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member member)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id)
    {
        logger.info("◇ 클래스:함수명 | {}:{} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());

        memberService.deleteMember(id);
    }
}
