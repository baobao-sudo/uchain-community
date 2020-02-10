package com.cute.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lenovo
 */
@MapperScan("com.cute.community.dao")
@SpringBootApplication
public class UchainCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UchainCommunityApplication.class, args);
    }

}
