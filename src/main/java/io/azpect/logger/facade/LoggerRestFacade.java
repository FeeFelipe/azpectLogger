package io.azpect.logger.facade;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.azpect.logger.dto.LoggerRestDTO;
import io.azpect.logger.dto.LoggerRestRequestDTO;
import io.azpect.logger.dto.LoggerRestResponseDTO;
import io.azpect.logger.rest.LoggerRestBody;
import io.azpect.logger.rest.LoggerRestHeader;

@Component
public class LoggerRestFacade {
	
	@Autowired
	private LoggerRestRequestDTO LoggerRestRequestDTO;
	
	@Autowired
	private LoggerRestResponseDTO LoggerRestResponseDTO;
	
	@Autowired
	private LoggerRestHeader LoggerRestHeader;
	
	@Autowired
	private LoggerRestBody LoggerRestBody;
	
	@Autowired
	private LoggerRestDTO LoggerRestDTO;
	
	@Autowired
	private ObjectMapper ObjectMapper;
	public void setRestRequest(HttpServletRequest request, String body) throws JsonProcessingException, IOException {
		LoggerRestDTO.setUrl(request.getRequestURL().toString());
		LoggerRestDTO.setMethod(request.getMethod());

		LoggerRestRequestDTO.setHeader(LoggerRestHeader.parse(request));
		LoggerRestRequestDTO.setBody(LoggerRestBody.parse(body));
		
		LoggerRestDTO.setRequest(LoggerRestRequestDTO);
	}
	
	public void setRestResponse(HttpServletResponse response, ResponseEntity<String> responseEntity) throws JsonProcessingException, IOException {
		LoggerRestHeader.setResponseTrace(response);

		LoggerRestResponseDTO.setHeader(LoggerRestHeader.parse(response));
		LoggerRestResponseDTO.setBody(LoggerRestBody.parse(responseEntity.getBody()));
		
		LoggerRestDTO.setResponse(LoggerRestResponseDTO);
	}
	
	public String toJson() throws JsonProcessingException {
		return ObjectMapper.writeValueAsString(LoggerRestDTO);
	}
	
}
