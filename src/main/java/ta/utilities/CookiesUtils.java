package ta.utilities;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import ta.driver.SeleniumDriver;
import ta.utilities.constants.Constants;

public class CookiesUtils {

    private static final String CONSTRUCTION_FORBIDDEN = "CookiesUtils - Object construction is forbidden";

    private CookiesUtils() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }

    /**
     * Retrieve the value of cookie with name specified as parameter
     *
     * @param name
     * @return cookie value
     */
    public static String getCookieValue(String name) {
        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        Cookie cookie = driver.manage().getCookieNamed(name);
        return Objects.nonNull(cookie) ? cookie.getValue(): "";
    }


    /**
     * Retrieve the value of cid from cookie ta and remove the prefix "_ta"
     *
     * @return cid value
     */
    public static String getCidValueFromCookieTA() {
        String value = getCookieValue("_ta");
        return value.isEmpty() ? value : value.substring(value.lastIndexOf('.') + 1);
    }


    /**
     * Add the current customer store cookie (default storeid)
     */
    public static void addCurrentCustomerStore() {
        addCurrentCustomerStore(Constants.Cookies.CurrentCustomerStore.DEFAULT_VALUE);
    }


    /**
     * Add the current customer cookie
     *
     * @param storeId
     */
    public static void addCurrentCustomerStore(String storeId) {

        Cookie cookie = new Cookie.Builder(Constants.Cookies.CurrentCustomerStore.NAME, storeId)
                .domain(Constants.Cookies.CurrentCustomerStore.DOMAIN)
                .path(Constants.Cookies.CurrentCustomerStore.PATH)
                .expiresOn(calculateExpiryDate())
                .build();

        SeleniumDriver.getInstance().getDriver().manage().addCookie(cookie);
    }


    /**
     * Replace the current customer cookie
     *
     * @param storeId
     */
    public static void replaceCurrentCustomerStore(String storeId) {

        WebDriver driver = SeleniumDriver.getInstance().getDriver();
        Cookie oldCookie = driver.manage().getCookieNamed(Constants.Cookies.CurrentCustomerStore.NAME);

        if(Objects.nonNull(oldCookie)) {
            driver.manage().deleteCookieNamed(Constants.Cookies.CurrentCustomerStore.NAME);
        }

        addCurrentCustomerStore(storeId);
    }

    /**
     * Replace the current customer cookie (default storeId)
     */
    public static void replaceCurrentCustomerStore() {
        replaceCurrentCustomerStore(Constants.Cookies.CurrentCustomerStore.DEFAULT_VALUE);
    }


    private static Date calculateExpiryDate() {

        Date now = new Date();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(now);
        cal.add(Calendar.DATE, 15);

        // add  15 days to current date
        return cal.getTime();
    }
}
