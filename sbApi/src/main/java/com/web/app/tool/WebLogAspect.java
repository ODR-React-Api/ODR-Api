package com.web.app.tool;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// import org.apache.logging.log4j.LogManager;
// import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class WebLogAspect {
  // 获取日志类，方便直接在控制台输出统一格式的日志信息
  private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

  // 切入点:待增强的方法
  @Pointcut("execution(public * com.web.app.controller.*.*(..))")
  // 切入点签名
  public void log() {
    System.out.println("pointCut签名。。。");
  }

  // 前置通知
  @Before("log()")
  public void deBefore(JoinPoint jp) throws Throwable {
    // 接收到请求，记录请求内容
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    // 记录下请求内容
    // System.out.println("====================== ↓↓↓↓↓ ======================");
    // System.out.println("RESQUEST_URL : " + request.getRequestURL().toString());
    // System.out.println("HTTP_METHOD : " + request.getMethod());
    // System.out.println("CLASS_METHOD : " + jp);
    // System.out.println("ARGS : " + Arrays.toString(jp.getArgs()));
    // System.out.println("前置通知：方法执行前执行...");
    logger.warn("======================  ↓↓↓↓↓  ======================");
    logger.info("-----TYPE : " + request.getMethod());
    logger.info("----- URL : " + request.getRequestURL().toString());
    logger.info("-----FUNC : " + jp);
    logger.info("-----ARGS : " + Arrays.toString(jp.getArgs()));
    logger.info("-----前置通知：方法执行前执行...");
  }

  // 返回通知
  @AfterReturning(returning = "ret", pointcut = "log()")
  public void doAfterReturning(Object ret) throws Throwable {
    // 处理完请求，返回内容
    // System.out.println("返回通知：方法的返回值 : " + ret);
    logger.info("-----返回通知：方法的返回值 : " + ret);
  }

  // 异常通知
  @AfterThrowing(throwing = "ex", pointcut = "log()")
  public void throwss(JoinPoint jp, Exception ex) {
    // System.out.println("异常通知：方法异常时执行.....");
    // System.out.println("产生异常的方法：" + jp);
    // System.out.println("异常种类：" + ex);

    logger.error("-----异常通知：方法异常时执行.....");
    logger.error("-----产生异常的方法：" + jp);
    logger.error("-----异常种类：" + ex);
  }

  // 后置通知
  @After("log()")
  public void after(JoinPoint jp) {
    // System.out.println("后置通知：最后且一定执行.....");
    // System.out.println("====================== ↑↑↑↑↑ ======================");
    logger.info("-----后置通知：最后且一定执行.....");
    logger.warn("======================  ↑↑↑↑↑  ======================");
  }
}
