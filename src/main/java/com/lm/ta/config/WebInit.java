package com.lm.ta.config;

import com.lm.ta.utilities.Constants;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebInit {
	
	private static final Logger logger = LoggerFactory.getLogger(WebInit.class);

	protected static String url;
	protected static WebDriver driver;
	protected static WebDriverWait timeWait;
	protected static int hightlightTime;
	protected static int popupLoadWait;
	protected static int pageLoadWait;
	protected static int imageLoadWait;
	protected static Document doc;	
	
	protected void Init() {
		logger.info("::: Opening Chrome Browser");
		System.setProperty(Constants.CHROME_DRIVER_SYSTEM_PROPERTY, Constants.CHROME_DRIVER_SYSTEM_PATH);
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Constants.IMPLICITY_WAIT, TimeUnit.SECONDS);
		//timeWait = new WebDriverWait(driver, Constants.EXPLICITY_WAIT);
		//driver.manage().window().maximize();
		logger.info("::: Navigate Url: {}",Constants.URL_TO_INVOKE);
		//driver.get(Constants.URL_TO_INVOKE);
	}
}
