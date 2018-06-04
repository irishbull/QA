package ta.test.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.ReceiptPO;
import ta.pageobjects.impl.SignUpPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SignUpFormTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(SignUpFormTest.class);

  @Test
  @Description("Test sign up form submit")
  public void signUp() {

    SeleniumDriver.getInstance().getDriver()
        .get("http://www.kimschiller.com/page-object-pattern-tutorial/index.html");

    SignUpPO signUpPO = new SignUpPO();
    assertTrue(signUpPO.isInitialized());

    signUpPO.enterName("First", "Last");
    signUpPO.enterAddress("123 Street", "12345");

    ReceiptPO receiptPO = signUpPO.submit();
    assertTrue(receiptPO.isInitialized());

    assertEquals("Thank you!", receiptPO.confirmationHeader());
  }
}
