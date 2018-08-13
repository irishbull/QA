package ta.utilities.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ta.utilities.ReadPropertiesFile;


public class ToosoConstants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - ToosoConstants construction is forbidden";

    public enum RequestType {
        SUGGEST,
        SEARCH,
        PAGEVIEW,
        PAGEVIEW_DETAIL,
        PAGEVIEW_PURCHASE,
        CLICK_AFTER_SEARCH,
        CLICK_ON_SUGGESTED,
        ADD_TO_CART,
        REMOVE_FROM_CART
    }


    // Tooso Document Location
    public static final String DL = "dl";
    // Tooso Document Path
    public static final String DP = "dp";
    // Tooso suggest request prefix
    public static final String PROXY_SUGGEST_PREFIX = "*";
    // Tooso quiet period
    public static final Long QUIET_PERIOD = 3L;
    // Tooso timeout
    public static final Long TIMEOUT = 10L;

    // Tooso environment values
    public static final String METHOD = "GET";
    public static final String BASE_URL = ReadPropertiesFile.getProperty("tooso.base.url");
    public static final String TID = ReadPropertiesFile.getProperty("tooso.tid");
    public static final String PROXY_TID = ReadPropertiesFile.getProperty("tooso.proxy.tid");
    public static final String PROXY_SEARCH_BASE_URL = ReadPropertiesFile.getProperty("tooso.proxy.search.base.url");
    public static final String PROXY_SUGGEST_BASE_URL = ReadPropertiesFile.getProperty("tooso.proxy.suggest.base.url");


    // Tooso request filters
    public static class Filters {

        public static final List<String> PAGEVIEW;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=pageview"
            ));
            PAGEVIEW = Collections.unmodifiableList(list);
        }


        public static final List<String> PAGEVIEW_DETAIL;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=pageview",
                    "pa=detail"
            ));
            PAGEVIEW_DETAIL = Collections.unmodifiableList(list);
        }


        public static final List<String> PAGEVIEW_PURCHASE;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=pageview",
                    "pa=purchase"
            ));
            PAGEVIEW_PURCHASE = Collections.unmodifiableList(list);
        }


        public static final List<String> CLICK_AFTER_SEARCH;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=event",
                    "ec=cart",
                    "ea=click"
            ));

            CLICK_AFTER_SEARCH = Collections.unmodifiableList(list);
        }


        public static final List<String> CLICK_ON_SUGGESTED;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=event",
                    "ec=suggestions",
                    "ea=select"
            ));
            CLICK_ON_SUGGESTED = Collections.unmodifiableList(list);
        }


        public static final List<String> ADD_TO_CART;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=event",
                    "ec=cart",
                    "ea=add"
            ));
            ADD_TO_CART = Collections.unmodifiableList(list);
        }


        public static final List<String> REMOVE_FROM_CART;

        static {
            List<String> list = new ArrayList<>(Arrays.asList(
                    "t=event",
                    "ec=cart",
                    "ea=remove"
            ));
            REMOVE_FROM_CART = Collections.unmodifiableList(list);
        }

        private Filters() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class Analytics {

        public static class Common {

            // Assert equals common query params map
            public static final Map<String, String> ASSERT_EQUALS_QUERY_PARAMS;

            static {
                Map<String, String> map = new HashMap<>();
                map.put("tid", TID);
                map.put("v", "1");
                map.put("de", Constants.Encode.UTF_8);
                map.put("ul", "it-IT");
                map.put("cu", "EUR");
                ASSERT_EQUALS_QUERY_PARAMS = Collections.unmodifiableMap(map);
            }

            // Assert not empty common query params list
            public static final List<String> ASSERT_NOT_EMPTY_QUERY_PARAMS;

            static {
                List<String> list = new ArrayList<>(Arrays.asList(
                        "sr",
                        "sd",
                        "cid",
                        "tm",
                        "z"
                ));

                ASSERT_NOT_EMPTY_QUERY_PARAMS = Collections.unmodifiableList(list);
            }

            private Common() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }

        private Analytics() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }


    public static class SearchAndSuggest {

        private SearchAndSuggest() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }

        public static class Common {

            // Assert equals common query params map
            public static final Map<String, String> ASSERT_EQUALS_QUERY_PARAMS;

            static {
                Map<String, String> map = new HashMap<>();
                map.put("tid", PROXY_TID);
                map.put("v", "3");
                map.put("ul", "it-IT");
                ASSERT_EQUALS_QUERY_PARAMS = Collections.unmodifiableMap(map);
            }

            // Assert not empty common query params list
            public static final List<String> ASSERT_NOT_EMPTY_QUERY_PARAMS;

            static {
                List<String> list = new ArrayList<>(Arrays.asList(
                        "q",
                        "tm",
                        "cid",
                        "z"
                ));

                ASSERT_NOT_EMPTY_QUERY_PARAMS = Collections.unmodifiableList(list);
            }

            private Common() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }
    }

    private ToosoConstants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}