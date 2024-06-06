package com.tom.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.tom.bean.PageRequestDTO;

@Aspect
@Component
public class TimeMeasureAspect {

	
//	@Around("servicePointcut()")
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
	
//	@Before("contorllerArgumentPointcut()")
	public void printRequestBody(JoinPoint joinPoint) {
		Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
	}
	
	@Before("contorllerArgumentPointcuts(pageRequestDTO)")
	public void printRequestBodys(PageRequestDTO pageRequestDTO) {
		System.out.println(pageRequestDTO);
	}
	
	@Pointcut("execution( * com.tom.service..*.*(..))")
	public void servicePointcut(){}
	
	@Pointcut("execution( * com.tom.controller..*.*(..))")
	public void contorllerArgumentPointcut(){}
	
	@Pointcut("execution( * com.tom.controller..*.*(..)) && args(pageRequestDTO,..)")
	public void contorllerArgumentPointcuts(PageRequestDTO pageRequestDTO){}
	
}
