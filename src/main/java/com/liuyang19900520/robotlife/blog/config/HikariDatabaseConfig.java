package com.liuyang19900520.robotlife.blog.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuya
 */
@Configuration
public class HikariDatabaseConfig {


    @Bean
    public HikariDataSource HikariDataSourceRobotLifeBlog() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/robotlife?useUnicode=true&characterEncoding=utf-8");
//        config.setUsername("root");
//        config.setPassword("1234");

        config.setJdbcUrl("jdbc:mysql://liuyang19900520db.chuuiho8uwp7.ap-northeast-1.rds.amazonaws.com/robotlife?useUnicode=true&characterEncoding=utf-8");
        config.setUsername("liuyang19900520");
        config.setPassword("LY-052077");

        config.setPoolName("HikariDataSourceRobotLifeBlog");
        config.setMinimumIdle(5);
        config.setMaximumPoolSize(15);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);
        config.setConnectionTimeout(30000);

        HikariDataSource ds = new HikariDataSource(config);

        return ds;
    }




}
