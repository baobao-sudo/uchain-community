package com.cute.community.controller;

import com.cute.community.accessctro.RoleContro;
import com.cute.community.enums.ResultEnum;
import com.cute.community.enums.RoleEnum;
import com.cute.community.form.UserInformationForm;
import com.cute.community.form.UserUpdatePwForm;
import com.cute.community.service.UserService;
import com.cute.community.util.ResultVOUtil;
import com.cute.community.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description 用户接口
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/updateUserPw")
    @ApiOperation("修改密码")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO updateUserPw(@Valid UserUpdatePwForm userUpdatePwForm,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return userService.updateUserPw(userUpdatePwForm);
    }

    @PostMapping("/uploadPhoto")
    @ApiOperation("上传头像")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO uploadPhoto(@RequestParam("file") MultipartFile file) {
        return userService.uploadPhoto(file);
    }

    @PostMapping("/updateUserInformation")
    @ApiOperation("更新个性签名和个人简介")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO updateUserInformation(@Valid UserInformationForm userInformationForm,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        return userService.updateUserInformation(userInformationForm);
    }

    @GetMapping("/selfInformation")
    @ApiOperation("展示个人信息")
    @RoleContro(role = RoleEnum.USER)
    public ResultVO selfInformation(){
        return userService.selfInformation();
    }
}
