package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;
import ta.utilities.BrowserUtils;
import ta.utilities.Constants;

public class ProductMenuPO extends PageObject {

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_DESKTOP")
    @CacheLookup
    private WebElement productMenuWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORYGROUP_WRAPPER")
    private WebElement macroCategoryGroupWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER")
    private WebElement macroCategoryWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER")
    private WebElement categoryWrapper;

    protected WebElement findMacroCategoryGroupLink(String str) {
        return macroCategoryGroupWrapper.findElement(By.linkText(str));
    }

    protected WebElement findMacroCategoryLink(String str) {
        String xpathMC = String.format("//div[@name='SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER']/div/div/span[contains(text(), '%s')]", str);
        return macroCategoryWrapper.findElement(By.xpath(xpathMC));
    }

    protected WebElement findCategoryLink(String str) {
        String xpathC = String.format("//div[@name='SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER']/div/a/div/span[contains(text(), '%s')]", str);
        return categoryWrapper.findElement(By.xpath(xpathC));
    }

    protected WebElement getFirstAncestorDiv(WebElement element) {
        return element.findElement(By.xpath("./ancestor::div[@class='jss140'][1]"));
    }

    // getter
    public WebElement getProductMenuWrapper() {
        return productMenuWrapper;
    }

    // actions
    public void mouseEnterMacroCategoryGroup(String str) throws Exception {

        WebElement link = findMacroCategoryGroupLink(str);
        WebElement ancestor = getFirstAncestorDiv(link);
        BrowserUtils.waitForClickable(ancestor, Constants.WaitTime.EXPLICIT_WAIT);
        BrowserUtils.hover(ancestor);
    }

    public void mouseEnterMacroCategory(String str) throws Exception {

        WebElement link = findMacroCategoryLink(str);
        WebElement ancestor = getFirstAncestorDiv(link);
        BrowserUtils.waitForClickable(ancestor, Constants.WaitTime.EXPLICIT_WAIT);
        BrowserUtils.hover(ancestor);
    }

    public void clickCategory(String str) {
        WebElement link = findCategoryLink(str);
        link.click();
    }
}
