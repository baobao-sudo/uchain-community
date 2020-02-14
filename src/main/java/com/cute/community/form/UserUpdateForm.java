package com.cute.community.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Lenovo
 */
@Data
public class UserUpdateForm {

    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String stuId;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty("姓名")
    private String userName;

    @NotNull(message = "小组不能为空")
    @ApiModelProperty("小组")
    private Integer groupType;

    @NotNull(message = "权限不能为空")
    @ApiModelProperty("权限")
    private Integer role;
}
