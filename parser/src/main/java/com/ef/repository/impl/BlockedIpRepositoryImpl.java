package com.ef.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import com.ef.dto.BlockedIpDto;
import com.ef.jdbc.ConnectionPool;
import com.ef.repository.BlockedIpRepository;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public class BlockedIpRepositoryImpl implements BlockedIpRepository {

	/*
	 * @see com.ef.repository.BlockedIpRepository#blockIp()
	 */
	@Override
	public void blockIp(BlockedIpDto blockedIpDto) {
		DataSource dataSource = ConnectionPool.getDataSource();

		try (Connection connection = dataSource.getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT_STATEMENT)) {

			stmt.setString(1, blockedIpDto.getIp());
			stmt.setLong(2, blockedIpDto.getRequestNumber());
			stmt.setTimestamp(3, Timestamp.valueOf(blockedIpDto.getInitialDate()));
			stmt.setTimestamp(4, Timestamp.valueOf(blockedIpDto.getFinalDate()));
			stmt.execute();

		} catch (Exception e) {
			RuntimeException runtime = new RuntimeException();
			runtime.addSuppressed(e);
			throw runtime;
		}
		
	}

}
