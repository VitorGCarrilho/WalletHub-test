package com.ef;

import java.io.IOException;

import com.ef.repository.LogRepository;
import com.ef.repository.impl.LogRepositoryImpl;
import com.ef.service.LogService;
import com.ef.service.ParserService;
import com.ef.service.impl.LogServiceImpl;
import com.ef.service.impl.ParserServiceImpl;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class Parser {
	public static void main(String[] args) throws IOException {
		LogRepository logRepoistory = new LogRepositoryImpl();
		LogService logService = new LogServiceImpl(logRepoistory);		
		ParserService parserService = new ParserServiceImpl(logService);
		parserService.parseFile(args);
	}
}
