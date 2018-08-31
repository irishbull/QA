package ta.test.impl.tooso.analytics;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.qameta.allure.Description;

import ta.driver.SeleniumDriver;
import ta.test.ToosoBaseTest;


public class ToosoClickAfterSearch extends ToosoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ToosoClickAfterSearch.class);

    @Test
    @Description("GET [type = CLICK ON SUGGESTED] - validate request")
    public void tc_001_verifyClickOnSuggestRequest() throws Exception {


        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get("https://www-react-qa.leroymerlin.it/");

        WebStorage webStorage = (WebStorage) driver;
        LocalStorage localStorage = webStorage.getLocalStorage();
        for(String key : localStorage.keySet()) {
            logger.info("key [{}] : value [{}]", key, localStorage.getItem(key));
        }


       /*

        LocalStorage.setItemInLocalStorage("storeConsent", "true");
        LocalStorage.setItemInLocalStorage("currentCustomerStore", "7");


        Cookie cookie = new Cookie.Builder("currentCustomerStore", "7")
                .domain("www-react-qa.leroymerlin.it")
                .expiresOn(new GregorianCalendar(2030, Calendar.JANUARY, 1).getTime())
                .path("/")
                .build();

        driver.manage().addCookie(cookie);

*/
        Thread.sleep(20_000);


    }


}
