package com.cute.community.service;

import com.cute.community.dto.UserDTO;
import com.cute.community.entity.User;
import com.cute.community.form.LoginForm;
import com.cute.community.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     * 添加用户
     * @param userDTO
     * @return
     */
    ResultVO addUser(UserDTO userDTO);

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


    ResultVO login(LoginForm loginForm, HttpServletResponse response);

}
