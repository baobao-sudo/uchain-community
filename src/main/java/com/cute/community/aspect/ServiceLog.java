package com.cute.community.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *@ClassName ServiceLog
 *@Description service的执行的日志生成
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Aspect
@Slf4j
@Component
public class ServiceLog {

    @Pointcut("execution(public * com.cute.community.service.*.*(..))")
    public void service() {

    }


    @Before("service()")
    public void before(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getDeclaringTypeName() + "." + signature.getName();
        log.info("-----------------------------------------------------");
        log.info("当前执行的service方法:" + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.info("参数:" + arg);
        }
    }

    @AfterReturning(pointcut = "service()", returning = "ret")
    public void after(Object ret) {
        log.info("service返回参数:" + ret);
        log.info("-----------------------------------------------------");

    }


}
