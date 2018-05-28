package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.IdeaPiuPO;
import ta.test.BaseTest;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class HomePageTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

  @Test
  public void spanTest() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get(ReadPropertiesFile.getProperty("base.url"));

    HomePagePO homePagePO = new HomePagePO();
    logger.info("SPAN TEXT = " + homePagePO.getSpanText());

    assertTrue(homePagePO.getSpanText().contains("Ideapi"));
  }


  @Test(dependsOnMethods = {"spanTest"})
  public void linkTest() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    HomePagePO homePagePO = new HomePagePO();

    String mainWindow = driver.getWindowHandle();

    // click link opening new tab
    IdeaPiuPO ideaPiuPO = homePagePO.clickLink();
    logger.info("BUTTON CLICKED");


    for (String winHandle : driver.getWindowHandles()) {
      if (!mainWindow.equals(winHandle)) {
        // switch window
        driver.switchTo().window(winHandle);
        logger.info("driver switched to window: {}", winHandle);
      }
    }


    assertTrue(ideaPiuPO.getTitle().contains("Diventa Titolare Idea"));

    assertTrue(ideaPiuPO.getUrl().contains(Constants.PathComponent.IDEAPIU));

    assertTrue(ideaPiuPO.getDivAttribute().equals("idea-piu"));


    // switch back
    if (!driver.getWindowHandle().equals(mainWindow)) {

      // close the window, window is no more required
      driver.close();

      // switch back to original window (main window)
      driver.switchTo().window(mainWindow);

      logger.info("driver switched to window: {}", mainWindow);
    }

  }
}

