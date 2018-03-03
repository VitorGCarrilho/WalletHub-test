package com.ef;

import java.io.IOException;

import com.ef.repository.BlockedIpRepository;
import com.ef.repository.LogRepository;
import com.ef.repository.impl.BlockedIpRepositoryImpl;
import com.ef.repository.impl.LogRepositoryImpl;
import com.ef.service.LogService;
import com.ef.service.ParserService;
import com.ef.service.ThresholdService;
import com.ef.service.impl.LogServiceImpl;
import com.ef.service.impl.ParserServiceImpl;
import com.ef.service.impl.ThresholdServiceImpl;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class Parser {
	public static void main(String[] args) throws IOException {
		LogRepository logRepoistory = new LogRepositoryImpl();
		LogService logService = new LogServiceImpl(logRepoistory);
		BlockedIpRepository blockedIpRepository = new BlockedIpRepositoryImpl();
		ThresholdService thresholdService = new ThresholdServiceImpl(blockedIpRepository, logRepoistory);
		ParserService parserService = new ParserServiceImpl(logService, thresholdService);
		parserService.parseFile(args);
	}
}
