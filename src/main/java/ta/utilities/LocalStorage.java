package ta.utilities;

/**
 * Selenium WebDriver Javascript execution for localStorage
 */
public class LocalStorage {

    private static final String CONSTRUCTION_FORBIDDEN = "BrowserUtils - Object construction is forbidden";

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

    private LocalStorage() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
}
