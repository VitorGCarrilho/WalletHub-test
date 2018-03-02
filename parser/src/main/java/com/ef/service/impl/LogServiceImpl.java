package com.ef.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ef.builder.LogDtoBuilder;
import com.ef.domain.enumeration.Duration;
import com.ef.dto.LogDto;
import com.ef.repository.LogRepository;
import com.ef.service.LogService;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogServiceImpl implements LogService {

	private LogRepository logRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ef.service.LogService#mapToLogDto(java.lang.String)
	 */
	@Override
	public LogDto mapToLogDto(String line) {
		String[] element = line.split("\\|");
		return new LogDtoBuilder()
						.setDate(element[0])
						.setIp(element[1])
						.setRequest(element[2])
						.setStatus(element[3])
						.setAgent(element[4])
						.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ef.service.LogService#saveAll(java.util.List)
	 */
	@Override
	public void saveAll(List<LogDto> logDtoList) {
		logDtoList.parallelStream().forEach(logRepository::save);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ef.service.LogService#checkRequest(java.util.List,
	 * java.time.LocalDateTime, com.ef.domain.enumeration.Duration, long)
	 */
	@Override
	public void checkRequest(List<LogDto> logDtoList, LocalDateTime startDate, Duration duration, long threshold) {
		// TODO Auto-generated method stub

	}

	public LogServiceImpl(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	/* (non-Javadoc)
	 * @see com.ef.service.LogService#save(com.ef.dto.LogDto)
	 */
	@Override
	public void save(LogDto logDto) {
		logRepository.save(logDto);		
	}
}
