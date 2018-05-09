package com.lm.ta.testrunners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

  private static final Logger logger = LoggerFactory.getLogger(Runner.class);

  public static void main(String[] args) {

    Class<?> c = null;

    for (String arg : args) {
      try {
        c = Class.forName(arg);
        logger.info("Starting test suite {}", c);

        Result result = JUnitCore.runClasses(c);

        for (Failure fail : result.getFailures()) {
          logger.info("Test failed: {}", fail.toString());
        }
        if (result.wasSuccessful()) {
          logger.info("All tests finished successfully...");
        }
      } catch (Exception e) {
        logger.error("Error running test suite {}", e.getMessage(), e);
      }
    }
  }
}
