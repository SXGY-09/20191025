package com.sxgy.sp52.core.aop;

import com.sxgy.sp52.core.aop.dto.SimpleLog;
import com.sxgy.sp52.core.domain.ApiRp;
import com.sxgy.sp52.core.pojo.Sp52Log;
import com.sxgy.sp52.core.service.LogService;
import com.sxgy.sp52.core.util.internet.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
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

    @Resource
    private LogService logService;

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
        SimpleLog simpleLog = getSimpleLog(joinPoint);
        Sp52Log operationLog = new Sp52Log();
        operationLog.setOperatorId((Long) session.getAttribute("id"));
        operationLog.setOperatorName((String) session.getAttribute("name"));
        operationLog.setLogName(simpleLog.getName());
        operationLog.setLogValue(simpleLog.getValue());
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
            //记录请求信息
            log.info("请求URL：" + request.getRequestURL().toString());
            log.info("IP 地址：" + ip);
            log.info("请求方式：" + request.getMethod());
            log.info("请求时间：" + sdf.format(start));
            log.info("请求用户：" + name);
            log.info("执行方法：" + joinPoint.getSignature().getDeclaringTypeName()
                    + "." + joinPoint.getSignature().getName());
            log.info("参数个数：" + args.length);
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    StringBuilder sb = new StringBuilder("参数" + (i + 1) + "：");
                    if (args[i] == null) {
                        sb.append("???: null");
                    } else {
                        sb.append(args[i].getClass().getTypeName() + ": " + args[i].toString());
                    }
                    log.info(sb.toString());
                }
            }
            long end = System.currentTimeMillis();
            if (result instanceof ApiRp) {
                ApiRp apiRp = (ApiRp) result;
                apiRp.setParams("耗时：" + (end - start) + "ms");
                result = apiRp;
            }
            log.info("AOP处理耗时" + (end - start) + "ms");
            log.info("<===around return " + sdf.format(new Date()));
            operationLog.setOperatorId((Long) session.getAttribute("id"));
            operationLog.setOperatorName((String) session.getAttribute("name"));
            operationLog.setLogState("成功");
            operationLog.setLogTime(new Date());
            return result;
        } catch (Throwable t) {
            log.info("<===around throw " + sdf.format(new Date()));
            operationLog.setLogState("失败");
            operationLog.setLogTime(new Date());
            throw t;
        } finally {
            new Thread(() -> logService.insertLog(operationLog)).start();
        }
    }

    /**
     * 获取方法的日志注解
     *
     * @param joinPoint 节点
     * @return SimpleLog
     * @throws Exception 反射解析异常
     */
    private static SimpleLog getSimpleLog(ProceedingJoinPoint joinPoint) throws Exception {
        SimpleLog simpleLog = new SimpleLog();
        Class clazz = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = clazz.getMethods();
        Object[] args = joinPoint.getArgs();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                if (method.getParameterTypes().length == args.length) {
                    if (method.getAnnotation(Log.class) != null) {
                        simpleLog.setName(method.getAnnotation(Log.class).name());
                        simpleLog.setValue(method.getAnnotation(Log.class).value());
                    }
                    break;
                }
            }
        }
        return simpleLog;
    }
}
