package com.huahua.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Huahua
 */
@Component
public class MyBatisConfig {


    @Bean("mapperScannerConfigurer")
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.huahua.dao");
        return msc;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Autowired DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.huahua.domain");
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties props = new Properties();
        props.setProperty("helperDialect", "mysql");
        props.setProperty("reasonable", "true");
        pageInterceptor.setProperties(props);
        //添加插件
        ssfb.setPlugins(pageInterceptor);
        ssfb.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = null;
        try {
             sqlSessionFactory = ssfb.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

}
