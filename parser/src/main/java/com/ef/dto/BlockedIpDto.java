package com.ef.dto;

import java.time.LocalDateTime;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public class BlockedIpDto {
	private String ip;
	
	private long requestNumber;
	
	private LocalDateTime initialDate;
	
	private LocalDateTime finalDate;	

	public String getIp() {
		return ip;
	}

	public BlockedIpDto ofIp(String ip) {
		BlockedIpDto blocekdIpDto = clone();
		blocekdIpDto.ip = ip;
		return blocekdIpDto;
	}

	public long getRequestNumber() {
		return requestNumber;
	}

	public BlockedIpDto ofRequestNumber(long requestNumber) {
		BlockedIpDto blocekdIpDto = clone();
		blocekdIpDto.requestNumber = requestNumber;
		return blocekdIpDto;
	}

	public LocalDateTime getInitialDate() {
		return initialDate;
	}

	public BlockedIpDto ofInitialDate(LocalDateTime initialDate) {
		BlockedIpDto blocekdIpDto = clone();
		blocekdIpDto.initialDate = initialDate;
		return blocekdIpDto;
	}

	public LocalDateTime getFinalDate() {
		return finalDate;
	}

	public BlockedIpDto ofFinalDate(LocalDateTime finalDate) {
		BlockedIpDto blocekdIpDto = clone();
		blocekdIpDto.finalDate = finalDate;
		return blocekdIpDto;
	}

	/**
	 * @param ip
	 * @param requestNumber
	 * @param initialDate
	 * @param finalDate
	 */
	public BlockedIpDto(String ip, long requestNumber, LocalDateTime initialDate, LocalDateTime finalDate) {
		super();
		this.ip = ip;
		this.requestNumber = requestNumber;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
	}
	
	public BlockedIpDto clone(){
		return new BlockedIpDto(ip, requestNumber, initialDate, finalDate);
	}
}
