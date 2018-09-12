package com.liuyang19900520.robotlife.blog.aop;


import com.liuyang19900520.robotlife.blog.common.util.LJsonUtils;
import com.liuyang19900520.robotlife.blog.common.util.LLoggerUtils;
import com.liuyang19900520.robotlife.blog.common.util.LJsonUtils;
import com.liuyang19900520.robotlife.blog.common.util.LLoggerUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: sales-promotion-api
 * @description:
 * @author: LiuYang
 * @create: 2018-06-27 14:07
 **/
@Aspect
@Component
@Order(1)
public class LogAspect {

    private Logger log = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    //申明一个切点 里面是 execution表达式
    @Pointcut("execution(public * com.liuyang19900520.robotlife.blog.web.controller.*.*(..))")
    private void controllerAspect() {
    }

    //请求method前打印内容
    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //打印请求内容
        log.info("===============Request===============");
//        log.info("RequestURL:" + request.getRequestURL().toString());
//        log.info("Args:" + Arrays.toString(joinPoint.getArgs()));
        log.info("RequestURL:" + request.getRequestURL().toString());
        log.info("Args:" + LJsonUtils.objectToJson(joinPoint.getArgs()));
        log.info("===============Request===============");
    }

    //在方法执行完结后打印返回内容
    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        log.info("--------------Response----------------");
        log.info("Response Contents:" + LJsonUtils.objectToJson(o));
        log.info("Spend Time:" + (System.currentTimeMillis() - startTime.get()));
        log.info("--------------Response----------------");
    }
}
