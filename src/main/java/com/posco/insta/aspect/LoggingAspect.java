package com.posco.insta.aspect;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j // 롬북
public class LoggingAspect {
//    @Before("execution(* com.posco.insta.user.service.*.find*(..))")
//    public void loggerBefore(){
////        System.out.println("---------- before ---------");
//        log.info("----------before---------");
//    }
//
//    @After("execution(* com.posco.insta.user.service.*.find*(..))")
//    public void loggerAfter(){
////        System.out.println("---------- after -----------");
//        log.info("----------after---------");
//    }

    @Around("execution(* com.posco.insta.user.controller.*.*(..))")
    public Object loggerAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long beforeTimeMillis = System.currentTimeMillis();
        log.info("start : " + beforeTimeMillis);

        Object result = proceedingJoinPoint.proceed(); // 이거 전후로 나눠지는 거
        long afterTimeMillis = System.currentTimeMillis();
        log.info("after : " + afterTimeMillis +
                ", 시간차 : " +  (afterTimeMillis - beforeTimeMillis));
        return result;
    }
}
