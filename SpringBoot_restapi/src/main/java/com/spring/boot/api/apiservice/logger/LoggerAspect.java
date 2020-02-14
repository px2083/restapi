package com.spring.boot.api.apiservice.logger;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
	private static Logger logger = LogManager.getLogger(LoggerAspect.class);
	
	@Before("execution(* com.spring.boot.api.apiservice..*(..))")
	public void startLog(JoinPoint jp) throws Exception {
		logger.info("Method: " + jp.getSignature());
		logger.info("Params: " + Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* com.spring.boot.api.apiservice.service..*(..))")
	public Object sqlLog(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n", joinPoint.getTarget().getClass().getSimpleName(), sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start));
            logger.info(joinPoint.getTarget().getClass().getSimpleName());
            logger.info(sig.getName());
            logger.info(Arrays.toString(joinPoint.getArgs()));
        }
    }

}
