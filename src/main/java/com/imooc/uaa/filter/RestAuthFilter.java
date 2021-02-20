package com.imooc.uaa.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.filter
 * @date 2021-02-04 15:54
 * @Copyright © 2018-2019 *******
 */
@RequiredArgsConstructor
public class RestAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest ;

        try {
            InputStream is=request.getInputStream();
            JsonNode node=objectMapper.readTree(is);
            String username=node.get("username").textValue();
            String password=node.get("password").textValue();
            authRequest = new UsernamePasswordAuthenticationToken(username, password);

        } catch (IOException e) {
            e.printStackTrace();
            throw new BadCredentialsException("json auth failed");
        }
        setDetails(request,authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
