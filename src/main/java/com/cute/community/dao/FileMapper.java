package com.cute.community.dao;

import com.cute.community.entity.File;
import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(File record);

    File selectByPrimaryKey(Integer fileId);

    List<File> selectAll();

    int updateByPrimaryKey(File record);
}