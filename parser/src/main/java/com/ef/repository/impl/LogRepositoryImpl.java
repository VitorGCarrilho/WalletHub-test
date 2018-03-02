package com.ef.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.ef.dto.LogDto;
import com.ef.jdbc.ConnectionPool;
import com.ef.repository.LogRepository;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogRepositoryImpl implements LogRepository {

	/* 
	 * @see com.ef.repository.LogRepository#save(com.ef.dto.LogDto)
	 */
	@Override
	public void save(LogDto logDto) {
		DataSource dataSource = ConnectionPool.getDataSource();
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT_STATEMENT)) {
			
			stmt.setTimestamp(1, Timestamp.valueOf(logDto.getDate()));
			stmt.setString(2, logDto.getIp());
			stmt.setString(3, logDto.getRequest());
			stmt.setLong(4, logDto.getStatus());
			stmt.setString(5, logDto.getAgent());
			stmt.execute();

		} catch (Exception e) {
			RuntimeException runtime = new RuntimeException();
			runtime.addSuppressed(e);
			throw runtime;
		}

	}

}
