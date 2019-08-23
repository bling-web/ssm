package com.cc.springMVC;

import com.cc.springMVC.Domain.User;
import com.cc.springMVC.Mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    public void findAll() throws IOException {
        //1.读取配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("applicationContext.xml");
        //2.获取sqlSession对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        SqlSession sqlSession = build.openSession(true);
        //3.获取代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.执行方法
        List<User> all = mapper.findAll();
        for(User u: all){
            System.out.println(u);
        }
        //5.释放资源
        sqlSession.close();
        resourceAsStream.close();
    }
}
