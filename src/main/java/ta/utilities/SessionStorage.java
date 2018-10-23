package ta.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Selenium WebDriver Javascript execution for sessionStorage
 */
public class SessionStorage {

    private static final Logger logger = LoggerFactory.getLogger(SessionStorage.class);
    private static final String CONSTRUCTION_FORBIDDEN = "SessionStorage - Object construction is forbidden";

    /**
     * Check if the item exists in session storage
     *
     * @param item
     * @return
     */
    public static boolean isItemPresent(String item) {
        return (JavascriptUtils.execute(String.format("return window.sessionStorage.getItem('%s');", item)) != null);
    }

    /**
     * Retrieve the item from session storage
     *
     * @param key
     * @return
     */
    public static String getItem(String key) {
        return (String) JavascriptUtils.execute(String.format("return window.sessionStorage.getItem('%s');", key));
    }

    /**
     * Retrieve the key from session storage
     *
     * @param key
     * @return
     */
    public static String getKey(int key) {
        return (String) JavascriptUtils.execute(String.format("return window.sessionStorage.key('%s');", key));
    }

    /**
     * Retrieve the number of items in session storage
     *
     * @return
     */
    public static long length() {
        return (long) JavascriptUtils.execute("return window.sessionStorage.length;");
    }

    /**
     * Set the item in session storage
     *
     * @param item
     * @param value
     */
    public static void setItem(String item, String value) {
        JavascriptUtils.execute(String.format("window.sessionStorage.setItem('%s','%s');", item, value));
    }

    /**
     * Remove item from session storage
     *
     * @param item
     */
    public static void removeItem(String item) {
        JavascriptUtils.execute(String.format("window.sessionStorage.removeItem('%s');", item));
    }


    /**
     * Clear session storage
     */
    public static void clear() {
        JavascriptUtils.execute("window.sessionStorage.clear();");
    }


    private SessionStorage() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
}
