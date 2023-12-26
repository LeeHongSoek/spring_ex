package com.example.www.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    public void setSubject(String string)
    {
        this.subject = string;        
    }

    public void setContent(String string)
    {
        this.content = string;        
    }

    public void setCreateDate(LocalDateTime now)
    {
        this.createDate = now;
        
    }
}