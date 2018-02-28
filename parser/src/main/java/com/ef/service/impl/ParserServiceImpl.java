package com.ef.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.ef.domain.enumeration.Duration;
import com.ef.service.ParserService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImpl implements ParserService{

	protected LocalDateTime startDate;
	
	protected Duration duration;
	
	protected long threshold;


	/* 
	 * @see com.ef.service.ParserService#parseFile(java.lang.String[])
	 */
	@Override
	public void parseFile(String[] args) {
		retrieveArguments(args);
	}

	protected void retrieveArguments(String[] args) {
		Map<String, String> argumentMap = argumentsToMap(args);
		startDate = LocalDateTime.parse(argumentMap.get(START_DATE), DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss"));
		duration = Duration.valueOf(argumentMap.get(DURATION).toUpperCase());
		threshold = Long.parseLong(argumentMap.get(THRESHOLD));
	}

	/**
	 * @param args
	 */
	protected Map<String, String> argumentsToMap(String[] args) {
		return Arrays.asList(args)
					.stream()
					.map(argument -> argument.split(EQUALS_OPERATOR))
					.collect(Collectors.toMap(argument -> argument[0], argument -> argument[1]));
		
	}
	
}
