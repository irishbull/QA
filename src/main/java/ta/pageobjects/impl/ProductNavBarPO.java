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

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_CATEGORYNAME_CONTAINER")
    private WebElement categoryFilterContainer;

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_BRAND_CONTAINER")
    private WebElement brandFilterContainer;

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_RANGEPRICEFILTER_CONTAINER")
    private WebElement priceFilterContainer;

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_CATEGORYNAME_APPLY")
    private WebElement categoryFilterApplyButton;

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_BRAND_APPLY")
    private WebElement brandFilterApplyButton;

    @FindBy(how = How.NAME, using = "SELENIUM_FILTER_RANGEPRICEFILTER_APPLY")
    private WebElement priceFilterApplyButton;

    @FindBy(how = How.NAME, using = "SELENIUM_ORDER_BY")
    private WebElement sortingMenuContainer;


    public void applyFilter(String filterType, String filterId) {

        WebElement filterContainer;
        WebElement applyButton;

        switch (filterType) {
            case "Categoria":
                filterContainer = categoryFilterContainer;
                applyButton = categoryFilterApplyButton;
                break;

            case "Marca":
                filterContainer = brandFilterContainer;
                applyButton = brandFilterApplyButton;
                break;

            case "Prezzo":
                filterContainer = priceFilterContainer;
                applyButton = priceFilterApplyButton;
                break;

            default:
                throw new IllegalArgumentException(String.format("Serp filter type [%s] is not valid", filterType));
        }

        // open menù
        filterContainer.click();

        // select filter by filterId
        WebElement filter = filterContainer.findElement(By.xpath(String.format(".//div[@data-filter-id='%s']", filterId)));
        BrowserUtils.waitFor(filter, Constants.WaitTime.EXPLICIT_WAIT);
        filter.click();

        // apply filter
        BrowserUtils.waitFor(applyButton, Constants.WaitTime.EXPLICIT_WAIT);
        applyButton.click();
    }


    public void sortBy(String sortingType) {

        // open menù
        sortingMenuContainer.click();

        // select the sorting type
        String xpath = String.format(".//*[contains(text(), '%s')]", sortingType);
        WebElement sortingTypeSelected = sortingMenuContainer.findElement(By.xpath(xpath));

        // apply sorting
        BrowserUtils.waitFor(sortingTypeSelected, Constants.WaitTime.EXPLICIT_WAIT);
        sortingTypeSelected.click();

    }


    public void searchWithTypoCorrectionDisabled(String typoError) {

        String xpath = String.format(".//*[contains(text(), '%s')]", typoError);
        WebElement elem = productsNavBar.findElement(By.xpath(xpath));

        // search
        elem.click();
    }

}
