package com.ef.service;

import java.time.LocalDateTime;

import com.ef.domain.enumeration.Duration;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public interface ThresholdService {
	
	/**
	 * @param threshold 
	 * @param duration 
	 * @param startDate 
	 */
	void manageThresholdRequest(LocalDateTime startDate, Duration duration, long threshold);

}
