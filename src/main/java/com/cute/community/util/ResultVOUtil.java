package com.cute.community.util;


import com.cute.community.enums.ResultEnum;
import com.cute.community.vo.ResultVO;
import lombok.Data;

/**
 * @ClassName ResultVOUtil
 * @Description ResultVO的工具类
 * @Author Lenovo
 * @Date 2020/2/15
 * @Version 1.0
 **/

@Data
public class ResultVOUtil {

    /**
     * @param object
     * @return 有参成功结果
     * @author hobo
     */
    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /***
     *
     * @author hobo
     * @return 返回无参结果
     */
    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }

    public static ResultVO error(String msg, Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

}
