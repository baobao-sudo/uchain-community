package com.cute.community.service;

import com.cute.community.entity.User;
import com.cute.community.form.*;
import com.cute.community.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 *@ClassName UserService
 *@Description 操作用户
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

public interface UserService {

    /**
     * 增加用户
     * @param registerForm
     * @param bindingResult
     * @return
     */
    ResultVO addUser(UserRegisterForm registerForm, BindingResult bindingResult);

    /**
     * 获取当前用户
     * @return
     */
    User getCurrentUser();

    /**
     * 通过用户名获取用户
     * @param stuId
     * @return
     */
    User getUserByStuId(String stuId);

    /**
     *登录
     * @param loginForm
     * @param response
     * @param bindingResult
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult);

    /**
     * 展示所有用户
     * @return
     */
    ResultVO showAll();

    /**
     * 更新密码
     * @param userUpdatePwForm
     * @param bindingResult
     * @return
     */
    ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm, BindingResult bindingResult);

    /**
     * 更新操作
     * @param user
     * @return
     */
    Boolean update(User user);

    /**
     *上传头像
     * @param file
     * @return
     */
    ResultVO uploadPhoto(MultipartFile file);

    /**
     * 删除用户
     * @param stuId
     * @return
     */
    ResultVO deleteUser(String stuId);

    /**
     * 更新个人签名和个人简介
     * @param userUpdateForm
     * @param bindingResult
     * @return
     */
    ResultVO updateUser(UserUpdateForm userUpdateForm,BindingResult bindingResult);


    /**
     * 更新个人信息
     * @param userInformationForm
     * @param bindingResult
     * @return
     */
    ResultVO updateUserInformation(UserInformationForm userInformationForm,BindingResult bindingResult);

    /**
     * 查看个人信息
     * @return
     */
    ResultVO selfInformation();

}
