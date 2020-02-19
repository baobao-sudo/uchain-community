package com.cute.community.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserInformationForm
 * @Description 修改个性签名和个人简介的表单
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/
@Data
public class UserInformationForm {

    @ApiModelProperty("个性签名")
    private String userSignature;

    @ApiModelProperty("个人简介")
    private String userDesc;
}
