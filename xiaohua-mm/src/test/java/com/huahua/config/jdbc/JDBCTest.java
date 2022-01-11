package com.huahua.config.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.huahua.config.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JDBCTest {

    @Test
    public void getDataSource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        DruidDataSource dataResource = (DruidDataSource) ctx.getBean("dataResource");
        System.out.println(dataResource);
    }
}
