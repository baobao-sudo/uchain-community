package com.cute.community.vo;

import lombok.Data;

/**
 *@ClassName AllUserVO
 *@Description 管理员展示用户
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Data
public class AllUserVO {
    private String stuId;

    private String userName;

    private Integer groupType;

    private Integer role;
}
