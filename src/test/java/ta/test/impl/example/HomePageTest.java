package ta.test.impl.example;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.IdeaPiuPO;
import ta.pageobjects.impl.ToosoSearchPO;
import ta.test.BaseTest;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

import static org.testng.Assert.assertTrue;
import static ta.utilities.constants.Constants.Url.BASE_URL;

public class HomePageTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(HomePageTest.class);

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica che la ricerca (string vuota) restuisce al piu' tre risultati")
    public void tc_001_searchReturnsAtMost3Results(JSONObject testData) throws Exception {

        logger.info(testData.get("description").toString());
        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

        ToosoSearchPO toosoSearchPO = new ToosoSearchPO();

        toosoSearchPO.clickOnSearchTopBar();

        int a = toosoSearchPO.getSearchResultsNumber();

        logger.info("description from json file: {}", testData.get("description"));
        logger.info(String.valueOf(a));

        assertTrue(toosoSearchPO.getSearchResultsNumber() <= 3);
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica la navigazione del menu : Prodotti -> Bagno -> Docce -> Saune")
    public void tc_002_navigateProductMenu(JSONObject testData) throws Exception {

        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

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
        logger.info("description from json file: {}", testData.get("description"));
    }


    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class)
    @Description("Verifica il corretto funzionamento del link idea-piu")
    public void tc_003_ideaPiuLinkTest(JSONObject testData) throws Exception {

        logger.info("thread-id:{}", String.valueOf(Thread.currentThread().getId()));

        WebDriver driver = SeleniumDriver.getInstance().getDriver();

        driver.get(BASE_URL);

        HomePagePO homePagePO = new HomePagePO();

        String mainWindow = driver.getWindowHandle();

        // click link opening new tab
        IdeaPiuPO ideaPiuPO = homePagePO.clickIdeaPiuLink();
        logger.info("IdeaPiu link CLICKED");
        logger.info("description from json file: {}", testData.get("description"));


        for (String winHandle : driver.getWindowHandles()) {
            if (!mainWindow.equals(winHandle)) {
                // switch window
                driver.switchTo().window(winHandle);
                logger.info("driver switched to window: {}", winHandle);
            }
        }


        assertTrue(ideaPiuPO.getTitle().contains("Diventa Titolare Idea"));

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

