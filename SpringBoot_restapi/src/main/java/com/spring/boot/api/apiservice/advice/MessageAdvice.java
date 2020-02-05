package com.spring.boot.api.apiservice.advice;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MessageAdvice {
	private static Logger logger = LogManager.getLogger(MessageAdvice.class);
	
	@Before("execution(* com.spring.boot.api.apiservice.service.RestApiService*.*(..))")
	public void startLog(JoinPoint jp) {
		logger.info("Method: " + jp.getSignature());
		logger.info("Params: " + Arrays.toString(jp.getArgs()));
	}
}
