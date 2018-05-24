package ta.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LogListener extends TestListenerAdapter {
  private static final Logger logger = LoggerFactory.getLogger("");

  /**
   * onTestStart method
   *
   * @param tr
   */
  @Override
  public void onTestStart(ITestResult tr) {
    logger.info("------------- Test '{}' -------------", tr.getName());
    logger.info("START -> {}", tr.getName());

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
    logger.info("END -> {}", tr.getName());

    super.onTestSuccess(tr);
  }


  /**
   * onTestFailure method
   *
   * @param tr
   */
  @Override
  public void onTestFailure(ITestResult tr) {
    logger.info("FAILED");
    logger.info("END  -> {}", tr.getName());

    super.onTestFailure(tr);
  }


  /**
   * onTestSkipped method
   *
   * @param tr
   */
  @Override
  public void onTestSkipped(ITestResult tr) {

    logger.info("SKIPPED");
    logger.info("END  -> {}, at {}", tr.getName());

    super.onTestSkipped(tr);
  }
}
