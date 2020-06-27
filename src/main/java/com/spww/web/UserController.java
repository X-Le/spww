package com.spww.web;

import com.spww.service.IUserService;
import com.spww.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.spww.constant.BusinessContant.SYSTEM_ERROR;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("register")
    public String register() {
        return "/user/register";
    }

    @PostMapping(value = "register",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String register(String name,String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return "参数不能为空";
        }
        String result=userService.register(name,password);
        return result;
    }

    @PostMapping(value = "login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(String name,String password) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return "参数不能为空";
        }
        String result = userService.login(name,password);
        return result;
    }
}
