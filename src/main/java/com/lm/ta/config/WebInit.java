package com.lm.ta.config;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.lm.ta.utilities.Constants;

/**
 * <strong>WebInit</strong> handles setup and teardown of WebDriver.
 * @author dmonaco
 */
public class WebInit {

	protected static WebDriver driver;
	
	@BeforeClass
	public static void setUp(){
		System.setProperty(Constants.CHROME_DRIVER_SYSTEM_PROPERTY, Constants.CHROME_DRIVER_SYSTEM_PATH);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@After
	public void cleanUp(){
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.close();
	}	
}