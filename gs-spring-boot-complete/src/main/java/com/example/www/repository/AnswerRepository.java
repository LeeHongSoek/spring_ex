package com.example.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.www.entity.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer>
{

}