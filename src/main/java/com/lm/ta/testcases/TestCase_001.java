package com.lm.ta.testcases;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lm.ta.config.WebInit;

public class TestCase_001 extends WebInit {
	
	private static final Logger logger = LoggerFactory.getLogger(TestCase_001.class);
	
	@Test
	public void Prerequisite() {
		logger.info("[" + this.getClass().getSimpleName() + "] - START");
		
		logger.info("\nLoading environment...");
		
		Init();
		
		logger.info("[" + this.getClass().getSimpleName() + "] - COMPLETE");
	}
}