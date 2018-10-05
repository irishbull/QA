package ta.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ta.utilities.constants.Constants.EMPTY_STRING;
import static ta.utilities.constants.Constants.LocalStorage.CURRENT_CUSTOMER_STORE;

/**
 * Selenium WebDriver Javascript execution for localStorage
 */
public class LocalStorage {

    private static final Logger logger = LoggerFactory.getLogger(LocalStorage.class);
    private static final String CONSTRUCTION_FORBIDDEN = "LocalStorage - Object construction is forbidden";
    private static final String SESSION_ID = "sessionID";
    private static final String UID_PREFIX = "(?i)web_";

    /**
     * Check if the item exists in local storage
     *
     * @param item
     * @return
     */
    public static boolean isItemPresent(String item) {
        return (JavascriptUtils.execute(String.format("return window.localStorage.getItem('%s');", item)) != null);
    }

    /**
     * Retrieve the item from local storage
     *
     * @param key
     * @return
     */
    public static String getItem(String key) {
        return (String) JavascriptUtils.execute(String.format("return window.localStorage.getItem('%s');", key));
    }

    /**
     * Retrieve the key from local storage
     *
     * @param key
     * @return
     */
    public static String getKey(int key) {
        return (String) JavascriptUtils.execute(String.format("return window.localStorage.key('%s');", key));
    }

    /**
     * Retrieve the number of items in local storage
     *
     * @return
     */
    public static long length() {
        return (long) JavascriptUtils.execute("return window.localStorage.length;");
    }

    /**
     * Set the item in local storage
     *
     * @param item
     * @param value
     */
    public static void setItem(String item, String value) {
        JavascriptUtils.execute(String.format("window.localStorage.setItem('%s','%s');", item, value));
    }

    /**
     * Remove item from local storage
     *
     * @param item
     */
    public static void removeItem(String item) {
        JavascriptUtils.execute(String.format("window.localStorage.removeItem('%s');", item));
    }


    /**
     * Clear local storage
     */
    public static void clear() {
        JavascriptUtils.execute("window.localStorage.clear();");
    }


    /**
     * Retrieve the value of uid from local storage and remove prefix and "
     *
     * @return uid value
     */
    public static String getUid() {

        if (isItemPresent(SESSION_ID)) {

            // remove prefix, leading and trailing quotes (")
            String uid = getItem(SESSION_ID).replaceAll(UID_PREFIX, EMPTY_STRING);
            return uid.substring(1, uid.length() - 1);

        } else {
            logger.warn("sessionId not found in localStorage");
            return EMPTY_STRING;
        }
    }


    /**
     * Retrieve the value of current customer store
     *
     * @return
     */
    public static String getCurrentCustomerStore() {

        if(isItemPresent(CURRENT_CUSTOMER_STORE)) {
            String currentCustomerStore = getItem(CURRENT_CUSTOMER_STORE);
            // remove leading and trailing quotes (")
            return currentCustomerStore.substring(1, currentCustomerStore.length() - 1);
        } else {
            logger.warn("current customer store not found in localStorage");
            return EMPTY_STRING;
        }
    }

    private LocalStorage() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
}
