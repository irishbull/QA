package ta.test.impl.tooso.analytics;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.test.ToosoBaseTest;
import ta.utilities.CookiesUtils;
import ta.utilities.LocalStorage;
import ta.utilities.constants.Constants;

import static ta.utilities.constants.Constants.Url.BASE_URL;


public class ToosoClickAfterSearch extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoClickAfterSearch.class);

    @Test
    @Description("GET [type = CLICK ON SUGGESTED] - validate request")
    public void tc_001_verifyClickOnSuggestRequest() throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

        logger.info("-----------------------------------------------------------------------");

        logger.info("CURRENT CUSTOMER STORE {}", CookiesUtils.getCookieValue(Constants.Cookies.CurrentCustomerStore.VALUE));

        for( int i=0; i< Math.toIntExact(LocalStorage.getLocalStorageLength()); i++) {
            logger.info("localStorage[{}] = {}", LocalStorage.getKey(i), LocalStorage.getItem(LocalStorage.getKey(i)));
        }

    }
}
