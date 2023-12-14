package com.example.com.vo;

// 추가된 BeanInfo 클래스
public class BeanInfo
{
    private String context;
    private String beanName;
    private String beanClass;

    public BeanInfo(String context, String beanName, String beanClass)
    {
        this.context = context;
        this.beanName = beanName;
        this.beanClass = beanClass;
    }

    public String getContext()
    {
        return context;
    }

    public String getBeanName()
    {
        return beanName;
    }

    public String getBeanClass()
    {
        return beanClass;
    }
}