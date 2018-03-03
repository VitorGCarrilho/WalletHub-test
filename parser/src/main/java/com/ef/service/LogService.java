package com.ef.service;

import java.util.List;

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
	 * @param logDto
	 */
	void save(LogDto logDto);
}
