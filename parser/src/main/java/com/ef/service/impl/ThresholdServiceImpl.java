package com.ef.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.BlockedIpDto;
import com.ef.repository.BlockedIpRepository;
import com.ef.repository.LogRepository;
import com.ef.service.ThresholdService;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public class ThresholdServiceImpl implements ThresholdService {
	
	/**
	 * Blocked ips repository
	 * **/
	private BlockedIpRepository blockedIpRepository;
	
	/**
	 * log repository
	 * **/
	private LogRepository logRepository;

	/*
	 * @see com.ef.service.ThresholdService#manageThresholdRequest(java.time.LocalDateTime, com.ef.domain.enumeration.Duration, long)
	 */
	@Override
	public void manageThresholdRequest(LocalDateTime startDate, Duration duration, long threshold) {
		List<BlockedIpDto> blockedIpList = logRepository.findThresholdRequest(startDate, duration, threshold);
		blockedIpList.parallelStream().forEach(blockedIpRepository::blockIp);
	}
	
	public ThresholdServiceImpl(BlockedIpRepository blockedIpRepository, LogRepository logRepository){
		this.blockedIpRepository = blockedIpRepository;
		this.logRepository = logRepository;
	}

}
