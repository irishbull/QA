package ta.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LogListener extends TestListenerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LogListener.class);

    /**
     * onTestStart method
     */
    @Override
    public void onTestStart(ITestResult tr) {
        logger.info("{} - {} START", tr.getTestClass().getName(), tr.getName());
        super.onTestStart(tr);
    }


    /**
     * onTestSuccess method
     */
    @Override
    public void onTestSuccess(ITestResult tr) {
        logger.info("{} - {} PASSED", tr.getTestClass().getName(), tr.getName());
        super.onTestSuccess(tr);
    }


    /**
     * onTestFailure method
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        logger.error(tr.getThrowable().getMessage());
        logger.error("{} - {} FAILED", tr.getTestClass().getName(), tr.getName());
        super.onTestFailure(tr);
    }


    /**
     * onTestSkipped method
     */
    @Override
    public void onTestSkipped(ITestResult tr) {
        logger.warn("{} - {} SKIPPED", tr.getTestClass().getName(), tr.getName());
        super.onTestSkipped(tr);
    }
}
