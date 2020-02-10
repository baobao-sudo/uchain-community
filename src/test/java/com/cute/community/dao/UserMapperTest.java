package com.cute.community.dao;

import com.cute.community.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insert() {
        User user = new User();
        user.setStuId("201831063220");
        user.setUserName("暴暴");
        user.setPassword("123456");
        user.setGroupId(1);
        user.setRole(1);
        userMapper.insert(user);
    }
}