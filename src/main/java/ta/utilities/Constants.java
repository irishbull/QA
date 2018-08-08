package ta.utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - Object construction is forbidden";

    public static class WaitTime {
        public static final Integer IMPLICIT_WAIT = 5;
        public static final Integer EXPLICIT_WAIT = 2;

        private WaitTime() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
    }

    public static class Encode {
        public static final String UTF_8 = "UTF-8";

        private Encode() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
    }

    public static class Url {
        public static final String BASE_URL = ReadPropertiesFile.getProperty("base.url");

        private Url() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
    }

    public static class Tooso {

        // Tooso document location
        public static final String DL = "dl";
        // Tooso document path
        public static final String DP = "dp";

        // Tooso environment values
        public static final String HOSTNAME = ReadPropertiesFile.getProperty("tooso.hostname");
        public static final String TID = ReadPropertiesFile.getProperty("tooso.tid");

        // Tooso proxy filters
        public static final String PAGEVIEW_FILTER = "t=pageview";
        public static final String SUGGEST_EC_FILTER = "ec=suggestions";


        public static class Common {

            // Assert equals common query params map
            public static final Map<String, String> ASSERT_EQUALS_QUERY_PARAMS;
            static {
                Map<String, String> map = new HashMap<>();
                map.put("tid", TID);
                map.put("v", "1");
                map.put("de", Encode.UTF_8);
                map.put("ul", "it-IT");
                map.put("cu", "EUR");
                ASSERT_EQUALS_QUERY_PARAMS = Collections.unmodifiableMap(map);
            }

            // Assert not empty common query params list
            public static final List<String> ASSERT_NOT_EMPTY_QUERY_PARAMS = Arrays.asList(
                    "sr",
                    "sd",
                    "cid",
                    "tm",
                    "z"
            );

            private Common() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
        }




        private Tooso() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
    }

    
    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
