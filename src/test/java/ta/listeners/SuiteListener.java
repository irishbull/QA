package ta.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

  private static final Logger logger = LoggerFactory.getLogger("");

  @Override
  public void onStart(ISuite suite) {
    logger.info("-------------------------- SUITE '{}' --------------------------",
        suite.getName());
  }

  @Override
  public void onFinish(ISuite suite) {
    logger.info("-------------------------- SUITE '{}' --------------------------",
        suite.getName());
  }
}
