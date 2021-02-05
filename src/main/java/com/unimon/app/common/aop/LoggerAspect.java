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

	@Pointcut("execution(* com.unimon.app..*.*(..)) && !execution(* com.unimon.app.common..*.*(..))")
	public void pointcut() {}

	@Around("pointcut()")
	public Object loggerAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		String type = signature.getDeclaringTypeName();
		String methodName = signature.getName();
		
		final String REP = "com.unimon.app.";
		type = type.replace(REP, "");
		
		log.debug(">> {}.{} >>", type, methodName);
		
		Object obj = joinPoint.proceed();
		
		log.debug("<< {}.{} <<", type, methodName);
		
		return obj;
	}
	
}
