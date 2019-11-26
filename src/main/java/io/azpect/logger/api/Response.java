package io.azpect.logger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.azpect.logger.NoLogger;

@Component
public class Response {
	
	@Autowired
	private ObjectMapper ObjectMapper;
	
	private Object message;

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}
	
	public String show() throws JsonProcessingException {
		return ObjectMapper.writeValueAsString(this);
	}

}
