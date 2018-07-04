package ta.utilities;

import java.util.ResourceBundle;

public class ReadPropertiesFile {

  private static ResourceBundle rb = ResourceBundle.getBundle("project-config");

  public static String getProperty(String key) {
    // ResourceBundle.clearCache();
    return rb.getString(key);
  }
}
