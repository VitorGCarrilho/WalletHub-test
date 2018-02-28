package com.ef.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.ef.service.ParserService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImplTest {
	
	private static final String [] ARGUMENTS = new String [] {"--startDate=2017-01-01.13:00:00","--duration=daily","--threshold=250"};
	
	private ParserServiceImpl parserService = new ParserServiceImpl();
	
	
	@Test
	public void retrieveArgumentsTest() {
		Map<String, String> argumentsMap = parserService.retrieveArguments(ARGUMENTS);

		assertEquals("2017-01-01.13:00:00", argumentsMap.get(ParserService.START_DATE));
		assertEquals("daily", argumentsMap.get(ParserService.DURATION));
		assertEquals("250", argumentsMap.get(ParserService.THRESHOLD));
	}

}
