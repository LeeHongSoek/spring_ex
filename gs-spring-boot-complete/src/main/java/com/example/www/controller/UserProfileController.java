package com.example.www.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/mysql")
public class UserProfileController
{

    private UserProfileMapper userProfileMapper;
    
    public UserProfileController(UserProfileMapper userProfileMapper)
    {
        this.userProfileMapper = userProfileMapper;
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id)
    {
        //return userProfileMapper.getUserProfile(id);
        return userProfileMapper.getUserProfileById(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList()
    {
        return userProfileMapper.getUserProfileList();
    }

    @PutMapping("/user")
    public void putUserProfile(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address)
    {
        userProfileMapper.insertUserProfile(id, name, phone, address);
    }

    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address)
    {
        userProfileMapper.updateUserProfile(id, name, phone, address);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id)
    {
        userProfileMapper.deleteUserProfile(id);
    }

}