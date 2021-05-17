package com.revature.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestDriver {

	static ScannerSingleton sc;
	private static final Logger logger = LogManager.getLogger(TestDriver.class);
	
	
	public static void main (String args[])
	{
		logger.trace("Trace");
		logger.debug("Debug");
		logger.info("Info");
		logger.warn("Warn");
		logger.error("Error");
		logger.fatal("Fatal");
	}
	
}
