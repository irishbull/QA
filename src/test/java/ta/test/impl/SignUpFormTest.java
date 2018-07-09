package ta.test.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.ReceiptPO;
import ta.pageobjects.impl.SignUpPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SignUpFormTest extends BaseTest {

  private static final String DATA_FILE = "src/test/java/ta/test/json/SignUpFormTest.json";
  private static final Logger logger = LoggerFactory.getLogger(SignUpFormTest.class);

  @BeforeClass(alwaysRun = true, enabled = true)
  protected void testClassSetup() throws Exception {

    // set datafile for data provider
    JSONDataProvider.dataFile=DATA_FILE;
  }

  @Test(dataProvider="fetchData_JSON", dataProviderClass=JSONDataProvider.class)
  @Description("Test sign up form submit")
  public void tc001_signUpFormTest(String rowID,
                     String description,
                     JSONObject testData) {

    logger.info(String.valueOf(Thread.currentThread().getId()));
    logger.info(description);
    SeleniumDriver.getInstance().getDriver()
        .get("http://www.kimschiller.com/page-object-pattern-tutorial/index.html");

    SignUpPO signUpPO = new SignUpPO();
    assertTrue(signUpPO.isInitialized());

    signUpPO.enterName(testData.get("first-name").toString(), testData.get("last-name").toString());
    signUpPO.enterAddress(testData.get("address").toString(), testData.get("zip-code").toString());

    ReceiptPO receiptPO = signUpPO.submit();
    assertTrue(receiptPO.isInitialized());

    assertEquals("Thank you!", receiptPO.confirmationHeader());
  }
}
