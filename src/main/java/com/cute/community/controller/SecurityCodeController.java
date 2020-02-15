package com.cute.community.controller;

import com.cute.community.accessctro.RoleContro;
import com.cute.community.enums.RoleEnum;
import com.cute.community.util.RandomUtil;
import com.cute.community.util.RedisUtil;
import com.cute.community.util.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *@ClassName SecurityCodeController
 *@Description 验证码接口
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@RestController
@Slf4j
@RequestMapping("code")
@Api(tags = "验证码生成接口")
@CrossOrigin
public class SecurityCodeController {
    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/getCode")
    @ApiOperation("获取验证码")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object getCode(@RequestParam String username){
        String optCode = String.valueOf(RandomUtil.returnCode());
        redisUtil.set(username,optCode,120);
        //这里由于没有用短信发送　为了获取验证码是多少　我们打印到控制台
        log.info(optCode);
        return ResultVOUtil.success(optCode);
    }
}
