package com.ef.repository;

import com.ef.dto.BlockedIpDto;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public interface BlockedIpRepository {
	
	/**
	 * The block insert statement
	 */
	public static final String INSERT_STATEMENT = "INSERT INTO WH_BLOCKED_IP (IP, REQUEST_NUMBER, INITIAL_DATE, FINAL_DATE) VALUES (?, ?, ?, ?)";
	
	/**
	 * The method that block an given ip
	 */
	void blockIp(BlockedIpDto blockedIpDto);
}
