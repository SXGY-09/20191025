package com.sxgy.sp52.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @author SXGY_09
 * @description Aop切面配置
 * @date 2019-11-15 22:06
 */
@Aspect
@Component
@Slf4j
public class WebAop {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Pointcut("execution(* com.sxgy.sp52.*.controller.*.*(..))")
    public void exec() {
    }

    @Before("exec()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("收到请求============"
                + joinPoint.getTarget().getClass().getName()
                + "--->" + joinPoint.getSignature().getName());
        log.info("开始执行请求");
    }

    @After("exec()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("请求执行完毕");
    }

    @Around("exec()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=====before====");
        long start = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable t) {
            return;
        }
        long end = System.currentTimeMillis();
        log.info(end - start + "");
        log.info("=====after=====");
    }
}
