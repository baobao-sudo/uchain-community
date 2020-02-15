package com.cute.community.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@ClassName JwtProperties
 *@Description 有关jwt的数据初始化文件
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private String expiration;

    private String tokenStart;

    private String tokenName;

    private String defaultPassword;
}
