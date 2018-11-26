package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

public class ProfilePO extends PageObject {

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_OTHER_INFO")
    private WebElement chipProfile;

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_EMAIL")
    private WebElement chipEmail;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[1]/div")
    private WebElement toast;

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_NUMBER")
    private WebElement chipNumber;

    @FindBy(how = How.ID, using ="id_taxID")
    private  WebElement codFiscale;

    @FindBy(how = How.ID_OR_NAME, using ="EDITCUSTOMER_SUBMIT")
    private  WebElement saveButton;

    public void clickChipProfile(){ chipProfile.click(); }

    public void clickChipNumber(){ chipNumber.click(); }

    public void clickCodFiscale(){
        codFiscale.click();
        codFiscale.clear();
        codFiscale.sendKeys("GSSGPP95D05B618Q");
    }

    public WebElement toast(){
        return toast;
    }

    public void clickSaveButton(){
        saveButton.click();
    }
}
