package io.azpect.logger;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.azpect.logger.facade.LoggerRestFacade;

@Aspect
@Component
public class AspectTest {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = false)
	private HttpServletRequest HttpServletRequest;
	
	@Autowired(required = false)
	private HttpServletResponse HttpServletResponse;
	
	@Autowired
	private LoggerRestFacade LoggerRestFacade;

	@Pointcut("within(io.azpect.logger..*) && !within(@io.azpect.logger.NoLogger *)")
	protected void azpectMethods() {}
	
	@Pointcut("within(@org.springframework.web.bind.annotation..* *)")
	protected void springWebAnnotation() {}
	
//	@Pointcut("within(@org.springframework.stereotype..* *")
//	protected void springWebAnnotation() {}
	
	@Pointcut("execution(* *.*(..))")
	protected void allMethods() {}

	@Before("springWebAnnotation()")
	public void teste(JoinPoint joinPoint) throws JsonProcessingException, IOException {

//		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
//		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//		log.info("Target class : " + joinPoint.getTarget().getClass().getName());
		
		if (HttpServletRequest != null) {
			LoggerRestFacade.setRestRequest(HttpServletRequest, Arrays.toString(joinPoint.getArgs()));
			log.info("Message : " + LoggerRestFacade.toJson());
		}
	}
	
	@AfterReturning(pointcut = "springWebAnnotation()", returning = "result")
	public void returnResult(JoinPoint joinPoint, ResponseEntity<String> result) throws IOException {
		
//		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
//		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//		log.info("Target class : " + joinPoint.getTarget().getClass().getName());
		
		if (HttpServletResponse != null) {
			LoggerRestFacade.setRestRequest(HttpServletRequest, Arrays.toString(joinPoint.getArgs()));
			LoggerRestFacade.setRestResponse(HttpServletResponse, result);
			log.info("Message : " + LoggerRestFacade.toJson());
		}
	}
	
	@AfterThrowing(pointcut = "springWebAnnotation() && allMethods()", throwing = "error")
	public void throwError(JoinPoint joinPoint, Throwable error) {
		log.error("Error " + error.getMessage() + Arrays.toString(error.getStackTrace()));
	}

//
//	@Before("io.azpectMethods() && args(..,request)")
//	public void logBeforeRequest(JoinPoint joinPoint, HttpEntityMethodProcessor request) {
//		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
//		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//		log.info("Target class : " + joinPoint.getTarget().getClass().getName());
//		
//	}
//	
//	@Before("io.azpectMethods() && args(..,response)")
//	public void logBeforeRespose(JoinPoint joinPoint, HttpServletResponse response) {
//		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
//		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//		log.info("Target class : " + joinPoint.getTarget().getClass().getName());
//		
//		if (null != request) {
//			log.debug("Start Header Section of request ");
//			log.debug("Method Type : " + request.getMethod());
//			Enumeration<String> headerNames = request.getHeaderNames();
//			while (headerNames.hasMoreElements()) {
//				String headerName = (String) headerNames.nextElement();
//				String headerValue = request.getHeader(headerName);
//				log.debug("Header Name: " + headerName + " Header Value : " + headerValue);
//			}
//			log.debug("Request Path info :" + request.getServletPath());
//			log.debug("End Header Section of request ");
//		}
//	}
	
}
