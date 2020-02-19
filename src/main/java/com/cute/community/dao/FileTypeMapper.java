package com.cute.community.dao;

import com.cute.community.entity.FileType;

import java.util.List;

public interface FileTypeMapper {
    int deleteByPrimaryKey(Integer fileTypeId);

    int insert(FileType record);

    FileType selectByPrimaryKey(Integer fileTypeId);

    List<FileType> selectAll();

    int updateByPrimaryKey(FileType record);
}