package com.ef.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ef.dto.BlockedIpDto;
import com.ef.repository.BlockedIpRepository;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author Vitor Carrilho - 03/03/2018
 *
 */
public class BlockedIpRepositoryImpl implements BlockedIpRepository {
	
	/**
	 * The logger
	 * **/
	private static final Logger logger = LoggerFactory.getLogger(BlockedIpRepositoryImpl.class);
	
	/**
	 * The datasource
	 * **/
	private DataSource dataSource;

	/*
	 * @see com.ef.repository.BlockedIpRepository#blockIp()
	 */
	@Override
	public void blockIp(BlockedIpDto blockedIpDto) {
		
		
		try (Connection connection = dataSource.getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT_STATEMENT)) {
			
			stmt.setString(1, blockedIpDto.getIp());
			stmt.setLong(2, blockedIpDto.getRequestNumber());
			stmt.setTimestamp(3, Timestamp.valueOf(blockedIpDto.getInitialDate()));
			stmt.setTimestamp(4, Timestamp.valueOf(blockedIpDto.getFinalDate()));
			stmt.execute();
			logger.info("Blocking ip {}", new Object[]{blockedIpDto.getIp()});
		} catch (MySQLIntegrityConstraintViolationException e) {
			logger.error("The ip {} is already blocked", blockedIpDto.getIp());
		} catch (Exception e) {
			RuntimeException runtime = new RuntimeException();
			runtime.addSuppressed(e);
			throw runtime;
		}
		
	}
	
	public BlockedIpRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
