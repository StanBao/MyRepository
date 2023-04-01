package com.by.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component //交给spring管理
@Aspect  //标记为切面类
public class TimerAspect {
    //around环绕  里面写表达式  指定哪个包 任意类 任意方法 任意参数
    @Around("execution(* com.by.store.service.impl.*.*(..))")
    //
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //先记录当前时间
        long start = System.currentTimeMillis();
        //调用业务方法
        Object result = joinPoint.proceed();
        //在记录一次当前时间
        long end = System.currentTimeMillis();
        System.out.println("耗时"+(end-start));
        return  result;
    }
}
