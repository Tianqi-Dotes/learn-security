package com.imooc.uaa.security;

import com.imooc.uaa.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.security
 * @date 2021-02-22 17:34
 * @Copyright © 2018-2019 *******
 */
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMapper.findByUsername(s);
    }
}
