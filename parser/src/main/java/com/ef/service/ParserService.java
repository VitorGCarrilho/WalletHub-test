package com.ef.service;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public interface ParserService {

	/**
	 * 
	 */
	public static final String EQUALS_OPERATOR = "=";
	
	public static final String START_DATE = "--startDate";

	public static final String DURATION = "--duration";

	public static final String THRESHOLD = "--threshold";
	
	/**
	 * Method that parse the file
	 * **/
	void parseFile(String [] args);
}
