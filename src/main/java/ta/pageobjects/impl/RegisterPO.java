package ta.pageobjects.impl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;

import java.io.UnsupportedEncodingException;


public class RegisterPO extends PageObject {

    @FindBy(how = How.NAME, using = "_REGISTRATI")
    private WebElement registrati;

    @FindBy(how = How.ID_OR_NAME, using = "_gender")
    private WebElement gender;

    @FindBy(how = How.ID, using = "menu-gender")
    private WebElement menugender;

    @FindBy(how = How.XPATH, xpath = "(\"(.//*[normalize-space(text()) and normalize-space(.)='Privato'])[1]/following::div[3]\"))")
    private WebElement privato;

    @FindBy (how = How.ID, using ="id_organizationName")
    private WebElement ragioneSociale;

    @FindBy (how = How.ID_OR_NAME, using ="menu-sector")
    private WebElement menuSector;

    @FindBy(how = How.NAME, using = "_AZIENDA")
    private WebElement azienda;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[2]/div/form/div[3]/div[1]/div[2]/div/div/label")
    private WebElement forma;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div/div[5]/button/span[1]")
    private WebElement goProfile;

    @FindBy(how = How.ID_OR_NAME, using="_sector")
    private WebElement settore;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[3]/div[2]/ul/li[1]")
    private WebElement male;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[4]/div[1]/div")
    private WebElement rifiuto;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[1]/div[1]/div")
    private WebElement accettaTermini;


    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[4]/div[2]/div")
    private WebElement accettaButton;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[2]/div[1]/div")
    private WebElement profiloCheck;


    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[4]/div[1]/div")
    private WebElement promo;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[6]/div[1]/div")
    private WebElement directMarketing;


    @FindBy(how = How.ID_OR_NAME, using = "name")
    private WebElement nome;

    @FindBy(how = How.ID_OR_NAME, using = "emailAddress")
    private WebElement email;

    @FindBy(how = How.ID_OR_NAME, using = "password")
    private WebElement password;

    @FindBy(how = How.ID_OR_NAME, using = "_CONTINUE")
    private WebElement prosegui;

    @FindBy(how = How.ID_OR_NAME, using ="id_surname")
    private WebElement cognome;

    @FindBy(how = How.ID_OR_NAME, using ="id_phone1")
    private WebElement telefono;

    @FindBy(how = How.ID_OR_NAME, using ="zipCode")
    private WebElement cap;

    @FindBy(how = How.ID_OR_NAME, using ="_storeCode")
    private WebElement storeCode;

    @FindBy(how = How.ID_OR_NAME, using="storeCode_33")
    private WebElement livornoShop;

    @FindBy(how = How.ID_OR_NAME, using="_termsConditions")
    private WebElement termsConditions;


    @FindBy(how = How.ID_OR_NAME, using="_REGISTER")
    private WebElement concludiRegistrazione;

    @FindBy(how = How.ID_OR_NAME, using="_profiling")
    private WebElement profiling;

    public void checkconditions(){termsConditions.click();}

    public void accetta(){accettaButton.click();}

    public void profilingClick(){
        profiling.click();
    }


    public void concludiRegistrazione(){
        concludiRegistrazione.click();
    }

    public void registratiClick(){ registrati.click();}


    public void accettaTermini(){ accettaTermini.click(); }

    public void selPrivatoClick(){
        privato.click();
    }

    public void aziendaClick(){ azienda.click(); }

    public WebElement setAzienda () {
        return azienda;
    }

    public void settoreClick(){ settore.click(); }

    public void continueClick(){ prosegui.click(); }

    public void goProfileButton(){ goProfile.click(); }

    public void genderClick(){ gender.click(); }

    public void maleClick(){
        WebElement genderSelected = menugender.findElement(By.xpath(".//div/ul/li[@data-value=\"MALE\"]"));
            genderSelected.click();
    }

    public void clickrifiuto(){
        rifiuto.click();
    }

    public void accettaProfilazione(){ profiloCheck.click(); }

    public void accettaPromo(){ promo.click(); }

    public void accettaDirectMarketing(){ directMarketing.click(); }

    public void selectStore(){
        storeCode.click();
        livornoShop.click();
    }

/*
   public String converterToUtf(String toEncode) throws UnsupportedEncodingException {
        return new String (toEncode.getBytes(), "UTF-8");
 }*/

    public void enterUsernameAndPassword(String nome, String cognome, String email, String password , String telefono, String cap) throws UnsupportedEncodingException {
        this.nome.click();
        this.nome.clear();
        this.nome.sendKeys(nome);
        this.cognome.click();
        this.cognome.clear();
        this.cognome.sendKeys(cognome);
        this.telefono.click();
        this.telefono.clear();
        this.telefono.sendKeys(telefono);
        this.email.click();
        this.email.clear();
        this.email.sendKeys(email);
        this.password.click();
        this.password.sendKeys(password);
        this.cap.click();
        this.cap.clear();
        this.cap.sendKeys(cap);
    }

    public void enterForCompany(String ragioneSociale,String nome, String cognome, String email, String password , String telefono, String cap) throws UnsupportedEncodingException {
        this.ragioneSociale.click();
        this.ragioneSociale.clear();
        this.ragioneSociale.sendKeys(ragioneSociale);
        this.nome.click();
        this.nome.clear();
        this.nome.sendKeys(nome);
        this.cognome.click();
        this.cognome.clear();
        this.cognome.sendKeys(cognome);
        this.telefono.click();
        this.telefono.clear();
        this.telefono.click();
        this.telefono.clear();
        this.telefono.sendKeys(telefono);
        this.email.click();
        this.email.clear();
        this.email.sendKeys(email);
        this.password.click();
        this.password.clear();
        this.password.sendKeys(password);
        this.cap.clear();
        this.cap.click();
        this.cap.sendKeys(cap);
    }

}
