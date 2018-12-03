package ta.pageobjects.impl;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

import java.util.Random;

public class ProfilePO extends PageObject {

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_OTHER_INFO")
    private WebElement chipProfile;

    @FindBy(how = How.ID_OR_NAME, using = "vatID")
    private WebElement partitaIva;

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_EMAIL")
    private WebElement chipEmail;

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_PASSWORD")
    private WebElement chipPassword;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[1]/div")
    private WebElement toast;


    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/main/div/div/div/div/div/div/div/div/div/div[2]/div/a/div/div/div[1]/span")
    private WebElement profiloMattonella;

    @FindBy(how = How.ID_OR_NAME, using = "PROFILE_CHIP_NUMBER")
    private WebElement chipNumber;

    @FindBy(how = How.ID, using ="id_taxID")
    private  WebElement codFiscale;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[1]/div/div/div/div[2]/div[2]/button/span[1]")
    private  WebElement saveButton;

    public void clickChipProfile(){ chipProfile.click(); }

    public void clickChipNumber(){ chipNumber.click(); }

    public void clickPassword(){ chipPassword.click(); }

    public void clickPartitaIva(){ partitaIva.click(); }

    public void sendPartitaIva(){
        partitaIva.clear();
        partitaIva.sendKeys(randomPartitaIva().toString());
    }

    public void clickProfiloMattonella(){ profiloMattonella.click(); }

    public void clickCodFiscale(){
        codFiscale.click();
        codFiscale.clear();
        codFiscale.sendKeys(randomCode().toString()+"95D05B618Q");
    }

    private static StringBuilder randomCode() {
        final String ALPHABET = "BCDFGHJKLMNPQRSTVWXYZ";
        Random rnd = new Random(System.currentTimeMillis());
        final int LENGHT = 6;
        StringBuilder sb = new StringBuilder(LENGHT);
        for (int i = 0; i < LENGHT; i++) {
            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        return sb;
    }

    private static StringBuilder randomPartitaIva() {
        final String ALPHABET = "0123456789";
        Random rnd = new Random(System.currentTimeMillis());
        final int LENGHT = 11;
        StringBuilder sb = new StringBuilder(LENGHT);
        for (int i = 0; i < LENGHT; i++) {
            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        return sb;
    }
    private static StringBuilder randomcap() {
        final String ALPHABET = "0123456789";
        Random rnd = new Random(System.currentTimeMillis());
        final int LENGHT = 5;
        StringBuilder sb = new StringBuilder(LENGHT);
        for (int i = 0; i < LENGHT; i++) {
            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        return sb;
    }
    public WebElement toast(){
        return toast;
    }

    public void clickSaveButton(){
        saveButton.click();
    }
}
