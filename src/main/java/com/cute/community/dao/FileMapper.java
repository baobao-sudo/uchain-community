package com.cute.community.dao;

import com.cute.community.entity.File;

import java.util.List;

/**
 * @ClassName FileMapper
 * @Description 文件
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

public interface FileMapper {
    /**
     * 删除
     *
     * @param fileId
     * @return
     */
    int deleteByPrimaryKey(Integer fileId);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(File record);

    /**
     * 通过学号删除
     *
     * @param fileId
     * @return
     */
    File selectByPrimaryKey(Integer fileId);

    /**
     * 查询所有
     *
     * @return
     */
    List<File> selectAll();

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(File record);
}