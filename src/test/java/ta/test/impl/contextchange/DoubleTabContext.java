package ta.test.impl.contextchange;

import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ta.dataproviders.JSONDataProvider;
import ta.driver.SeleniumDriver;
import ta.pageobjects.impl.HomePagePO;
import ta.pageobjects.impl.LoginPO;
import ta.test.BaseTest;

import java.net.URISyntaxException;

import static ta.utilities.constants.Constants.Url.BASE_URL;

public class DoubleTabContext extends BaseTest {

    @Test(dataProvider = "fetchJSONData", dataProviderClass = JSONDataProvider.class , priority = 1)
    @Description(("<div>Given: utente non loggato&nbsp;</div>\n" +
        "<div>When: </div>\n" +
        "<div>&nbsp; &nbsp; &nbsp; And: utente  (<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it/mylm</span>)&nbsp;</div>\n" +
        "<div>&nbsp; &nbsp; &nbsp; And: utente naviga su pagina in contesto react&nbsp;(<span style=\"font-family:courier new,courier,monospace\">https://www.leroymerlin.it</span>)</div>\n" +
        "<div>Then: utente resta loggato&nbsp;&nbsp;</div>"))

    public void test_001_DoubleTab(JSONObject testData) throws URISyntaxException
    {
        
    }

    public void test_002_DoubleTab(){

    }


}
