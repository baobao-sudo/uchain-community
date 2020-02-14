package com.cute.community.service;

import com.cute.community.entity.User;
import com.cute.community.form.*;
import com.cute.community.vo.ResultVO;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     * 添加用户
     * @param registerForm
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


    ResultVO login(LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult);

    ResultVO showAll();

    ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm, BindingResult bindingResult);

    /**
     * 更新操作
     * @param user
     * @return
     */
    Boolean update(User user);

    ResultVO uploadPhoto(MultipartFile file);

    ResultVO deleteUser(String stuId);

    ResultVO updateUser(UserUpdateForm userUpdateForm,BindingResult bindingResult);


    ResultVO updateUserInformation(UserInformationForm userInformationForm,BindingResult bindingResult);

    ResultVO selfInformation();

}
