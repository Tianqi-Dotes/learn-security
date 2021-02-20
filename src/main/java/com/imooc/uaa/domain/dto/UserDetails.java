package com.imooc.uaa.domain.dto;

import com.imooc.uaa.annotation.PasswordAnno;
import com.imooc.uaa.annotation.PasswordMatch;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.domain.dto
 * @date 2021-02-19 17:49
 * @Copyright © 2018-2019 *******
 */
@Getter
@Setter
@PasswordMatch
public class UserDetails {
    @NotNull
    @NotBlank
    @Size(min=4,max=50,message = "用户名不合规4-50")
    private String username;
    @NotNull
    @PasswordAnno
    private String password;
    @NotNull
    private String password2;//验证密码
    @Email
    @NotNull
    private String email;
    @NotNull
    @NotBlank
    @Size(min=4,max=20,message = "用户名不合规4-20")
    private String name;

}
