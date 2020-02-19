package com.cute.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName UchainCommunityApplication
 * @Description 启动类
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@MapperScan("com.cute.community.dao")
@SpringBootApplication
public class UchainCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UchainCommunityApplication.class, args);
    }

}
