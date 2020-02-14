package com.cute.community.controller;

import com.cute.community.form.LoginForm;
import com.cute.community.service.UserService;
import com.cute.community.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: zty
 * @date 2019/9/6 上午10:37
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/anon")
@Api(tags = "登录接口")
@CrossOrigin
public class AnonController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultVO login(@Valid LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult) {
        return userService.login(loginForm,response,bindingResult);
    }
}
