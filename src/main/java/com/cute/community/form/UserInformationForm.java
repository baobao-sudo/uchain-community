package com.cute.community.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Lenovo
 */
@Data
public class UserInformationForm {

    @ApiModelProperty("个性签名")
    private String userSignature;

    @ApiModelProperty("个性签名")
    private String userDesc;
}
