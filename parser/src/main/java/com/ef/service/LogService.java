package com.ef.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.LogDto;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public interface LogService {
	
	/**
	 * @param logDtoList
	 */
	LogDto mapToLogDto(String line);

	/**
	 * @param logDtoList
	 */
	void saveAll(List<LogDto> logDtoList);

	/**
	 * @param logDtoList
	 * @param threshold 
	 * @param duration 
	 * @param startDate 
	 */
	void checkRequest(List<LogDto> logDtoList, LocalDateTime startDate, Duration duration, long threshold);
}
