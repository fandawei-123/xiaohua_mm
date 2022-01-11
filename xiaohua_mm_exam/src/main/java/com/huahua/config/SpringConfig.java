package com.huahua.config;

import org.springframework.context.annotation.*;

/**
 * @author Huahua
 * @version 1.0
 */
@Configuration
@ComponentScan("com.huahua")
@PropertySource("classpath:jdbc.properties")
@Import({JDBCConfig.class,MyBatisConfig.class})
public class SpringConfig {
}
