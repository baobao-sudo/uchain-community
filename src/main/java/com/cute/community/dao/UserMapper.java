package com.cute.community.dao;

import com.cute.community.entity.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String stuId);

    int insert(User record);

    User selectByPrimaryKey(String stuId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}