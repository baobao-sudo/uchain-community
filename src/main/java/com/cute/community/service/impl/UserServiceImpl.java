package com.cute.community.service.impl;

import com.cute.community.dao.UserMapper;
import com.cute.community.dto.UserDTO;
import com.cute.community.entity.User;
import com.cute.community.enums.ResultEnum;
import com.cute.community.form.LoginForm;
import com.cute.community.security.JwtProperties;
import com.cute.community.security.JwtUserDetailServiceImpl;
import com.cute.community.service.UserService;
import com.cute.community.util.JwtTokenUtil;
import com.cute.community.util.RedisUtil;
import com.cute.community.util.ResultVOUtil;
import com.cute.community.vo.ResultVO;
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

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 */
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
    public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
        int code = Integer.valueOf(loginForm.getSecurityCode());

        String code2 = (String) redisUtil.get(loginForm.getStuId());
        int redisCode = Integer.valueOf(code2);
        if(code==redisCode) {
            User user = userMapper.selectByPrimaryKey(loginForm.getStuId());
            if (user == null) {
                return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
            }
            UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getStuId());
            if (!(new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword()))) {
                return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
            }
            Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getStuId(), loginForm.getPassword(), userDetails.getAuthorities());
            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            final String realToken = jwtTokenUtil.generateToken(userDetails);
            response.addHeader(jwtProperties.getTokenName(), realToken);

            Map map = new HashMap();
            map.put("role", user.getRole());
            map.put("token", realToken);
            return ResultVOUtil.success(map);
        }
        return ResultVOUtil.error(ResultEnum.CODE_ERROR);
    }


    @Override
    public ResultVO addUser(UserDTO userDTO) {
        int code = Integer.valueOf(userDTO.getSecurityCode());

        String code2 = (String) redisUtil.get(userDTO.getStuId());
        int redisCode = Integer.valueOf(code2);
        if (code == redisCode) {
            User user = new User();
            BeanUtils.copyProperties(userDTO, user);
            user.setPassword(passwordEncoder.encode(jwtProperties.getDefaultPassword()));
            log.info("用户信息" + user);
            int result = userMapper.insert(user);
            if (result != 1) {
                return ResultVOUtil.error(ResultEnum.USER_ALREADY_EXIST);
            }
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.CODE_ERROR);
    }
}
