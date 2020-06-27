package com.spww.web;

import com.spww.dao.UserMapper;
import com.spww.entity.User;
import com.spww.exception.BusinessException;
import com.spww.util.HashKit;
import com.spww.util.JwtUtil;
import com.spww.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.spww.constant.BusinessContant.SYSTEM_ERROR;

@RequestMapping("/index")
@RestController
public class indexController {
    private static Logger logger = LoggerFactory.getLogger(indexController.class);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("login")
    public ResultVo  longin(User user) {
        User user1 = userMapper.selectByPrimaryKey(user.getUserid());
        logger.info("登录成功！生成token！");
        String token = JwtUtil.createToken(user1);
        return ResultVo.error(token);
    }


    @RequestMapping("test")
    public String test(){
        logger.info("测试");
        throw new BusinessException(-1,"信息错误");
    }
}
