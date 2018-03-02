package com.ef.repository;

import com.ef.dto.LogDto;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public interface LogRepository {
	void save(LogDto logDto);
}
