package ta.test.impl;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.PdpPO;
import ta.pageobjects.impl.AddToCartOverlayPO;
import ta.pageobjects.impl.SelectStoreModalPO;
import ta.pageobjects.impl.StoreLocatorPO;
import ta.test.BaseTest;
import ta.utilities.CookiesUtils;
import ta.utilities.LocalStorage;
import ta.utilities.SessionStorage;
import ta.utilities.constants.Constants;

import static ta.utilities.constants.Constants.LocalStorage.ACCEPT;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE_VALUE;
import static ta.utilities.constants.Constants.LocalStorage.STORE_CONSENT;
import static ta.utilities.constants.Constants.SessionStorage.CART_CODE;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class PdpTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PdpTest.class);

    @AfterClass
    public void resetStoreAndCart() {
        SeleniumDriver.getInstance().getDriver().get(BASE_URL);
        CookiesUtils.replaceCurrentCustomerStore();
        LocalStorage.removeItem(CURRENT_CUSTOMER_STORE);
        LocalStorage.setItem(CURRENT_CUSTOMER_STORE, CURRENT_CUSTOMER_STORE_VALUE);
        LocalStorage.removeItem(STORE_CONSENT);
        LocalStorage.setItem(STORE_CONSENT, ACCEPT);
        SessionStorage.removeItem(CART_CODE);
    }


    @Test(priority = 1, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Add product to cart")
    public void tc_001_addToCart(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        PdpPO pdpPO = new PdpPO();

        // increment quantity
        pdpPO.incrementQuantity(Integer.valueOf(testData.get("incrementQt").toString()));
        // decrement quantity
        pdpPO.decrementQuantity(Integer.valueOf(testData.get("decrementQt").toString()));

        AddToCartOverlayPO addToCartOverlayPO = pdpPO.addToCart();

        Assert.assertEquals(addToCartOverlayPO.getCartLabelText(), testData.get("expectedCartLabel").toString(), "Cart label:");
        Assert.assertEquals(addToCartOverlayPO.getProductQuantity(), testData.get("expectedQuantity"), "Quantity:");

        addToCartOverlayPO.continueShopping();

        //TODO assert
    }


    @Test(priority = 2, dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Change store")
    public void tc_002_changeStore(JSONObject testData) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL + testData.get("pathAndQuery").toString());

        PdpPO pdpPO = new PdpPO();

        SelectStoreModalPO modalPO = pdpPO.openSelectStoreModal();

        StoreLocatorPO storeLocatorPO = modalPO.selectStore(testData.get("storeId").toString());

        boolean isStoreLocationValid = storeLocatorPO.isStoreLocationValid(testData.get("storeLocation").toString());
        String localStorageCurrentCustomerStore = LocalStorage.getCurrentCustomerStore();
        String cookieCurrentCustomerStore = CookiesUtils.getCookieValue(Constants.Cookies.CurrentCustomerStore.NAME);

        Assert.assertTrue(isStoreLocationValid, "Store location is valid");
        Assert.assertEquals(localStorageCurrentCustomerStore, testData.get("storeId").toString(), "[LocalStorage] current customer store value");
        Assert.assertEquals(cookieCurrentCustomerStore, testData.get("storeId").toString(), "[Cookie] current customer store value");
    }
}
