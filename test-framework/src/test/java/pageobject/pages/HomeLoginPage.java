package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLoginPage {

    @FindBy(xpath = "(//input[@id='formHorizontalEmail'])[1]")
    private WebElement fieldEmailLogin;

    @FindBy(xpath = "(//input[@id='formHorizontalPassword'])[1]")
    private WebElement fieldHasloLogin;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Zaloguj \" +\n" +
            "            \"się')]\"")
    private WebElement buttonZalogujSie;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement divAlertLogin;

    public void typeInEmailLogin(String emailLogin) {
        fieldEmailLogin.sendKeys(emailLogin);
    }

    public void typeInHasloLogin(String hasloLogin) {
        fieldHasloLogin.sendKeys(hasloLogin);
    }

    public void clickOnButtonZalogujSie() {
        buttonZalogujSie.click();
    }

    public String getTextDivAlertLogin() {
        return divAlertLogin.getText();
    }

    public void zalogujSie(String emailLogin, String hasloLogin) {
        fieldEmailLogin.sendKeys(emailLogin);
        fieldHasloLogin.sendKeys(hasloLogin);
        buttonZalogujSie.click();
    }
}
