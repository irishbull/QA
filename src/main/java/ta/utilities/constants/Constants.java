package ta.utilities.constants;

import ta.utilities.ReadPropertiesFile;

public class Constants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - Constants construction is forbidden";

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

    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
