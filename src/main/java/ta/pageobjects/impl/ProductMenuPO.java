package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class ProductMenuPO extends PageObject {

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_DESKTOP")
    @CacheLookup
    private WebElement productMenuWrapper;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORYGROUP_WRAPPER")
    private WebElement macroCategoryGroup;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER")
    private WebElement macroCategory;

    @FindBy(how = How.NAME, using = "SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER")
    private WebElement category;

    public WebElement findMacroCategoryGroupLink(String str) {
        return macroCategoryGroup.findElement(By.linkText(str));
    }

    public WebElement findMacroCategoryLink(String str) {
        String xpathMC = String.format("//div[@name='SELENIUM_PRODUCTS_MENU_MACROCATEGORY_WRAPPER']/div/div/span[contains(text(), '%s')]", str);
        return macroCategory.findElement(By.xpath(xpathMC));
    }

    public WebElement findCategoryLink(String str) {
        String xpathC = String.format("//div[@name='SELENIUM_PRODUCTS_MENU_CATEGORY_WRAPPER']/div/a/div/span[contains(text(), '%s')]", str);
        return category.findElement(By.xpath(xpathC));
    }

    // getters
    public WebElement getProductMenuWrapper() {
        return productMenuWrapper;
    }

    public WebElement getMacroCategoryGroup() {
        return macroCategoryGroup;
    }

    public WebElement getMacroCategory() {
        return macroCategory;
    }

    public WebElement getCategory() {
        return category;
    }

}
