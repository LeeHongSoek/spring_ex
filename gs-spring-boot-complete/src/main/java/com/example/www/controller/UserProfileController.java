package com.example.www.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.www.ClassUtils;
import com.example.www.mapper.UserProfileMapper;
import com.example.www.vo.UserProfile;
/*
CREATE TABLE `UserProfile` (
  `id` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
 * */

@RestController
public class UserProfileController
{

    private final Logger logger = LogManager.getLogger(getClass().getName());
    private UserProfileMapper userProfileMapper;
    
    public UserProfileController(UserProfileMapper userProfileMapper)
    {
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id)
    {
    	logger.info("클래스:함수명 | ▷ {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
    	logger.info("--------------------------------------------------------------------------------------");
    	
        return userProfileMapper.getUserProfile(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList()
    {
    	logger.info("클래스:함수명 | ▷ {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
    	logger.info("--------------------------------------------------------------------------------------");
    	
        return userProfileMapper.getUserProfileList();
    }

    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address)
    {
    	logger.info("클래스:함수명 | ▷ {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
    	logger.info("--------------------------------------------------------------------------------------");
    	
        userProfileMapper.insertUserProfile(id, name, phone, address);
    }

    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address)
    {
    	logger.info("클래스:함수명 | ▷ {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
    	logger.info("--------------------------------------------------------------------------------------");
    	
        userProfileMapper.updateUserProfile(id, name, phone, address);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id)
    {
    	logger.info("클래스:함수명 | ▷ {} : {} ", ClassUtils.getShtClassNm(getClass()), Thread.currentThread().getStackTrace()[1].getMethodName());
    	logger.info("--------------------------------------------------------------------------------------");
    	
        userProfileMapper.deleteUserProfile(id);
    }

}