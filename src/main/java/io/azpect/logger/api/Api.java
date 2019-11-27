package io.azpect.logger.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.azpect.logger.feign.JSONPlaceHolderClient;
import io.azpect.logger.feign.Post;

@RestController
public class Api {
	
	@Autowired
	private Response Response;
	
	@Autowired
	private DTOTestString DTOTestString;
	
	@Autowired
	private DTOTestLong DTOTestLong;
	
	@Autowired
	private JSONPlaceHolderClient JSONPlaceHolderClient;

	@GetMapping("greeting/{id}")
    public String greeting(@PathVariable String id) {
	
    	
        return " Valor registrado.";
    }
	
	@PostMapping("/teste/long")
	public ResponseEntity<String> addtesteLong(@RequestBody String newEmployee) throws JsonProcessingException {
		DTOTestLong.setType("Texto");
		DTOTestLong.setMuNum(3123.321);
		Response.setMessage(DTOTestLong);
		
		List<Post> p = JSONPlaceHolderClient.getPosts();
		return new ResponseEntity<String>(Response.show(), HttpStatus.OK);
	}
	
	@GetMapping("/teste/long")
	public ResponseEntity<String> testeLong() throws JsonProcessingException {
		DTOTestLong.setType("Texto");
		DTOTestLong.setMuNum(3123.321);
		Response.setMessage(DTOTestLong);
		return new ResponseEntity<String>(Response.show(), HttpStatus.OK);
	}

	@RequestMapping("/teste/string")
	public ResponseEntity<String> testeString() throws JsonProcessingException {
		DTOTestString.setType("Texto");
		DTOTestString.setMuNum("31u793821739812");
		Response.setMessage(DTOTestString);
		return new ResponseEntity<String>(Response.show(), HttpStatus.OK);
	}

	@RequestMapping("/ping")
	public ResponseEntity<String> ping() throws JsonProcessingException {
		Response.setMessage("ok");
		return new ResponseEntity<String>(Response.show(), HttpStatus.OK);
	}
}