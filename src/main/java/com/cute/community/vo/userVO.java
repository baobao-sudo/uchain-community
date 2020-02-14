package com.cute.community.vo;

import lombok.Data;

/**
 * @author Lenovo
 */
@Data
public class UserVO {
    private String stuId;

    private String userName;

    private Integer groupId;

    private Integer role;

    private String userDesc;

    private String userSignature;

    private String headPictureUrl;
}
