package ta.pageobjects.impl;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;


public class ToosoSearchAngularPO extends PageObject {

    @FindBy(how = How.NAME, using = "tooso-search-primary")
    private WebElement searchTopBar;

    public void clickOnSearchTopBar() {
        searchTopBar.click();
    }

    public void enterWord(String word) {
        this.searchTopBar.sendKeys(word);
    }

    public SerpPO search() {
        this.searchTopBar.sendKeys(Keys.ENTER);
        return new SerpPO();
    }
}
