package com.cute.community.accessctro;

import com.cute.community.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 *@ClassName RoleContro
 *@Description 注解类，用于注解用户权限
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleContro {
    RoleEnum role();
}
