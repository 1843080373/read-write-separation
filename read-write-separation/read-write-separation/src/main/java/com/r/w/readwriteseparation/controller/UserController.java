package com.r.w.readwriteseparation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.r.w.readwriteseparation.entity.User;
import com.r.w.readwriteseparation.service.UserService;


/**
 * @author Administrator
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user){
        return userService.writeUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/findAllUser", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(){
        return userService.readUser();
    }
}