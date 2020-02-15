package com.cute.community.vo;

import lombok.Data;

/**
 *@ClassName ResultVO
 *@Description 传给前端的形式
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
