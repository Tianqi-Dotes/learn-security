package com.imooc.uaa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.uaa.filter.RestAuthFilter;
import com.imooc.uaa.security.UserDetailsServiceImp;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.config
 * @date 2021-02-04 11:16
 * @Copyright © 2018-2019 *******
 */
@EnableWebSecurity
@Slf4j
@Configuration
@Order(99)
@Import(SecurityProblemSupport.class)//引入
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    ObjectMapper objectMapper;
    @Resource
    SecurityProblemSupport securityProblemSupport;
    @Resource
    DataSource dataSource;
    @Resource
    UserDetailsServiceImp userDetailsServiceImp;

    @Override
    //controller 鉴权不需要csrf
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers(req->req.mvcMatchers("/au/**","/admin/**","/api/**"))//rest鉴权只走这些url
            .exceptionHandling(ex->ex
                .authenticationEntryPoint(securityProblemSupport)
                .accessDeniedHandler(securityProblemSupport))//安全问题交给SecurityProblemSupport处理
            .authorizeRequests(req->
                req.antMatchers("/au/**").permitAll()
                    .anyRequest().authenticated())
            .addFilterAt(restAuthFilter(), UsernamePasswordAuthenticationFilter.class)
            //.formLogin(form->form.loginPage("/login").permitAll())//permitAll()排除/login不会多次重定向
            .httpBasic(Customizer.withDefaults())//通过认证头 认证用户
            .csrf(csrf->csrf.disable())
            .sessionManagement(sesiion->sesiion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            //.logout(logout->logout.logoutUrl("/perform_logout"));



    }

    private RestAuthFilter restAuthFilter() throws Exception {
        RestAuthFilter filter=new RestAuthFilter(objectMapper);
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        filter.setAuthenticationManager(authenticationManager());//父类 userservice
        filter.setFilterProcessesUrl("/au/login");
        return filter;
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler(){
        return (req, res, auth) -> {
            res.getWriter().println("success!");
        };
    }
    private AuthenticationFailureHandler authenticationFailureHandler(){
        return (req, res, ex) -> {
            res.setStatus(HttpStatus.UNAUTHORIZED.value());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setCharacterEncoding("UTF-8");
            val json=new ObjectMapper();
            val msg= Map.of(
                "msg","auth failed!",
                "reason",ex.getMessage()
            );
            res.getWriter().println(json.writeValueAsString(msg));
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp)
            .passwordEncoder(passwordEncoder());

    /*.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username,password,enabled from mooc_users where username=?")
            .passwordEncoder(passwordEncoder());*/
        //.withDefaultSchema()
            /*.withUser("user")
            .password("{bcrypt}$2a$10$t/z9qsfV906MWmcI93XFQuhvnc9aTHgw1t52QmQ.prlL8EKVQSG7u")
            .roles("USER","ADMIN")
        .and()
        .withUser("tq")
        .password("{SHA-1}{RP9fpmw4xQjOLVVW4z7YYKxkXgqAiE9TQc93IZusp1w=}12c76e6318fd7d8d5876e2f168e2e1c81a9a4ac2")
        .roles("USER","ADMIN");*/
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        val defaultEncode="SHA-1";
        val encodes=Map.of(
            "bcrypt",new BCryptPasswordEncoder(),
            "SHA-1",new MessageDigestPasswordEncoder("SHA-1")
        );

        return new DelegatingPasswordEncoder(defaultEncode,encodes);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {//过滤不进入
        web.ignoring().antMatchers("/static/**","/error","/h2-console/**");
    }
}
