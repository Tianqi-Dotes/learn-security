package com.imooc.uaa.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.domain
 * @date 2021-02-19 17:48
 * @Copyright © 2018-2019 *******
 */
@Getter
@Setter
public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String name;

}
