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
	 * The select statement that returns the ip that make more requests than the threshold
	 */
	public static final String FIND_IP_TO_BLOCK = "SELECT IP, COUNT(IP) as REQUISITIONS FROM WH_LOG WHERE (REQUEST_DATE BETWEEN ? AND ?) GROUP BY IP HAVING REQUISITIONS > ?";

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
