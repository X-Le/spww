package com.spww.exception;

import com.alibaba.fastjson.JSON;

import com.spww.util.ResponseUtil;
import com.spww.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerException {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerException.class);

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void  handleMyException(Exception e, HttpServletResponse response){
        ResultVo result = null;
        //参数验证异常
        if (e instanceof BusinessException) {
            BusinessException bus = (BusinessException) e;
            result = ResultVo.error(bus.getErrorCode(), bus.getErrorMsg());
        } else {
            result = ResultVo.error(-1, "未知系统异常");
            logger.error("", e);
        }

        //返回结果
        try {
            ResponseUtil.returnJSON(response, result);
            logger.info("接口返回结果{}", JSON.toJSONString(result));
        } catch (IOException e1) {
            logger.error("", e1);
        }
        return ;
    }
}
