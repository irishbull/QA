package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import ta.pageobjects.PageObject;


public class ToosoSearchAngularPO extends PageObject {

    @FindBy(how = How.NAME, using = "tooso-search-primary")
    private WebElement searchBar;


    @FindBy(how = How.NAME, using = "SELENIUM_SEARCH_RESULTS_WRAPPER")
    private WebElement searchResultsWrapper;

    public void clickOnSearchBar() {
        searchBar.click();
    }

    public void enterWord(String word) {
        this.searchBar.sendKeys(word);
    }

    public void search() {
        this.searchBar.sendKeys(Keys.ENTER);
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
