package com.cute.community.dao;

import com.cute.community.entity.User;
import java.util.List;
/**
 *@ClassName UserMapper
 *@Description 用户Mapper
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

public interface UserMapper {
    /**
     * 通过学号删除
     * @param stuId
     * @return
     */
    int deleteByPrimaryKey(String stuId);

    /**
     * 插入用户
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 通过学号删除
     * @param stuId
     * @return
     */
    User selectByPrimaryKey(String stuId);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAll();

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
}