package com.cute.community.service.impl;

import com.cute.community.dao.UserMapper;
import com.cute.community.dto.PhotoUploadDTO;
import com.cute.community.entity.User;
import com.cute.community.enums.ResultEnum;
import com.cute.community.form.*;
import com.cute.community.security.JwtProperties;
import com.cute.community.security.JwtUserDetailServiceImpl;
import com.cute.community.service.UserService;
import com.cute.community.util.JwtTokenUtil;
import com.cute.community.util.RedisUtil;
import com.cute.community.util.ResultVOUtil;
import com.cute.community.util.UploadUtil;
import com.cute.community.vo.ResultVO;
import com.cute.community.vo.AllUserVO;
import com.cute.community.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@ClassName UserServiceImpl
 *@Description UserService实现类
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UploadUtil uploadUtil;

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        String key = "anonymousUser";
        if (!userName.equals(key)) {
            return getUserByStuId(userName);
        }
        return null;
    }

    @Override
    public User getUserByStuId(String stuId) {
        return userMapper.selectByPrimaryKey(stuId);
    }

    /**
     * Security自带的
     *
     * @param loginForm
     * @param response
     * @return
     */
    @Override
    public ResultVO login(LoginForm loginForm, HttpServletResponse response, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(loginForm.getStuId());
        if(user == null){
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getStuId());
        if(!(new BCryptPasswordEncoder().matches(loginForm.getPassword(),userDetails.getPassword()))){
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getStuId(),loginForm.getPassword(),userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String realToken = jwtTokenUtil.generateToken(userDetails);
        response.addHeader(jwtProperties.getTokenName(),realToken);

        Map map = new HashMap();
        map.put("username",user.getUserName());
        map.put("role",user.getRole());
        map.put("token",realToken);

        return ResultVOUtil.success(map);
    }

    @Override
    public ResultVO showAll() {
        List<User> users = new ArrayList<>();
        users = userMapper.selectAll();
        List<AllUserVO> allUserVOS = new ArrayList<>();
        for(User user:users){
            AllUserVO allUserVO = new AllUserVO();
            BeanUtils.copyProperties(user, allUserVO);
            allUserVOS.add(allUserVO);
        }
        return ResultVOUtil.success(allUserVOS);
    }


    @Override
    public ResultVO addUser(UserRegisterForm registerForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        boolean isHave = (userMapper.selectByPrimaryKey(registerForm.getStuId()) != null);
        if (isHave){
            return ResultVOUtil.error(ResultEnum.USER_ALREADY_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(registerForm,user);
        user.setRole(0);
        user.setPassword(passwordEncoder.encode(jwtProperties.getDefaultPassword()));
        log.info("用户信息" + user);
        int result = userMapper.insert(user);
        if (result != 1) {
            return ResultVOUtil.error(ResultEnum.SQL_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUserPw(UserUpdatePwForm userUpdatePwForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = getCurrentUser();
        log.info("学号: "+ user.getStuId());
        if(!user.getStuId().equals(userUpdatePwForm.getStuId())){
            log.info("非本人操作,修改密码失败!");
            return ResultVOUtil.error(ResultEnum.IS_NOT_PERSONAL_OPERATION);
        }
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userUpdatePwForm.getStuId());
        if(!new BCryptPasswordEncoder().matches(userUpdatePwForm.getOldPassword(),userDetails.getPassword())){
            return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
        }
        User updateUser = userMapper.selectByPrimaryKey(userUpdatePwForm.getStuId());
        updateUser.setPassword(new BCryptPasswordEncoder().encode(userUpdatePwForm.getNewPassword()));
        if(update(updateUser)){
            return  ResultVOUtil.success("修改密码成功");
        }else{
            System.out.println(updateUser.toString());
            log.info("修改密码失败");
        }
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR);
    }

    @Override
    public ResultVO uploadPhoto(MultipartFile file) {
        User user = getCurrentUser();
        log.info("开始上传图片");
        PhotoUploadDTO photoUploadDTO = uploadUtil.fileUpload(file);
        user.setHeadPictureUrl(photoUploadDTO.getPhotoUrl());
        update(user);
        return ResultVOUtil.success("自拍上传成功噢！！！");
    }

    @Override
    public ResultVO deleteUser(String stuId) {
        if(userMapper.deleteByPrimaryKey(stuId) != 1){
            return ResultVOUtil.error(ResultEnum.USER_DELETE_FAILED);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUser(UserUpdateForm userUpdateForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("参数注意必填项！");
            return ResultVOUtil.error(ResultEnum.PARAMETER_ERROR);
        }
        User user = getCurrentUser();
        if(userMapper.selectByPrimaryKey(userUpdateForm.getStuId()) == null){
            log.info("此用户不存在！");
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        user.setUserName(userUpdateForm.getUserName());
        user.setGroupId(userUpdateForm.getGroupType());
        user.setRole(userUpdateForm.getRole());
        update(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateUserInformation(UserInformationForm userInformationForm, BindingResult bindingResult) {
        User user = getCurrentUser();
        user.setUserSignature(userInformationForm.getUserSignature());
        user.setUserDesc(userInformationForm.getUserDesc());
        update(user);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO selfInformation() {
        User user = getCurrentUser();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return ResultVOUtil.success(userVO);
    }

    @Override
    public Boolean update(User user) {
        return (userMapper.updateByPrimaryKey(user) == 1);
    }

}
