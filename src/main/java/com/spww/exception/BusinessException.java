package com.spww.exception;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException{
    //错误代码
    private Integer errorCode = -1;

    //错误提示
    private String errorMsg = "未知系统异常";

    public BusinessException(Integer errorCode,String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException() {
        super();
    }
}
