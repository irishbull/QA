package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class ProductNavBarPO extends PageObject {

    @FindBy(how = How.NAME, using = "productsNavBar")
    private WebElement productsNavBar;

    public void clickOnFilterFacet(String filterType) {
        productsNavBar.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", filterType))).click();
    }

    public void selectFilter(String dataFilterId) {
        WebElement filter = productsNavBar.findElement(By.xpath(String.format("//div[@data-filter-id='%s']", dataFilterId)));
        BrowserUtils.waitFor(filter, Constants.WaitTime.EXPLICIT_WAIT);
        filter.click();
    }

    public void applyFilter() {
        List<WebElement> applyButtonList = productsNavBar.findElements(By.xpath("//*[contains(text(), 'APPLICA')]"));

        // TODO remove xpath - Note: DOM contains more than one button APPLICA
        WebElement applyButton = applyButtonList.stream().filter(x -> x.isDisplayed()).findFirst().orElse(applyButtonList.get(0));

        applyButton.click();
    }
}
