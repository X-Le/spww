package com.spww.service.impl;

import com.spww.dao.UserMapper;
import com.spww.entity.User;
import com.spww.exception.BusinessException;
import com.spww.service.IUserService;
import com.spww.util.HashKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.spww.constant.BusinessContant.SYSTEM_ERROR;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String name, String password) {
        User user = userMapper.getUser(name);
        String salt = user.getSalt();
        String hashedPass = HashKit.sha256(salt + password);
        if (!user.getPasswordhash().equals(hashedPass)) {
            throw new BusinessException(SYSTEM_ERROR,"手机号或密码不正确");
        }
        return "登录成功";
    }

    @Override
    public String register(String name, String password) {
        String salt = HashKit.generateSaltForSha256();
        password = HashKit.sha256(salt + password);
        User user = new User();
        user.setName(name);
        user.setSalt(salt);
        user.setPasswordhash(password);
        int result = userMapper.insertSelective(user);
        if (result > 0) {
            return "注册成功";
        }
        return "注册失败";
    }
}
