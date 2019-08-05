package com.cc.work3.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbUtil {
    //读取配置文件
    static DataSource dataSource;
    static{
        InputStream resourceAsStream = JdbUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            //初始化连接池
            dataSource=DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static  DataSource getDataSource(){
        return dataSource;
    }
}
