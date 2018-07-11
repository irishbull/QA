package ta.utilities;

public class Constants {

    private static final String CONSTRUCTION_FORBIDDEN = "Constants class - Object construction is forbidden";

    public static class WaitTime {
        public static final Integer IMPLICIT_WAIT = 5;
        public static final Integer EXPLICIT_WAIT = 2;

        private WaitTime() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class PathComponent {
        public static final String IDEAPIU = "idea-piu";

        private PathComponent() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }
    }

    public static class SignUpPage {

        private SignUpPage() {
            throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
        }

        public static class PageStructure {
            public static final String LOGIN_BUTTON_ID = "";

            private PageStructure() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }

        public static class PageUtils {
            public static final String URL = "";

            private PageUtils() {
                throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
            }
        }
    }

    private Constants() {
        throw new IllegalStateException(CONSTRUCTION_FORBIDDEN);
    }
}
