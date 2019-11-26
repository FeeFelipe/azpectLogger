package io.azpect.logger.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoggerRestBody {
	
	@Autowired
	public ObjectMapper ObjectMapper;
	
	public JsonNode parse(String body) throws JsonProcessingException, IOException {
		return ObjectMapper.readTree(body);
	}
}
