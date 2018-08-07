package ta.utilities;

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

        // Tooso environment values
        public static final String HOSTNAME = ReadToosoPropertiesFile.getProperty("tooso.hostname");
        public static final String TID = ReadToosoPropertiesFile.getProperty("tooso.tid");

        // Tooso proxy filters
        public static final String PAGEVIEW_FILTER = ReadToosoPropertiesFile.getProperty("tooso.filter.pageview.type");
        public static final String SUGGEST_EC_FILTER = ReadToosoPropertiesFile.getProperty("tooso.filter.suggest.ec");

        // Tooso constant query parameters values
        public static final String V = ReadToosoPropertiesFile.getProperty("tooso.v");
        public static final String DE = ReadToosoPropertiesFile.getProperty("tooso.de");
        public static final String UL = ReadToosoPropertiesFile.getProperty("tooso.ul");
        public static final String CU = ReadToosoPropertiesFile.getProperty("tooso.cu");

        private Tooso() { throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }
    }

    
    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
