package com.cute.community.enums;

import lombok.Getter;

/**
 * @ClassName ResultEnum
 * @Author hobo
 * @Date 19-4-22 下午3:45
 * @Description
 **/
@Getter
public enum ResultEnum {
    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),
    USER_NOT_EXIST(1,"此用户不存在" ),
    PASSWORD_ERROR(2,"密码错误" ),
    OLD_PASSWORD_ERROR(3,"原密码错误" ),
    IS_NOT_PERSONAL_OPERATION(4,"非本人操作" ),
    USER_ALREADY_EXIST(5, "此用户已存在"),
    CODE_ERROR(6,"验证码错误");;


    private Integer code;

    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
