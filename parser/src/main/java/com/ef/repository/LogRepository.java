package com.ef.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.BlockedIpDto;
import com.ef.dto.LogDto;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public interface LogRepository {
	
	/**
	 * The log insert statement
	 */
	public static final String INSERT_STATEMENT = "INSERT INTO WH_LOG (REQUEST_DATE, IP, REQUEST, STATUS, AGENT) VALUES (?, ?, ?, ?, ?)";

	/**
	 * The method that saves a given log
	 * @param logDto
	 * **/
	void save(LogDto logDto);
	
	/**
	 * The method that find the ips that make more request than the allowed
	 * **/
	List<BlockedIpDto> findThresholdRequest(LocalDateTime startDate, Duration duration, long threshold);
}
