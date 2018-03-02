package com.ef.builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ef.dto.LogDto;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogDtoBuilder {
	
	private LocalDateTime date;
	
	private String ip;
	
	private String request;
	
	private int status;
	
	private String agent;

	public LogDtoBuilder setDate(String date) {
		this.date = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		return this;
	}

	public LogDtoBuilder setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public LogDtoBuilder setRequest(String request) {
		this.request = request;
		return this;
	}

	public LogDtoBuilder setStatus(String status) {
		this.status = Integer.parseInt(status);
		return this;
	}

	public LogDtoBuilder setAgent(String agent) {
		this.agent = agent;
		return this;
	}
	
	public LogDto build(){
		return new LogDto(date, ip, request, status, agent);
	}

}
