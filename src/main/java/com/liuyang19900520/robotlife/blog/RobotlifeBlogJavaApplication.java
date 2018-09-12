package com.liuyang19900520.robotlife.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.liuyang19900520.robotlife.blog.dao")
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class RobotlifeBlogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RobotlifeBlogJavaApplication.class, args);
    }
}
