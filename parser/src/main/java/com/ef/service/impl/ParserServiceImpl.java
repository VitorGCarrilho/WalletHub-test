package com.ef.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ef.domain.enumeration.Duration;
import com.ef.dto.LogDto;
import com.ef.service.LogService;
import com.ef.service.ParserService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImpl implements ParserService {

	protected LocalDateTime startDate;

	protected Duration duration;

	protected long threshold;

	protected Path accessLog;

	private LogService logService;

	/*
	 * @see com.ef.service.ParserService#parseFile(java.lang.String[])
	 */
	@Override
	public void parseFile(String[] args) {
		retrieveArguments(args);
		List<LogDto> logDtoList = retrieveData();
		logService.saveAll(logDtoList);
		logService.checkRequest(logDtoList, startDate, duration, threshold);
	}

	protected List<LogDto> retrieveData() {
		List<String> contents = null;
		try {
			contents = Files.readAllLines(accessLog);
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error when trying to read file");
		}
		return contents.parallelStream().map(logService::mapToLogDto).collect(Collectors.toList());
	}

	protected void retrieveArguments(String[] args) {
		Map<String, String> argumentMap = argumentsToMap(args);
		startDate = LocalDateTime.parse(argumentMap.get(START_DATE),
				DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
		duration = Duration.valueOf(argumentMap.get(DURATION).toUpperCase());
		threshold = Long.parseLong(argumentMap.get(THRESHOLD));
		String uri = argumentMap.getOrDefault(ACCESS_LOG,
				System.getProperty("user.dir").concat("/").concat("access.log"));
		accessLog = Paths.get(uri);
	}

	/**
	 * @param args
	 */
	protected Map<String, String> argumentsToMap(String[] args) {
		return Arrays.asList(args).parallelStream().map(argument -> argument.split(EQUALS_OPERATOR))
				.collect(Collectors.toMap(argument -> argument[0], argument -> argument[1]));

	}
	
	public ParserServiceImpl(LogService logService){
		this.logService = logService;
	}

}
