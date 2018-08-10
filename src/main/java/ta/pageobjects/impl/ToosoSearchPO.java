package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

import java.util.List;


public class ToosoSearchPO extends PageObject {

    @FindBy(how = How.NAME, using = "SELENIUM_POPUP_SEARCH_INPUT")
    private WebElement popupSearchInput;

    @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_RESULTS_WRAPPER")
    private WebElement searchResultsWrapper;

    public void enterWord(String word) {
        this.popupSearchInput.sendKeys(word);
    }

    public ToosoSearchPO search() {
        this.popupSearchInput.click();
        return this;
    }

    public int getResultsNumber() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        return liElems.size();
    }

    public WebElement getFirstResultElement() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        return liElems.get(0);
    }
}
