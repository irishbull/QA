package ta.utilities.constants;

import ta.utilities.ReadPropertiesFile;

public class Constants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - Constants construction is forbidden";

    public static String EMPTY_STRING = "";

    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }

    public static class WaitTime {
        public static final Integer IMPLICIT_WAIT = 5;
        public static final Integer EXPLICIT_WAIT = 2;

        private WaitTime() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class Encode {
        public static final String UTF_8 = "UTF-8";

        private Encode() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class Url {
        public static final String BASE_URL = ReadPropertiesFile.getProperty("base.url");

        private Url() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class Cookies {

        private Cookies() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }

        public static class CurrentCustomerStore {
            public static final String NAME = "currentCustomerStore";
            public static final String VALUE = ReadPropertiesFile.getProperty("current.customer.store.value");
            public static final String DOMAIN = ReadPropertiesFile.getProperty("current.customer.store.domain");
            public static final String PATH = ReadPropertiesFile.getProperty("current.customer.store.path");

            private CurrentCustomerStore() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }
    }
}
