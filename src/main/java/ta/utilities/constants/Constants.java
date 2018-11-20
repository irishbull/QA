package ta.utilities.constants;

import ta.utilities.ReadPropertiesFile;

public class Constants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - Constants construction is forbidden";

    public static final String EMPTY_STRING = "";

    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }

    public static class WaitTime {
        public static final Integer IMPLICIT_WAIT = 5;
        public static final Integer EXPLICIT_WAIT = 2;
        public static final Integer FIVE_SECONDS = 5;
        public static final Integer TEN_SECONDS = 10;
        public static final Integer FIFTEEN_SECONDS = 15;

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
            public static final String DEFAULT_VALUE = ReadPropertiesFile.getProperty("current.customer.store.cookie.default.value");
            public static final String DOMAIN = ReadPropertiesFile.getProperty("current.customer.store.cookie.domain");
            public static final String PATH = ReadPropertiesFile.getProperty("current.customer.store.cookie.path");

            private CurrentCustomerStore() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }
    }

    public static class LocalStorage {
        private LocalStorage() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }

        public static final String CURRENT_CUSTOMER_STORE = "currentCustomerStore";
        public static final String CURRENT_CUSTOMER_STORE_VALUE = "7";
        public static final String STORE_CONSENT = "storeConsent";
        public static final String ACCEPT = "true";
        public static final String DENY = "false";
    }

    public static class SessionStorage {
        private SessionStorage() {throw new IllegalStateException(CONSTRUCTION_FORBIDDEN); }

        public static final String CART_CODE = "cartCode";
    }
}
