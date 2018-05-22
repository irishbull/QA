package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.IdeaPiuPO;
import ta.test.BaseTest;

import static org.testng.Assert.assertTrue;

public class HomePageTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

  @Test
  public void spanTest() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get("\n" + "https://lmuser:test0102@www-react-qa.leroymerlin.it/");

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

    // switch window
    for (String winHandle : driver.getWindowHandles()) {
      if (!mainWindow.equals(winHandle)) {
        driver.switchTo().window(winHandle);
      }
    }

    assertTrue(ideaPiuPO.getTitle().contains("Diventa Titolare Idea"));

    assertTrue(ideaPiuPO.getUrl().contains("idea-piu"));

    assertTrue(ideaPiuPO.getDivAttribute().equals("idea-piu"));

    // close the window, if that window no more required
    driver.close();

    // switch back to original window (main window)
    driver.switchTo().window(mainWindow);

  }
}

