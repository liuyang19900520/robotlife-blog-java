package com.liuyang19900520.robotlife.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableTransactionManagement
@MapperScan("com.liuyang19900520.robotlife.blog.dao")
public class RobotlifeBlogJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(RobotlifeBlogJavaApplication.class, args);
    }
}
