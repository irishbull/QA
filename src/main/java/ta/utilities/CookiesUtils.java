package ta.utilities;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import ta.driver.SeleniumDriver;

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
}
