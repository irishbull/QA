package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;


import ta.driver.SeleniumDriver;

import ta.pageobjects.impl.HomePageSearchPO;
import ta.pageobjects.impl.HomePageSearchResultsPO;
import ta.test.BaseTest;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class HomePageSearchTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(HomePageSearchTest.class);

  @Test
  public void searchReturnsAtMost3Results() throws Exception{
      WebDriver driver = SeleniumDriver.getInstance().getDriver();

      driver.get(ReadPropertiesFile.getProperty("base.url"));

      HomePageSearchPO homePageSearchPO = new HomePageSearchPO();

      HomePageSearchResultsPO homePageSearchResultsPO = homePageSearchPO.clickOnSearch();

      int a = homePageSearchResultsPO.numberOfLiElems();
      logger.info(String.valueOf(a));

      assertTrue(homePageSearchResultsPO.numberOfLiElems() <= 3);
  }
}


