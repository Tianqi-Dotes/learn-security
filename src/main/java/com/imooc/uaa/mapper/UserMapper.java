package com.imooc.uaa.mapper;

import com.imooc.uaa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tq
 * @version V1.0
 * @Package com.imooc.uaa.mapper
 * @date 2021-02-22 17:35
 * @Copyright © 2018-2019 *******
 */
@Repository
public interface UserMapper extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
