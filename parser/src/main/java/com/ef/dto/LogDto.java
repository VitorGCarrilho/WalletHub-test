package com.ef.dto;

import java.time.LocalDateTime;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogDto {
	
	public LocalDateTime date;
	public String ip;
	public String request;
	public int status;
	public String agent;
	
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
	
	public LogDto clone(){
		LogDto logDto = new LogDto();
		logDto.agent = this.agent;
		logDto.date = this.date;
		logDto.ip = this.ip;
		logDto.request = this.request;
		logDto.status = this.status;	
		
		return logDto;
	}
}
