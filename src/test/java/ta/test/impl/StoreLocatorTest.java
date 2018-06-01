package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.StoreLocatorPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

public class StoreLocatorTest extends BaseTest {

    @Test
    public void openPopup() throws Exception {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(ReadPropertiesFile.getProperty("base.url"));

        StoreLocatorPO storeLocatorPO = new StoreLocatorPO();

        BrowserUtils.hover(storeLocatorPO.getStoreLocatorDiv());

        BrowserUtils.waitFor(storeLocatorPO.getStoreLocatorPopup(), Constants.WaitTime.EXPLICT_WAIT);

        BrowserUtils.hover(storeLocatorPO.getStoreLocatorPopup());

        BrowserUtils.hover(storeLocatorPO.getSeeOnMapLink());

        storeLocatorPO.getSeeOnMapLink().click();
    }
}
