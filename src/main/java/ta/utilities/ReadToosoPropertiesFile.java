package ta.utilities;

import java.util.ResourceBundle;

public class ReadToosoPropertiesFile {

    private static ResourceBundle rb = ResourceBundle.getBundle("tooso");

    private ReadToosoPropertiesFile() {
        throw new IllegalStateException("ReadPropertiesFile - Object construction is forbidden");
    }

    public static String getProperty(String key) {
        return rb.getString(key);
    }
}
