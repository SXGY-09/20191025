package com.sxgy.sp52.core.aop;

import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.util.internet.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void doBefore() {
        log.debug("<===before===>");
    }

    @AfterReturning("exec()")
    public void doAfterReturn() {
        log.debug("===>after return<===");
    }

    @AfterThrowing("exec()")
    public void doAfterThrow() {
        log.debug("===>after throw<===");
    }

    @After("exec()")
    public void doAfter() {
        log.debug("===>after<===");
    }

    @Around("exec()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===>around begin " + sdf.format(new Date()));
        long start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        try {
            Object result = joinPoint.proceed();
            if (result == null) {
                return null;
            }
            if (attributes == null) {
                return result;
            }
            StringBuffer Url = request.getRequestURL();
            if (Url.indexOf("/api/") == -1 || Url.indexOf("/ws") != -1) {
                return result;
            }
            String name = (String) session.getAttribute("name");
            if (name == null || name.length() < 1) {
                return result;
            }
            String ip = IpUtil.getIpAddr(request);

            Object[] args = joinPoint.getArgs();
            StringBuilder argsTypes = new StringBuilder();
            //记录请求信息
            log.info("请求URL：" + request.getRequestURL().toString());
            log.info("IP 地址：" + ip);
            log.info("请求方式：" + request.getMethod());
            log.info("请求时间：" + sdf.format(start));
            log.info("请求用户：" + name);
            log.info("参数个数：" + args.length);
            if (args.length > 0) {
                for (Object arg : args) {
                    if (arg != null) {
                        argsTypes.append(arg.getClass().getName() + ",");
                    } else {
                        argsTypes.append("null,");
                    }
                }
                argsTypes.deleteCharAt(argsTypes.length() - 1);
                log.info("参数类型：" + argsTypes.toString());
            }
            log.info("Controller:" + joinPoint.getTarget().getClass().getTypeName()
                    + "." + joinPoint.getTarget().getClass().getName());
            log.info("执行方法：" + joinPoint.getSignature().getDeclaringTypeName()
                    + "." + joinPoint.getSignature().getName());
            log.info("执行参数：" + joinPoint.getArgs());
            long end = System.currentTimeMillis();
            if (result instanceof ApiRp) {
                ApiRp apiRp = (ApiRp) result;
                apiRp.setParams("耗时：" + (end - start) + "ms");
                result = apiRp;
            }
            log.info("AOP处理耗时" + (end - start) + "ms");
            log.info("<===around return " + sdf.format(new Date()));
            return result;
        } catch (Throwable t) {
            log.info("<===around throw " + sdf.format(new Date()));
            throw t;
        }
    }
}
