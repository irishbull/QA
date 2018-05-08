package com.lm.ta.testrunners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import com.lm.ta.testsuites.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {
	
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(TestSuite.class);
		for (Failure fail : result.getFailures()) {
			logger.info("Test failed: {}",fail.toString());
		}
		if (result.wasSuccessful()) {
			logger.info("All tests finished successfully...");
		}
	}
}