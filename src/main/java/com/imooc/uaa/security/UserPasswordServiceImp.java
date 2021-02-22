package com.imooc.uaa.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.security
 * @date 2021-02-22 17:49
 * @Copyright © 2018-2019 *******
 */
@Service
public class UserPasswordServiceImp implements UserDetailsPasswordService {
    //无缝密码升级
    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {
        //s为新的密码规则的string  直接更改数据库内容即可
        return null;
    }
}
