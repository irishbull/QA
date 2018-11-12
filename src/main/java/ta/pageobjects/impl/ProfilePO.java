package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

public class ProfilePO extends PageObject {

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_OTHER_INFO")
    private WebElement chipProfile;

    @FindBy(how = How.ID, using ="id_taxCode")
    private  WebElement codFiscale;

    @FindBy(how = How.ID_OR_NAME, using ="EDITCUSTOMER_SUBMIT")
    private  WebElement saveButton;

    public void clickChipProfile(){
        chipProfile.click();
    }

    public void clickCodFiscale(){
        codFiscale.click();
        codFiscale.clear();
        codFiscale.sendKeys("GSSGPP95D05B619O");
    }
    public void clickSaveButton(){
        saveButton.click();
    }
}
