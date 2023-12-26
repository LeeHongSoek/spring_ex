package com.example.www.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
        // TODO Auto-generated method stub
        
    }

    public void setContent(String string)
    {
        // TODO Auto-generated method stub
        
    }

    public void setCreateDate(LocalDateTime now)
    {
        // TODO Auto-generated method stub
        
    }
}