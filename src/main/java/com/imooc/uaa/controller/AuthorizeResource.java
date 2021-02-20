package com.imooc.uaa.controller;

import com.imooc.uaa.domain.dto.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.controller
 * @date 2021-02-19 17:52
 * @Copyright © 2018-2019 *******
 */
@RestController
@RequestMapping("/au")
public class AuthorizeResource {

    @PostMapping("/registry")
    public String makeGreetings(@Valid @RequestBody UserDetails user){

        return user.toString();
    }


}
