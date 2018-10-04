package ta.test.impl;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.PdpPO;
import ta.pageobjects.impl.ChangeStoreModalPO;

import ta.pageobjects.impl.StoreLocatorPO;
import ta.test.BaseTest;
import ta.utilities.CookiesUtils;
import ta.utilities.LocalStorage;
import ta.utilities.constants.Constants;

import static ta.utilities.constants.Constants.LocalStorage.ACCEPT;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE_VALUE;
import static ta.utilities.constants.Constants.LocalStorage.STORE_CONSENT;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class PdpTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PdpTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Change store")
    public void tc_001_changeStore(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        PdpPO pdpPO = new PdpPO();

        ChangeStoreModalPO modalPO = pdpPO.openChangeStoreModal();

        StoreLocatorPO storeLocatorPO = modalPO.changeStore();

        boolean isStoreLocationValid = storeLocatorPO.isStoreLocationValid(testData.get("storeLocation").toString());

        Assert.assertTrue(isStoreLocationValid, "Store location is valid");
        Assert.assertEquals(LocalStorage.getCurrentCustomerStore(), testData.get("storeId").toString(), "[LocalStorage] current customer store value");
        Assert.assertEquals(CookiesUtils.getCookieValue(Constants.Cookies.CurrentCustomerStore.NAME), testData.get("storeId").toString(), "[Cookie] current customer store value" );
    }

    @AfterTest
    public void afterTest(){
        // restore default current customer store cookie
        SeleniumDriver.getInstance().getDriver().get(BASE_URL);
        CookiesUtils.replaceCurrentCustomerStore();
        LocalStorage.removeItem(CURRENT_CUSTOMER_STORE);
        LocalStorage.setItem(CURRENT_CUSTOMER_STORE, CURRENT_CUSTOMER_STORE_VALUE);
        LocalStorage.removeItem(STORE_CONSENT);
        LocalStorage.setItem(STORE_CONSENT, ACCEPT);
    }
}
