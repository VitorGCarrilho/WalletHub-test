package com.ef.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.LogDto;
import com.ef.service.LogService;
import com.ef.service.ParserService;
import com.ef.service.ThresholdService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImpl implements ParserService {
	
	/**
	 * The logger
	 * **/
	private static final Logger logger = LoggerFactory.getLogger(ParserService.class);

	/**
	 * The start date
	 **/
	protected LocalDateTime startDate;

	/**
	 * The duration
	 **/
	protected Duration duration;

	/**
	 * The threshold request
	 **/
	protected long threshold;

	/**
	 * The access log path
	 **/
	protected Path accessLog;

	/**
	 * log service
	 **/
	private LogService logService;
	
	/**
	 * threshold service
	 **/
	private ThresholdService thresholdService;

	/*
	 * @see com.ef.service.ParserService#parseFile(java.lang.String[])
	 */
	@Override
	public void parseFile(String[] args) {
		retrieveArguments(args);
		saveData();
	}

	/**
	 * The method that read the data from file and persist it.
	 **/
	protected void saveData() {
		ExecutorService executor = Executors.newFixedThreadPool(20);
		logger.info("Reading file");
		try (InputStream in = Files.newInputStream(accessLog);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

			String line;
			while ((line = reader.readLine()) != null) {
				final String log = line;
				
				executor.execute(() -> {
					LogDto logDto = logService.mapToLogDto(log);
					logService.save(logDto);
				});
			}

		} catch (IOException e) {
			RuntimeException runtime = new RuntimeException();
			runtime.addSuppressed(e);
			throw runtime;
		} finally {
			if (executor != null){
				executor.shutdown();
			}
		}
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			thresholdService.manageThresholdRequest(startDate, duration, threshold);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The method that retrieve the program arguments
	 **/
	protected void retrieveArguments(String[] args) {
		try {
			Map<String, String> argumentMap = argumentsToMap(args);
			startDate = LocalDateTime.parse(argumentMap.get(START_DATE),
					DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
			duration = Duration.valueOf(argumentMap.get(DURATION).toUpperCase());
			threshold = Long.parseLong(argumentMap.get(THRESHOLD));
			String uri = argumentMap.getOrDefault(ACCESS_LOG,
					System.getProperty("user.dir").concat("/").concat("access.log"));
			accessLog = Paths.get(uri);
		} catch (Exception e) {
			logger.error("Illegal arguments");
			IllegalArgumentException illegalArgument = new IllegalArgumentException("Illegal arguments: ");
			illegalArgument.addSuppressed(e);
			throw illegalArgument;
		}
	}

	/**
	 * The method that map the argument and the value
	 * 
	 * @param args
	 */
	protected Map<String, String> argumentsToMap(String[] args) {
		return Arrays.asList(args).parallelStream().map(argument -> argument.split(EQUALS_OPERATOR))
				.collect(Collectors.toMap(argument -> argument[0], argument -> argument[1]));

	}

	public ParserServiceImpl(LogService logService, ThresholdService thresholdService) {
		this.logService = logService;
		this.thresholdService = thresholdService;
	}

}
