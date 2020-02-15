package com.cute.community.controller;


import com.cute.community.accessctro.RoleContro;
import com.cute.community.enums.RoleEnum;
import com.cute.community.form.UserRegisterForm;
import com.cute.community.form.UserUpdateForm;
import com.cute.community.service.UserService;
import com.cute.community.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 *@ClassName AdminController
 *@Description 管理员接口类
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Slf4j
@RequestMapping("/admin")
@RestController
@Api(tags = "管理员接口")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object addUser(@Valid UserRegisterForm registerForm, BindingResult bindingResult) {
        return userService.addUser(registerForm,bindingResult);
    }

    @GetMapping("/showAll")
    @ApiOperation("展示所有用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO showAll() {
        return userService.showAll();
    }

    @PostMapping("/deleteUser")
    @ApiOperation("删除用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO deleteUser(@RequestParam("stuId") String stuId) {
        return userService.deleteUser(stuId);
    }

    @PostMapping("/updateUser")
    @ApiOperation("修改用户信息")
    @RoleContro(role = RoleEnum.ADMIN)
    public ResultVO updateUser(@Valid UserUpdateForm userUpdateForm, BindingResult bindingResult) {
        return userService.updateUser(userUpdateForm,bindingResult);
    }


}
