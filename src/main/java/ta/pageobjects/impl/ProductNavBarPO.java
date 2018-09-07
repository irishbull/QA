package ta.pageobjects.impl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import ta.pageobjects.PageObject;

public class ProductNavBarPO extends PageObject {

    @FindBy(how = How.NAME, using = "productsNavBar")
    private WebElement productsNavBar;

    public void clickOnCategoria() {
        productsNavBar.findElement(By.xpath("//*[contains(text(), 'Categoria')]")).click();
    }

    public void clickOnPorteInterne() {
        productsNavBar.findElement(By.xpath("//div[@data-filter-id='Porte interne']")).click();
    }

    public void applyFilter() {
        productsNavBar.findElement(By.xpath("//*[contains(text(), 'APPLICA')]")).click();
    }
}
