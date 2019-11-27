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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;

import feign.Client;
import feign.Request;
import feign.RequestTemplate;
import feign.Response;
import io.azpect.logger.facade.LoggerRestFacade;

@Profile("!test")
@Aspect
@Configuration
public class AspectTest {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required = false)
	private HttpServletRequest HttpServletRequest;
	
	@Autowired(required = false)
	private HttpServletResponse HttpServletResponse;
	
	@Autowired(required = false)
	private Client Client;
	
	@Autowired(required = false)
	private Request Request;
	
	@Autowired(required = false)
	private Response Response;
	
	@Autowired(required = false)
	private RequestTemplate RequestTemplate;
	
	@Autowired
	private LoggerRestFacade LoggerRestFacade;

	@Pointcut("within(io.azpect.logger..*) && !within(@io.azpect.logger.NoLogger *)")
	protected void azpectMethods() {}
	
	@Pointcut("within(@org.springframework.web.bind.annotation..* *)")
	protected void springWebAnnotation() {}
	
//	@Pointcut("execution(* feign.*(..))")
//	@Pointcut("execution(* org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient.*(..))")
//	protected void openfeignAnnotation() {}
	
	@Pointcut("execution(* *.*(..))")
	protected void allMethods() {}

	@Before("springWebAnnotation()")
	public void beforeSpringWebAnnotation(JoinPoint joinPoint) throws JsonProcessingException, IOException {
		if (HttpServletRequest != null) {
			LoggerRestFacade.setRestRequest(HttpServletRequest, Arrays.toString(joinPoint.getArgs()));
			log.info("Message : " + LoggerRestFacade.toJson());
		}
	}
	
	@AfterReturning(pointcut = "springWebAnnotation()", returning = "result")
	public void resultSpringWebAnnotation(JoinPoint joinPoint, ResponseEntity<String> result) throws IOException {
		if (HttpServletResponse != null) {
			LoggerRestFacade.setRestRequest(HttpServletRequest, Arrays.toString(joinPoint.getArgs()));
			LoggerRestFacade.setRestResponse(HttpServletResponse, result);
			log.info("Message : " + LoggerRestFacade.toJson());
		}
	}
	
//	@Before("openfeignAnnotation()")
//	public void resultOpenfeignAnnotation(JoinPoint joinPoint) throws IOException {
//		
//		log.info("Entering in Method :  " + joinPoint.getSignature().getName());
//		log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
//		log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
//		log.info("Target class : " + joinPoint.getTarget().getClass().getName());
//		
//		
//	}
	
	@AfterThrowing(pointcut = "springWebAnnotation() && allMethods()", throwing = "error")
	public void throwError(JoinPoint joinPoint, Throwable error) {
		log.error("Error " + error.getMessage() + Arrays.toString(error.getStackTrace()));
	}
	
}
