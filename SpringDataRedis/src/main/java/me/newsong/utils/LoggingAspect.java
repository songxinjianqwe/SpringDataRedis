package me.newsong.utils;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());
	
	@Pointcut("execution(* me.newsong.service.impl.*.*(..))||execution(* me.newsong.web.*.*(..))")
	public void declareJoinPointExpression() {
	}

	@Before("declareJoinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {// 连接点
		Object[] args = joinPoint.getArgs();// 取得方法参数
		logger.info("The method [" + joinPoint.getSignature() + " ] begins with Parameters: " + Arrays.toString(args));
	}

	@AfterReturning(value = "declareJoinPointExpression()", returning = "result")
	public void afterMethodReturn(JoinPoint joinPoint, Object result) {
		logger.info("The method [" + joinPoint.getSignature() + "] ends with Result: " + result);
	}

	@AfterThrowing(value = "declareJoinPointExpression()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
		logger.error("Error happened in method: [" + joinPoint.getSignature()+"]");
		logger.error("Parameters: "+Arrays.toString(joinPoint.getArgs()));
		logger.error("Exception StackTrace: ",e);
	}
}
