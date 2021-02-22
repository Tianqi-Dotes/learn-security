package com.imooc.uaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.domain
 * @date 2021-02-19 17:48
 * @Copyright © 2018-2019 *******
 */
@Data
@Entity
@Table(name="mooc_users")
public class User implements UserDetails,Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @JsonIgnore
    @Column(name = "password_hash",nullable = false)
    private String password;
    private String email;
    private String mobile;
    private String name;
    private boolean enabled;
    @Column(name = "account_non_expired",nullable = false)
    private boolean accountNonExpired;
    @Column(name = "account_non_locked",nullable = false)
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired",nullable = false)
    private boolean credentialsNonExpired;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "mooc_users_roles",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> authorities;
}
