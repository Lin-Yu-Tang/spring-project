package com.tom.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimeMeasureAspect {

	
	@Around("servicePointcut()")
	public Object measureTimeElapsed(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		String name = signature.getDeclaringType().getSimpleName();
		Method method = signature.getMethod();
		StopWatch sw = new StopWatch(name + "." + method.getName());
		try {
			sw.start(pjp.getSignature().getName());
			return pjp.proceed();
		} finally {
			sw.stop();
			
			System.out.println(sw.shortSummary());
		}
	}
	
	@Pointcut("execution( * com.tom.service..*.*(..))")
	public void servicePointcut(){}
}
