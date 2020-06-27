package com.spww.vo;
import lombok.Data;



/**
 * 返回前端的统一类
 *
 * @author lin ShangBin
 * @version v1.1
 * @date 2020/4/19 22:04
 */
@Data
public class ResultVo<T> {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String desc;

    /**
     * 返回数据
     */
    private T data;

    public static ResultVo success(Object data) {
        ResultVo result = new ResultVo();
        result.setCode(1);
        result.setData(data);
        return result;
    }

    public static ResultVo success(Object data,String desc) {
        ResultVo result = new ResultVo();
        result.setCode(1);
        result.setData(data);
        result.setDesc(desc);
        return result;
    }

    public static ResultVo success(String desc) {
        ResultVo result = new ResultVo();
        result.setCode(1);
        result.setDesc(desc);
        return result;
    }

    public static ResultVo success() {
        ResultVo result = new ResultVo();
        result.setCode(1);
        return result;
    }

    public static ResultVo error(String msg) {
        return error(0,msg);
    }

    public static ResultVo error(Integer code, String desc) {
        ResultVo result = new ResultVo();
        result.setCode(code);
        result.setDesc(desc);
        return result;
    }

    public static ResultVo error(String desc,Object data) {
        ResultVo result = new ResultVo();
        result.setCode(0);
        result.setDesc(desc);
        result.setData(data);
        return result;
    }
}
