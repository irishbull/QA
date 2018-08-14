package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

import java.util.List;


public class ToosoSearchPO extends PageObject {

    @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_TOP")
    @CacheLookup
    private WebElement searchWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_POPUP_SEARCH_INPUT")
    private WebElement popupSearchInput;

    @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_RESULTS_WRAPPER")
    private WebElement searchResultsWrapper;

    public void clickOnSearchBar() {
        searchWrapper.click();
    }

    public void enterWord(String word) {
        this.popupSearchInput.sendKeys(word);
    }

    public void search() {
        this.popupSearchInput.sendKeys(Keys.ENTER);
    }

    public int getResultsNumber() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        return liElems.size();
    }

    public void clickOnFirstResultElement() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        liElems.get(0).click();
    }
}
