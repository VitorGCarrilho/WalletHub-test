package com.ef.domain.enumeration;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * @author Vitor Carrilho - 27/02/2018
 *
 */
public enum Duration {
	HOURLY(ChronoUnit.HOURS), DAILY(ChronoUnit.DAYS);
	
	private TemporalUnit temporalUnit;
	
	private Duration(TemporalUnit temporalUnit){
		this.temporalUnit = temporalUnit;
	}
	
	public TemporalUnit getTimeUnit(){
		return temporalUnit;
	}
}
