package com.ef.repository;

import com.ef.dto.BlockedIpDto;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public interface BlockedIpRepository {
	void blockIp(BlockedIpDto blockedIpDto);
}
