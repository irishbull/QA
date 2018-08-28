package ta.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ta.utilities.constants.Constants.EMPTY_STRING;

/**
 * Selenium WebDriver Javascript execution for localStorage
 */
public class LocalStorage {

    private static final Logger logger = LoggerFactory.getLogger(LocalStorage.class);
    private static final String CONSTRUCTION_FORBIDDEN = "LocalStorage - Object construction is forbidden";
    private static final String SESSION_ID = "sessionID";
    private static final String UID_PREFIX = "web_";

    /**
     * Check if the item exists in local storage
     *
     * @param item
     * @return
     */
    public static boolean isItemPresentInLocalStorage(String item) {
        return (JavascriptUtils.execute(String.format("return window.localStorage.getItem('%s');", item)) != null);
    }

    /**
     * Retrieve the item from local storage
     *
     * @param key
     * @return
     */
    public static String getItemFromLocalStorage(String key) {
        return (String) JavascriptUtils.execute(String.format("return window.localStorage.getItem('%s');", key));
    }

    /**
     * Retrieve the key from local storage
     *
     * @param key
     * @return
     */
    public static String getKeyFromLocalStorage(int key) {
        return (String) JavascriptUtils.execute(String.format("return window.localStorage.key('%s');", key));
    }

    /**
     * Retrieve the number of item in local storage
     *
     * @return
     */
    public static int getLocalStorageLength() {
        return (int) JavascriptUtils.execute("return window.localStorage.length;");
    }

    /**
     * Set the item in local storage
     *
     * @param item
     * @param value
     */
    public static void setItemInLocalStorage(String item, String value) {
        JavascriptUtils.execute(String.format("window.localStorage.setItem('%s','%s');", item, value));
    }

    /**
     * Remove iteme from local storage
     *
     * @param item
     */
    public static void removeItemFromLocalStorage(String item) {
        JavascriptUtils.execute(String.format("window.localStorage.removeItem('%s');", item));
    }


    /**
     * Clear local storage
     */
    public static void clearLocalStorage() {
        JavascriptUtils.execute("window.localStorage.clear();");
    }


    /**
     * Retrieve the value of uid from local storage and remove prefix and "
     *
     * @return uid value
     */
    public static String getUidFromLocalStorage() {

        if (isItemPresentInLocalStorage(SESSION_ID)) {

            // remove prefix and (")
            String uid = getItemFromLocalStorage(SESSION_ID).replace(UID_PREFIX, EMPTY_STRING);
            return uid.substring(1, uid.length() - 1);

        } else {
            logger.warn("sessionId not found in localStorage");
            return EMPTY_STRING;
        }
    }

    private LocalStorage() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
}
