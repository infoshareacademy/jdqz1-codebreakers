package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.waits.CustomWait;

public class HomeLoginPage {

    private WebDriver driver;

    @FindBy(xpath = "(//input[@id='formHorizontalEmail'])[1]")
    private WebElement fieldEmailLogin;

    @FindBy(xpath = "(//input[@id='formHorizontalPassword'])[1]")
    private WebElement fieldHasloLogin;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Zaloguj się')]")
    private WebElement buttonZalogujSie;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement divAlertLogin;

    public HomeLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeInEmailLogin(String emailLogin) {
        fieldEmailLogin.sendKeys(emailLogin);
    }

    public void typeInHasloLogin(String hasloLogin) {
        fieldHasloLogin.sendKeys(hasloLogin);
    }

    public void clickOnButtonZalogujSie() {
        new CustomWait(driver).waitForElementToBeClickable(buttonZalogujSie);
        buttonZalogujSie.click();
    }

    public String getTextDivAlertLogin() {
        new CustomWait(driver).waitForElementToBeVisible(divAlertLogin);
        return divAlertLogin.getText();
    }

    public void zalogujSie(String emailLogin, String hasloLogin) {
        typeInEmailLogin(emailLogin);
        typeInHasloLogin(hasloLogin);
        clickOnButtonZalogujSie();
    }
}
