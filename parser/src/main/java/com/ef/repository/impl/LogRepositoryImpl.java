package com.ef.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

	/* (non-Javadoc)
	 * @see com.ef.repository.LogRepository#save(com.ef.dto.LogDto)
	 */
	@Override
	public void save(LogDto logDto) {
		Connection connection = null;
		PreparedStatement stmt = null;
		DataSource dataSource = ConnectionPool.getDataSource();
		
		try{			
			connection = dataSource.getConnection();			
			stmt = connection.prepareStatement("INSERT INTO WH_LOG (REQUEST_DATE, IP, REQUEST, STATUS, AGENT) VALUES (?, ?, ?, ?, ?)");
			stmt.setTimestamp(1, Timestamp.valueOf(logDto.getDate()));
			stmt.setString(2, logDto.getIp());
			stmt.setString(3, logDto.getRequest());
			stmt.setLong(4, logDto.getStatus());
			stmt.setString(5, logDto.getAgent());
			stmt.execute();
			
		} catch (Exception e) {
			
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
