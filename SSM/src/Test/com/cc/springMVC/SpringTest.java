package com.cc.springMVC;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
    @Test
    public void springTest(){
        ClassPathXmlApplicationContext aC = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object userServiceImpl = aC.getBean("userServiceImpl");
        System.out.println(userServiceImpl);

    }
}
