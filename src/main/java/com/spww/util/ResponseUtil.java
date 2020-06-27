package com.spww.util;

import com.alibaba.fastjson.JSON;
import com.spww.vo.ResultVo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    public static void returnJSON (HttpServletResponse response, ResultVo result) throws IOException {

        response.setContentType("application/json; charset=" + "UTF-8");

        response.setHeader("Charset", "UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*"); //解决HTML5+js跨域无法访问问题
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
