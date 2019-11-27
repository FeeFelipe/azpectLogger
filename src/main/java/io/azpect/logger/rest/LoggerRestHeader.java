package io.azpect.logger.rest;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoggerRestHeader {
	
	@Value("${io.azpectLogger.correlation-id:#{null}}")
	private String correlationId;
	
	@Value("${io.azpectLogger.trace-id:#{null}}")
	private String traceId;
	
	@Value("${io.azpectLogger.parent-id:#{null}}")
	private String parentId;
	
	@Autowired
	public ObjectMapper ObjectMapper;
	
	public JsonNode parse(HttpServletRequest request) throws JsonProcessingException, IOException {
		Map<String, Object> map = new HashMap<>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			map.put(headerName, headerValue);
		}
		
		String headerJsonString = ObjectMapper.writeValueAsString(map);
		
		return ObjectMapper.readTree(headerJsonString);
	}
	
	public JsonNode parse(HttpServletResponse response) throws JsonProcessingException, IOException {
		Map<String, Object> map = new HashMap<>();
		Collection<String> headerNames = response.getHeaderNames();
		Iterator<String> iterator = headerNames.iterator();
		while (iterator.hasNext()) {
			String headerName = iterator.next();
			String headerValue = response.getHeader(headerName);
			map.put(headerName, headerValue);
		}
		
		String headerJsonString = ObjectMapper.writeValueAsString(map);
		
		return ObjectMapper.readTree(headerJsonString);
	}
	
	public void setRequestTrace(HttpServletRequest request) {
		if (correlationId != null) {
			if (request.getHeader(correlationId) != null)
				MDC.put(correlationId, request.getHeader(correlationId));
			else
				MDC.put(correlationId, UUID.randomUUID().toString());
		}
		
		if (traceId != null)  {
			MDC.put(parentId, request.getHeader(traceId));
			MDC.put(traceId, UUID.randomUUID().toString());
		}
	}
	
	public void setResponseTrace(HttpServletResponse response) {
		if (correlationId != null)
			response.setHeader(correlationId, MDC.get(correlationId));
		
		if (traceId != null)
			response.addHeader(traceId, MDC.get(traceId));
		
		if (parentId != null)
			response.addHeader(parentId, MDC.get(parentId));
	}
}
