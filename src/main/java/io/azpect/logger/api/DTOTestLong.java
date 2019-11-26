package io.azpect.logger.api;

import org.springframework.stereotype.Component;

import io.azpect.logger.NoLogger;

@Component
@NoLogger
public class DTOTestLong {
	
	private String type;
	
	private double muNum;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getMuNum() {
		return muNum;
	}

	public void setMuNum(double d) {
		this.muNum = d;
	}

}
