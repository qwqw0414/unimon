package com.unimon.app.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

	private static final int REP_DOT = "com.unimon.app.".length();
	
	@Pointcut("execution(* com.unimon.app..*.*(..)) && !execution(* com.unimon.app.common..*.*(..))")
	public void pointcut() {}

	@Around("pointcut()")
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		String type = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		
//		공통 경로 제외
		type = type.substring(REP_DOT);
		
		log.debug(">> {}.{} >>", type, methodName);
		
		Object obj = joinPoint.proceed();
		
		log.debug("<< {}.{} <<", type, methodName);
		
		return obj;
	}
	
}
