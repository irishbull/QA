package ta.testcases;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ta.config.WebInit;
import ta.pageobjects.ReceiptPage;
import ta.pageobjects.SignUpPage;
import ta.utilities.ReadPropertiesFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignUpFormTest extends WebInit {

  private static final Logger logger = LoggerFactory.getLogger(SignUpFormTest.class);

  @Test
  public void signUp() {

    String str = ReadPropertiesFile.getProperty("environment");
    logger.info("**** ENVIRONMENT {} ****", str);

    driver.get("http://www.kimschiller.com/page-object-pattern-tutorial/index.html");

    SignUpPage signUpPage = new SignUpPage(driver);
    assertTrue(signUpPage.isInitialized());

    signUpPage.enterName("First", "Last");
    signUpPage.enterAddress("123 Street", "12345");

    ReceiptPage receiptPage = signUpPage.submit();
    assertTrue(receiptPage.isInitialized());

    assertEquals("Thank you!", receiptPage.confirmationHeader());
  }
}
