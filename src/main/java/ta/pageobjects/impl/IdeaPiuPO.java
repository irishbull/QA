package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.driver.SeleniumDriver;
import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;


public class IdeaPiuPO extends PageObject {

    @FindBy(how = How.ID, using = "idea-piu")
    WebElement div;

    public String getTitle() {
        BrowserUtils.waitForTitle("Diventa Titolare Idea", Constants.WaitTime.EXPLICIT_WAIT);
        return SeleniumDriver.getInstance().getDriver().getTitle();
    }

    public String getUrl() {
        BrowserUtils.waitForURLContains("idea-piu", Constants.WaitTime.EXPLICIT_WAIT);
        return SeleniumDriver.getInstance().getDriver().getCurrentUrl();
    }

    public String getDivAttribute() {
        return div.getAttribute("class");
    }

    public IdeaPiuPO() {
        super();
    }
}
