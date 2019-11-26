package io.azpect.logger.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;

import io.azpect.logger.NoLogger;

@Component
@NoLogger
public class LoggerRestResponseDTO {
	
	private JsonNode header;
	
	private JsonNode body;

	public JsonNode getHeader() {
		return header;
	}

	public void setHeader(JsonNode header) {
		this.header = header;
	}

	public JsonNode getBody() {
		return body;
	}

	public void setBody(JsonNode body) {
		this.body = body;
	}
	
}
