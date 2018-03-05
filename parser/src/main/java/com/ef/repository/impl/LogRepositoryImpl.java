package com.ef.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.BlockedIpDto;
import com.ef.dto.LogDto;
import com.ef.repository.LogRepository;

/**
 * @author Vitor Carrilho - 01/03/2018
 *
 */
public class LogRepositoryImpl implements LogRepository {
	
	/**
	 * Data source
	 * **/
	private DataSource dataSource;

	/*
	 * @see com.ef.repository.LogRepository#save(com.ef.dto.LogDto)
	 */
	@Override
	public void save(LogDto logDto) {

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

	/*
	 * @see com.ef.repository.LogRepository#findThresholdRequest(java.time.
	 * LocalDateTime, com.ef.domain.enumeration.Duration, long)
	 */
	@Override
	public List<BlockedIpDto> findThresholdRequest(LocalDateTime startDate, Duration duration, long threshold) {
		List<BlockedIpDto> blockedIpDtoList = new ArrayList<>();
		LocalDateTime finalDate = startDate.plus(1, duration.getTimeUnit());

		try (Connection connection = dataSource.getConnection();
				PreparedStatement stmt = connection.prepareStatement(FIND_IP_TO_BLOCK)) {

			stmt.setTimestamp(1, Timestamp.valueOf(startDate));
			stmt.setTimestamp(2, Timestamp.valueOf(finalDate));
			stmt.setLong(3, threshold);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String ip = rs.getString("IP");
				long requestNumber = rs.getLong("REQUISITIONS");
				BlockedIpDto blockedIpDto = new BlockedIpDto(ip, requestNumber, startDate, finalDate);
				blockedIpDtoList.add(blockedIpDto);

			}

		} catch (Exception e) {
			RuntimeException runtime = new RuntimeException();
			runtime.addSuppressed(e);
			throw runtime;
		}

		return blockedIpDtoList;
	}

	public LogRepositoryImpl(DataSource dataSource){
		this.dataSource = dataSource;
	}
}
