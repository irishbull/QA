package ta.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
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

        // option needed by Jenkins to work on Linux. This option avoid browser opening during test
        // for local test comment this line
        chrOptions.addArguments("--headless");

        // Chrome desired capabilities
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chrOptions);
        capabilities.setCapability("applicationCacheEnabled", false);

        System.setProperty("webdriver.chrome.driver",
            ReadPropertiesFile.getProperty("chrome.webdriver.path"));
        webDriver.set(new ChromeDriver(chrOptions.merge(capabilities)));
        getDriver().manage().timeouts().implicitlyWait(Constants.WaitTime.IMPLICIT_WAIT,
            TimeUnit.SECONDS);

        break;


      case "firefox":

        // Firefox options
        FirefoxOptions ffxOpts = new FirefoxOptions();

        // Firefox profile
        FirefoxProfile ffProfile = new FirefoxProfile();
        ffProfile.setPreference("browser.autofocus", true);
        ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

        // option needed by Jenkins to work on Linux. This option avoid browser opening during test
        // for local test comment this line
        ffxOpts.addArguments("--headless");

        // Firefox capabilities
        capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, ffProfile);
        capabilities.setCapability("marionette", true);


        System.setProperty("webdriver.gecko.driver",
            ReadPropertiesFile.getProperty("firefox.webdriver.path"));
        webDriver.set(new FirefoxDriver(ffxOpts.merge(capabilities)));
        getDriver().manage().timeouts().implicitlyWait(Constants.WaitTime.IMPLICIT_WAIT,
            TimeUnit.SECONDS);

        break;

      case "iexplorer":

        // Internet Explorer options
        InternetExplorerOptions ieOpts = new InternetExplorerOptions();

        // Internet Explorer capabilities
        capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        capabilities.setCapability(
            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
        capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        capabilities.setCapability("allow-blocked-content", true);
        capabilities.setCapability("allowBlockedContent", true);

        System.setProperty("webdriver.ie.driver",
            ReadPropertiesFile.getProperty("iexplorer.webdriver.path"));
        webDriver.set(new InternetExplorerDriver(ieOpts.merge(capabilities)));
        getDriver().manage().timeouts().implicitlyWait(Constants.WaitTime.IMPLICIT_WAIT,
            TimeUnit.SECONDS);
    }
  }
}
