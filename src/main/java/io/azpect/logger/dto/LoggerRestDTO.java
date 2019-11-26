package io.azpect.logger.dto;

import org.springframework.stereotype.Component;

import io.azpect.logger.NoLogger;

@Component
@NoLogger
public class LoggerRestDTO {
	
	private String url;
	
	private String method;
	
	private String status;
	
	private LoggerRestRequestDTO request;
	
	private LoggerRestResponseDTO response;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public LoggerRestRequestDTO getRequest() {
		return request;
	}

	public void setRequest(LoggerRestRequestDTO request) {
		this.request = request;
	}

	public LoggerRestResponseDTO getResponse() {
		return response;
	}

	public void setResponse(LoggerRestResponseDTO response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
