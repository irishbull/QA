package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.constants.Constants;

public class ProductNavBarPO extends PageObject {

    @FindBy(how = How.NAME, using = "productsNavBar")
    private WebElement productsNavBar;

    public void clickOnCategoria() {
        productsNavBar.findElement(By.xpath("//*[contains(text(), 'Categoria')]")).click();
    }

    public void selectFilter(String dataFilterId) {
        productsNavBar.findElement(By.xpath(String.format("//div[@data-filter-id='%s']", dataFilterId))).click();
    }

    public void applyFilter() {
        WebElement applyButton = productsNavBar.findElement(By.xpath("//*[contains(text(), 'APPLICA')]"));
        BrowserUtils.waitFor(applyButton, Constants.WaitTime.EXPLICIT_WAIT);
        applyButton.click();
    }
}
