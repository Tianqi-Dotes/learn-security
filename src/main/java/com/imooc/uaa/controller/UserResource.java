package com.imooc.uaa.controller;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.controller
 * @date 2021-02-04 11:03
 * @Copyright © 2018-2019 *******
 */
@RestController
@RequestMapping("api")
public class UserResource {


    @GetMapping("/greetings")
    public String greetings(){
        return "hello world";
    }

    @PostMapping("/greetings")
    public String makeGreetings(@RequestParam("name")String name){
        return "hello world"+name;
    }

    @GetMapping("/principle")
    public Authentication getPrinciple(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
