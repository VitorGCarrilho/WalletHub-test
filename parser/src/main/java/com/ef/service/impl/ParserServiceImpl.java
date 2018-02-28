package com.ef.service.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.ef.service.ParserService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImpl implements ParserService{



	/* 
	 * @see com.ef.service.ParserService#parseFile(java.lang.String[])
	 */
	@Override
	public void parseFile(String[] args) {
		retrieveArguments(args);
		
	}

	/**
	 * @param args
	 */
	protected Map<String, String> retrieveArguments(String[] args) {
		return Arrays.asList(args)
					.stream()
					.map(argument -> argument.split(EQUALS_OPERATOR))
					.collect(Collectors.toMap(argument -> argument[0], argument -> argument[1]));
		
	}
	
	
}
