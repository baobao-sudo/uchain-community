package com.cute.community.controller;


import com.cute.community.accessctro.RoleContro;
import com.cute.community.dto.UserDTO;
import com.cute.community.enums.RoleEnum;
import com.cute.community.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: zty
 * @date 2019/9/5 下午5:06
 * @description:
 */
@Slf4j
@RequestMapping("/admin")
@RestController
@Api(tags = "注册接口")
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    @RoleContro(role = RoleEnum.ADMIN)
    public Object addUser(UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

}
