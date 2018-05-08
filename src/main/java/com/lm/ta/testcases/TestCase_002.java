package com.lm.ta.testcases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lm.ta.config.WebInit;
/**
 * @author dmonaco
 *
 */
public class TestCase_002 extends WebInit{

   private static final Logger logger = LoggerFactory.getLogger(TestCase_002.class);
   
   @Before
   public void enterBefore(){
	   logger.info("In @Before Login");
   }
   
   @Test
   public void executeTest() {
	   logger.info("Enter executeTest() method");
	   driver.get("https://www.leroymerlin.it");	   
	   WebDriverWait timeWait = new WebDriverWait(driver, 15);
	   timeWait.until(ExpectedConditions.elementToBeClickable(By.linkText("SERVIZI"))).click();
	   logger.info("Message: button clicked");
   }
   
   @After
   public void enterAfter(){
	   logger.info("In @After Login");
   }
}