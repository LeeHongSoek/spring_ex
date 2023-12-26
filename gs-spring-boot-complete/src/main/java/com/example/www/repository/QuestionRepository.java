package com.example.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.www.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>
{

}