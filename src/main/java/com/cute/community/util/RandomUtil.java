package com.cute.community.util;

import java.util.Random;

/**
 *@ClassName RandomUtil
 *@Description 生成随机数的工具类
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

public class RandomUtil {
    public static int returnCode(){
        Random rand = new Random();
        return rand.nextInt(89999)+10000;
    }

}
