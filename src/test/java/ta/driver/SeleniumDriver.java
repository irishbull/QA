package ta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

public class SeleniumDriver {

  private static SeleniumDriver instance = new SeleniumDriver();
  private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();


  // private constructor to prevent any other class from instantiating (singleton)
  private SeleniumDriver() {}


  /**
   * getInstance method to retrieve active driver instance
   *
   * @return SeleniumDriver
   */
  public static SeleniumDriver getInstance() {
    if (instance == null) {
      instance = new SeleniumDriver();
    }
    return instance;
  }


  /**
   * getDriver method to retrieve active driver
   *
   * @return WebDriver
   */
  public WebDriver getDriver() {
    return webDriver.get();
  }


  /*
   * setDriver method to create driver instance
   *
   * @param browser
   */
  public final void setDriver(String browser) {
    DesiredCapabilities capabilities;

    switch (browser) {

      case "chrome":

        // Chrome preferences
        Map<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("credentials_enable_service", false);

        // Chrome options
        ChromeOptions chrOptions = new ChromeOptions();
        chrOptions.setExperimentalOption("prefs", chromePrefs);
        chrOptions.addArguments("--disable-plugins", "--disable-extensions",
            "--disable-popup-blocking");

        // Chrome desired capabilities
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chrOptions);
        capabilities.setCapability("applicationCacheEnabled", false);

        System.setProperty("webdriver.chrome.driver",
            ReadPropertiesFile.getProperty("chrome.webdriver.path"));
        webDriver.set(new ChromeDriver(chrOptions.merge(capabilities)));
        getDriver().manage().timeouts().implicitlyWait(Constants.SetUp.IMPLICITY_WAIT,
            TimeUnit.SECONDS);

        break;


      case "firefox":

        // Firefox options
        FirefoxOptions ffxOpts = new FirefoxOptions();

        // Firefox profile
        FirefoxProfile ffProfile = new FirefoxProfile();
        ffProfile.setPreference("browser.autofocus", true);
        ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

        // Firefox capabilities
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
        capabilities.setCapability("marionette", true);


        System.setProperty("webdriver.gecko.driver",
            ReadPropertiesFile.getProperty("firefox.webdriver.path"));
        webDriver.set(new FirefoxDriver(ffxOpts.merge(capabilities)));
        getDriver().manage().timeouts().implicitlyWait(Constants.SetUp.IMPLICITY_WAIT,
            TimeUnit.SECONDS);

        break;

    }
  }
}
