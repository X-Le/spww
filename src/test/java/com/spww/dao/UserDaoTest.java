package com.spww.dao;

import com.spww.BaseTest;
import com.spww.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUser() {
        User user = userMapper.selectByPrimaryKey(2);
        System.out.println(user.toString());
    }
}
