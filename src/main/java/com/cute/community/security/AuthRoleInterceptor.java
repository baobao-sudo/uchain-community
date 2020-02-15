package com.cute.community.security;

import com.alibaba.fastjson.JSON;
import com.cute.community.accessctro.RoleContro;
import com.cute.community.entity.User;
import com.cute.community.enums.ResultEnum;
import com.cute.community.service.UserService;
import com.cute.community.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *@ClassName AuthRoleInterceptor
 *@Description 权限验证
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Slf4j
@Service
public class AuthRoleInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String json = JSON.toJSONString(ResultVOUtil.error(ResultEnum.AUTHENTICATION_ERROR));
        User user = userService.getCurrentUser();
        if (user == null) {

            return true;
        }
        log.info("============执行权限验证============");
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RoleContro roleContro = handlerMethod.getMethodAnnotation(RoleContro.class);
            if (roleContro == null) {
                return true;
            }
            Integer roleValue = roleContro.role().getValue();
            Integer userValue = user.getRole();
            log.info("RoleValue:{},userRole:{}", roleValue, userValue);
            if (userValue >= roleValue) {
                return true;
            } else {
                json = JSON.toJSONString(ResultVOUtil.error(ResultEnum.PERMISSION_DENNY));
                log.info("============权限不足===============");
            }
        }
        response.getWriter().append(json);
        return false;
    }
}
