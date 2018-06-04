package ta.test.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.IdeaPiuPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class HomePageTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

  @Test
  public void navigateProductMenu() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get(ReadPropertiesFile.getProperty("base.url"));

    HomePagePO homePagePO = new HomePagePO();

    BrowserUtils.hover(homePagePO.getProductMenu());

    //BrowserUtils.waitFor(By.name("SELENIUM_PRODUCTS_MENU_MACROCATEGORYGROUP_WRAPPER"),10);
    BrowserUtils.waitFor(homePagePO.getMacroCategoryGroup(),Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getMacroCategoryGroup());

    BrowserUtils.hover(homePagePO.getBagnoLink());

    //BrowserUtils.waitFor(By.name("SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER"),10);
    BrowserUtils.waitFor(homePagePO.getMacroCategory(),Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getDocceSpan());


    //BrowserUtils.waitFor(By.name("SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER"),10);
    BrowserUtils.waitFor(homePagePO.getCategory(),Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getSauneSpan());

    logger.info("Navigate menu : Prodotti -> Bagno -> Docce -> Saune");
  }


  @Test
  public void ideaPiuLinkTest() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get(ReadPropertiesFile.getProperty("base.url"));

    HomePagePO homePagePO = new HomePagePO();

    String mainWindow = driver.getWindowHandle();

    // click link opening new tab
    IdeaPiuPO ideaPiuPO = homePagePO.clickIdeaPiuLink();
    logger.info("IdeaPiu link CLICKED");


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

