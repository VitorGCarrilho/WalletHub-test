package com.ef.dto;

import java.time.LocalDateTime;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogDto {
	
	private LocalDateTime date;
	private String ip;
	private String request;
	private int status;
	private String agent;
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public LogDto ofDate(LocalDateTime date) {
		LogDto logDto = clone();
		logDto.date = date;
		return logDto;
	}
	
	public String getIp() {
		return ip;
	}
	
	public LogDto ofIp(String ip) {
		LogDto logDto = clone();
		logDto.ip = ip;
		return logDto;
	}
	
	public String getRequest() {
		return request;
	}
	
	public LogDto ofRequest(String request) {
		LogDto logDto = clone();
		logDto.request = request;
		return logDto;
	}
	
	public int getStatus() {
		return status;
	}
	
	public LogDto ofStatus(int status) {
		LogDto logDto = clone();
		logDto.status = status;
		return logDto;
	}
	
	public String getAgent() {
		return agent;
	}
	
	public LogDto ofAgent(String agent) {
		LogDto logDto = clone();
		logDto.agent = agent;
		return logDto;
	}
	
	/**
	 * @return a clone of the object
	 * **/
	public LogDto clone(){		
		return new LogDto(date, ip, request, status, agent);
	}
	
	/**
	 * @param date
	 * @param ip
	 * @param request
	 * @param status
	 * @param agent
	 */
	public LogDto(LocalDateTime date, String ip, String request, int status, String agent) {
		super();
		this.date = date;
		this.ip = ip;
		this.request = request;
		this.status = status;
		this.agent = agent;
	}
	
}
