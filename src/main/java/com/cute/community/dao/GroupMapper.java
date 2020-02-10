package com.cute.community.dao;

import com.cute.community.entity.Group;
import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(Group record);

    Group selectByPrimaryKey(Integer groupId);

    List<Group> selectAll();

    int updateByPrimaryKey(Group record);
}