package com.marvel.framework.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname RequestLogAspect
 * @Description 请求日志拦截器
 * @Date 2019-03-29 16:44
 * @Author zhongjie
 */
@Aspect
@Component
public class RequestLogAspect {

    /**
     * log对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(RequestLogAspect.class);

    /**
     * 记录接口耗时
     */
    private NamedThreadLocal<Long> threadLocal = new NamedThreadLocal<Long>("Api-Processor-Times");


    @Pointcut(value = "execution(* com.marvel.*.controller..*Controller.*(..)))")
    public void pointCut(){

    }

    @Before(value = "pointCut()")
    public void before() {
        threadLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint point, Object result) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        writeLogInfo(request, response, result);
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint point, Throwable e) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        writeLogInfo(request, response, e.toString());
    }

    /**
     * 打印接口日志
     * @param request
     */
    private void writeLogInfo(HttpServletRequest request, HttpServletResponse response, Object result) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(request.getRemoteAddr());
        logBuilder.append("\t");
        logBuilder.append(request.getRequestURI());
        logBuilder.append("\t");
        logBuilder.append(request.getMethod());
        logBuilder.append("\t");
        logBuilder.append(response.getStatus());
        logBuilder.append("\t");
        logBuilder.append((System.currentTimeMillis() - threadLocal.get()) + "ms");
        logBuilder.append("\t");
        logBuilder.append(JSON.toJSONString(request.getParameterMap()));
        logBuilder.append("\t");
        logBuilder.append(request.getRemoteAddr());
        logBuilder.append("\t");
        logBuilder.append(result);
        LOG.info(logBuilder.toString());
    }

}
