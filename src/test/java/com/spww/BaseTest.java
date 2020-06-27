package com.spww;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
/*告诉junit spring 配置文件的位置*/
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class BaseTest {
}
