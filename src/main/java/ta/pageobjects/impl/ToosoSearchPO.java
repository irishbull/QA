package ta.pageobjects.impl;

import org.apache.commons.lang3.StringUtils;
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
    private WebElement searchTopBar;

    @FindBy(how = How.NAME, using = "SELENIUM_POPUP_SEARCH_INPUT")
    private WebElement popupSearchInput;

    @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_RESULTS_WRAPPER")
    private WebElement searchResultsWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCT_CARD_0")
    private WebElement firstProductCard;

    public void clickOnSearchTopBar() {
        searchTopBar.click();
    }

    public void erasePopupSearchInput() {
        // note: ChromeDriver.clear does not clear text input fields
        while (StringUtils.isNotEmpty(popupSearchInput.getAttribute("value"))) {
            popupSearchInput.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void enterWord(String word) {
        popupSearchInput.sendKeys(word);
    }

    public void search() {
        popupSearchInput.sendKeys(Keys.ENTER);
    }

    public int getSearchResultsNumber() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        return liElems.size();
    }

    public void clickOnFirstResultElement() {
        List<WebElement> liElems = searchResultsWrapper.findElements(By.tagName("li"));
        liElems.get(0).click();
    }

    public void clickOnFirstProduct() {
        firstProductCard.findElement(By.tagName("img")).click();
    }
}
