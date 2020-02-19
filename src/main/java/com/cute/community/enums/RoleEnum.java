package com.cute.community.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @ClassName RoleEnum
 * @Description 用户权限的枚举
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/
@Getter
public enum RoleEnum {
    USER(0, "普通用户"),
    ADMIN(1, "管理员");


    private Integer value;
    private String role;

    RoleEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }

    public static String getRole(Integer integer) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getValue(), ADMIN.getRole());
        hashMap.put(USER.getValue(), USER.getRole());
        return hashMap.get(integer);
    }

    public static Integer getValue(String role) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(ADMIN.getRole(), ADMIN.getValue());
        hashMap.put(USER.getRole(), USER.getValue());
        return hashMap.get(role);
    }
}
