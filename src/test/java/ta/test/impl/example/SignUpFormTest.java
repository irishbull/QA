package ta.test.impl.example;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.example.ReceiptPO;
import ta.pageobjects.impl.example.SignUpPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class SignUpFormTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SignUpFormTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class, testName = "DataProviderExample.json")
    @Description("Test sign up form submit")
    public void tc001_signUpFormTest(JSONObject testData) {

        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));
        logger.info(testData.get("description").toString());
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
