package ta.utilities;

import java.util.ResourceBundle;

public class ReadPropertiesFile {

  private static ResourceBundle rb = ResourceBundle.getBundle("project-config");

  private ReadPropertiesFile() {
    throw new IllegalStateException("ReadPropertiesFile - Object construction is forbidden");
  }

  public static String getProperty(String key) {
    return rb.getString(key);
  }
}
