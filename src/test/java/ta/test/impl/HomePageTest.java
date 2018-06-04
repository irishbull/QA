package ta.test.impl;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.HomePageSearchResultsPO;
import ta.pageobjects.impl.IdeaPiuPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;
import ta.utilities.ReadPropertiesFile;

import static org.testng.Assert.assertTrue;

public class HomePageTest extends BaseTest {

  private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

  @Test
  @Description("Verifica che la ricerca (string vuota) restuisce al piu' tre risultati")
  public void tc_001_searchReturnsAtMost3Results() throws Exception {
    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get(ReadPropertiesFile.getProperty("base.url"));

    HomePagePO homePagePO = new HomePagePO();

    HomePageSearchResultsPO homePageSearchResultsPO = homePagePO.clickOnSearch();

    int a = homePageSearchResultsPO.getResultsNumber();

    logger.info(String.valueOf(a));

    assertTrue(homePageSearchResultsPO.getResultsNumber() <= 3);
  }


  @Test
  @Description("Verifica la navigazione del menu : Prodotti -> Bagno -> Docce -> Saune")
  public void tc_002_navigateProductMenu() throws Exception {

    WebDriver driver = SeleniumDriver.getInstance().getDriver();

    driver.get(ReadPropertiesFile.getProperty("base.url"));

    HomePagePO homePagePO = new HomePagePO();

    BrowserUtils.hover(homePagePO.getProductMenu());

    BrowserUtils.waitFor(homePagePO.getMacroCategoryGroup(), Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getMacroCategoryGroup());

    BrowserUtils.hover(homePagePO.getBagnoLink());

    BrowserUtils.waitFor(homePagePO.getMacroCategory(), Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getDocceSpan());

    BrowserUtils.waitFor(homePagePO.getCategory(), Constants.WaitTime.EXPLICIT_WAIT);
    BrowserUtils.hover(homePagePO.getSauneSpan());

    logger.info("Navigate menu : Prodotti -> Bagno -> Docce -> Saune");
  }


  @Test
  @Description("Verifica il corretto funzionamento del link idea-piu")
  public void tc_003_ideaPiuLinkTest() throws Exception {

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

