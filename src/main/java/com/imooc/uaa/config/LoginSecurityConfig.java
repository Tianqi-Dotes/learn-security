package com.imooc.uaa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.config
 * @date 2021-02-20 11:38
 * @Copyright © 2018-2019 *******
 */
@Slf4j
@Configuration
@Order(100)
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    //cookie session鉴权
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests(auth->auth.anyRequest().authenticated())//授权请求控制
            .formLogin(form->form.loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll())//permitAll()排除/login不会多次重定向
                .logout(logout->logout.logoutUrl("/perform_logout"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {//过滤不进入
        web.ignoring().mvcMatchers("/static/**","/error")
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations());//过滤静态文件
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user")
            .password("{bcrypt}$2a$10$t/z9qsfV906MWmcI93XFQuhvnc9aTHgw1t52QmQ.prlL8EKVQSG7u")
            .roles("USER","ADMIN")
            .and()
            .withUser("tq")
            .password("{SHA-1}{RP9fpmw4xQjOLVVW4z7YYKxkXgqAiE9TQc93IZusp1w=}12c76e6318fd7d8d5876e2f168e2e1c81a9a4ac2")
            .roles("USER","ADMIN");
    }
}
