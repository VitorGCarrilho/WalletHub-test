package com.ef.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mock;

import com.ef.domain.enumeration.Duration;
import com.ef.service.ParserService;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class ParserServiceImplTest {
	
	private static final String [] ARGUMENTS = new String [] {"--startDate=2017-01-01.13:00:00","--duration=daily","--threshold=250"};
	
	@Mock
	private LogServiceImpl logService;
	
	private ParserServiceImpl parserService = new ParserServiceImpl(logService);
	
	@Test
	public void retrieveArguments() {
		parserService.retrieveArguments(ARGUMENTS);
		LocalDate date = LocalDate.of(2017, Month.JANUARY, 1);
		LocalTime time = LocalTime.of(13, 00);
		assertEquals(LocalDateTime.of(date, time), parserService.startDate);
		assertEquals(Duration.DAILY, parserService.duration);
		assertEquals(250L, parserService.threshold);
	}
	
	@Test
	public void argumentsToMapTest() {
		Map<String, String> argumentsMap = parserService.argumentsToMap(ARGUMENTS);

		assertEquals("2017-01-01.13:00:00", argumentsMap.get(ParserService.START_DATE));
		assertEquals("daily", argumentsMap.get(ParserService.DURATION));
		assertEquals("250", argumentsMap.get(ParserService.THRESHOLD));
	}
}
