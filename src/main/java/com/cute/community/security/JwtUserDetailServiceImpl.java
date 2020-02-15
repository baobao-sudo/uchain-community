package com.cute.community.security;


import com.cute.community.entity.User;
import com.cute.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *@ClassName JwtUserDetailServiceImpl
 *@Description UserDetailService实现类
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/


@Service
@Slf4j
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String stuId) throws UsernameNotFoundException {
        User user = userService.getUserByStuId(stuId);
        if (user == null) {
            log.info("此用户不存在");
            throw new UsernameNotFoundException(String.format("用户名为 %s 的用户不存在", stuId));
        } else {
            Integer role = user.getRole();
            return new JwtUser(stuId, user.getPassword(), role);
        }
    }
}
