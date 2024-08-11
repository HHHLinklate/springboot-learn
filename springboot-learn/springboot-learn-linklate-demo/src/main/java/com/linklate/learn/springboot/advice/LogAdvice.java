package com.linklate.learn.springboot.advice;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(5)
public class LogAdvice {


    public  void before(JoinPoint point) {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        System.out.println(className + "::" + methodName+ "开始执行了");
    }
}
