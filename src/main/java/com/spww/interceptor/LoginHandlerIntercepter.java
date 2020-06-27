package com.spww.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.interfaces.Claim;
import com.spww.exception.BusinessException;
import com.spww.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.spww.constant.BusinessContant.SYSTEM_ERROR;

/*拦截器配置*/

public class LoginHandlerIntercepter implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginHandlerIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> mapIn = request.getParameterMap();
        JSONArray objects = new JSONArray();
        StringBuffer stringBuffer = request.getRequestURL();
        logger.info("httpServletRequest ,路径:" + stringBuffer + ",入参:" + objects.toString());

        //获取 header里的token
        final String token = request.getHeader("authorization");
        //来探测后续需要发起的跨域POST请求是否安全可接受,所以这个请求就不需要拦截
        if ("OPTIONS".equals(request.getMethod())) {
            logger.info("OPTIONS请求不做拦截操作");
            return true;
        }else{
            if (token == null) {
                throw new BusinessException(SYSTEM_ERROR,"没有token!");
            }
            Map<String, Claim> userData = JwtUtil.verifyToken(token);
            if (userData == null) {
                throw new BusinessException(SYSTEM_ERROR,"token不合法!");
            }
            Integer id = userData.get("id").asInt();
            String name = userData.get("name").asString();
            //拦截器 拿到用户信息，放到request中
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
