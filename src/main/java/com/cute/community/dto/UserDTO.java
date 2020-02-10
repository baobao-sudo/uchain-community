package com.cute.community.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull(message = "不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    @NotNull(message = "不能为空")
    @ApiModelProperty("用户真实姓名")
    private String userName;

    @NotNull(message = "不能为空")
    @ApiModelProperty("成员方向")
    private Integer groupId;

    @NotNull(message = "不能为空")
    @ApiModelProperty("成员权限")
    private Integer role;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty("验证码" +
            "")
    private Integer securityCode;
}
