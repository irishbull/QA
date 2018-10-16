package ta.pageobjects.impl;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ta.pageobjects.PageObject;


public class RegisterPO extends PageObject {

    @FindBy(how = How.NAME, using = "_REGISTRATI")
    private WebElement registrati;

    @FindBy(how = How.ID_OR_NAME, using = "_gender")
    private WebElement gender;
    @FindBy(how = How.ID, using = "menu-gender")
    private WebElement menugender;

    @FindBy(how = How.NAME, using = "_PRIVATO")
    private WebElement privato;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[3]/div[2]/ul/li[1]")
    private WebElement male;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[4]/div[1]/div")
    private WebElement rifiuto;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[1]/div[1]/div")
    private WebElement accettaTermini;

    @FindBy(how = How.XPATH, xpath = "/html/body/div[1]/div/div[6]/div/div[14]/div/div[2]/div[1]/div")
    private WebElement profiloCheck;

    @FindBy(how = How.ID_OR_NAME, using = "name")
    private WebElement name;

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
    public void setProfiling(){
        profiling.click();
    }
    public void concludiRegistrazione(){
        concludiRegistrazione.click();
    }

    public void registratiClick(){ registrati.click();}

    public void accettaTermini(){
        accettaTermini.click();
    }

    public void selPrivatoClick(){
        privato.click();
    }

    public void continueClick(){
        prosegui.click();
    }

    public void genderClick(){
        gender.click();
    }


    public void maleClick(){
        WebElement genderSelected = menugender.findElement(By.xpath(".//div/ul/li[@data-value=\"MALE\"]"));
            genderSelected.click();
    }

    public void clickrifiuto(){
        rifiuto.click();
    }

    public void profilo(){
        profiloCheck.click();
    }
    public void selectStore(){
        storeCode.click();
        livornoShop.click();

    }

    public void enterUsernameAndPassword(String nome, String cognome,  String email, String password ,String telefono, String cap) throws Exception {
        this.name.sendKeys(nome);
        this.cognome.sendKeys(cognome);
        this.telefono.sendKeys(telefono);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.cap.sendKeys(cap);
    }


    /*
    protected WebElement findCategoryLink(String str) {
        String xpathC = String.format(".//div/a/div/span[contains(text(), '%s')]", str);
        BrowserUtils.waitFor(categoryWrapper, Constants.WaitTime.EXPLICIT_WAIT);
        return categoryWrapper.findElement(By.xpath(xpathC));
    }*/




}
