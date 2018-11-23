package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.StoreLocatorPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class StoreLocatorTest extends BaseTest {

    @Test
    public void openPopup() {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

        StoreLocatorPO storeLocatorPO = new StoreLocatorPO();

        BrowserUtils.hover(storeLocatorPO.getStoreLocatorDiv());

        BrowserUtils.waitFor(storeLocatorPO.getStoreLocatorPopup(), Constants.WaitTime.EXPLICIT_WAIT);

        BrowserUtils.hover(storeLocatorPO.getStoreLocatorPopup());

        BrowserUtils.hover(storeLocatorPO.getSeeOnMapLink());

        storeLocatorPO.getSeeOnMapLink().click();
    }
}
