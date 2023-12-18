package com.example.www.controller;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.www.vo.Member;

public interface MemberRepository extends JpaRepository<Member, Long>
{
    // 추가적인 쿼리 메서드 등록 가능
}
