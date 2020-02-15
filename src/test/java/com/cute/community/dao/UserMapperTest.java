package com.cute.community.dao;

import com.cute.community.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void insert() {
        User user = new User();
        user.setStuId("201831063220");
        user.setUserName("暴暴");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRole(1);
        user.setGroupId(1);
        userMapper.insert(user);
    }
}