package ta.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LogListener extends TestListenerAdapter {
  private static final Logger logger = LoggerFactory.getLogger("");

  /**
   * onFinish method
   *
   * @param testContext
   */
  @Override
  public void onFinish(ITestContext testContext) {
    logger.info("Total Passed =  {}, Total Failed = {}, Total Skipped = {}",
        getPassedTests().size(), getFailedTests().size(), getSkippedTests().size());

    super.onFinish(testContext);
  }


  /**
   * onTestStart method
   *
   * @param tr
   */
  @Override
  public void onTestStart(ITestResult tr) {
    logger.info("------------- Test '{}' -------------", tr.getName());
    logger.info("START -> {} , at {}", tr.getName(), tr.getStartMillis());

    super.onTestStart(tr);
  }


  /**
   * onTestSuccess method
   *
   * @param tr
   */
  @Override
  public void onTestSuccess(ITestResult tr) {
    logger.info("PASSED");
    logger.info("END -> {}, at {}", tr.getName(), tr.getEndMillis());

    super.onTestSuccess(tr);
  }
}
