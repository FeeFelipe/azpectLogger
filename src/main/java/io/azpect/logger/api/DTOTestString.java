package io.azpect.logger.api;

import org.springframework.stereotype.Component;

import io.azpect.logger.NoLogger;

@Component
@NoLogger
public class DTOTestString {

	private String type;

	private String muNum;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMuNum() {
		return muNum;
	}

	public void setMuNum(String muNum) {
		this.muNum = muNum;
	}

}
