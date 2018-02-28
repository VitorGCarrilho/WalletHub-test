package com.ef;

import com.ef.service.ParserService;
import com.ef.service.impl.ParserServiceImpl;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public class Parser {
	public static void main(String[] args) {
		ParserService parserService = new ParserServiceImpl();
		parserService.parseFile(args);
	}
}
