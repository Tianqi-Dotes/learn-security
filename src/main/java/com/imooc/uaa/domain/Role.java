package com.imooc.uaa.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.domain
 * @date 2021-02-22 15:27
 * @Copyright © 2018-2019 *******
 */
@Data
@Entity
@Table(name = "mooc_roles")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name",unique = true,nullable = false)
    private String authority;

}
