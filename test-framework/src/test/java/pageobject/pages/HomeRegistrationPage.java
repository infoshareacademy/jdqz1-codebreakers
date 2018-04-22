package pageobject.pages;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GetRandomEmailAndPassword.GetRandomPassword.password;
import utils.waits.CustomWait;

import java.util.concurrent.TimeUnit;

import static utils.GetRandomEmailAndPassword.GetRandomEmail.email;

public class HomeRegistrationPage {

    private WebDriver driver;

    private CustomWait customWait;

    @FindBy(xpath = "//a[@id='noanim-tab-example-tab-2']")
    private WebElement buttonNavZarejestruj;

    @FindBy(xpath = "(//input[@id='formHorizontalEmail'])[2]")
    private WebElement fieldEmailRegister;

    @FindBy(xpath = "(//div[2]/input[1])[2]")
    private WebElement fieldFirstPasswordRegister;

    @FindBy(xpath = "//div[3]/input[1]")
    private WebElement fieldSecondPasswordRegister;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Zarejestruj się')]")
    private WebElement buttonZarejestrujSie;

    @FindBy(xpath = "//a[@href='/'][contains(text(),'Wyloguj się')]")
    private WebElement buttonNavWylogujSie;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertElement;

    public HomeRegistrationPage(WebDriver driver) {
        this.driver = driver;
        customWait = new CustomWait(driver);
    }

    private void clickOnButtonNavZarejestruj() {
        buttonNavZarejestruj.click();
    }

    private void typeInFieldEmailRegister(String emailRegister) {
        fieldEmailRegister.sendKeys(emailRegister);
    }

    private void typeInFieldFirstPasswordRegister(String password) {
        fieldFirstPasswordRegister.sendKeys(password);
    }

    private void typeInFieldSecondPasswordRegister(String password) {
        fieldSecondPasswordRegister.sendKeys(password);
    }

    private void clickOnButtonZarejestrujSie() {
        buttonZarejestrujSie.click();
    }

    public String getTextButtonNavWylogujSie() {
        customWait.waitForElementToBeVisible(buttonNavWylogujSie);
        return buttonNavWylogujSie.getText();
    }

    public void zarejestrujSie(String emailRegister, String password) {
        customWait.waitForElementToBeClickable(buttonNavZarejestruj);
        clickOnButtonNavZarejestruj();
        customWait.waitForElementToBeVisible(fieldEmailRegister);
        typeInFieldEmailRegister(emailRegister);
        typeInFieldFirstPasswordRegister(password);
        typeInFieldSecondPasswordRegister(password);
        customWait.waitForElementToBeClickable(buttonZarejestrujSie);
        clickOnButtonZarejestrujSie();
        customWait.waitForElementToBeVisible(buttonNavWylogujSie);
    }

    public void clickOnRegisterTab() {
        buttonNavZarejestruj.click();
    }

    public void typeInEmail(String email) {
        fieldEmailRegister.sendKeys(email);
    }

    public void typeInPassword(String password) { fieldFirstPasswordRegister.sendKeys(password); }

    public void typeInConfirmPassword(String confirmPassword) {
        fieldSecondPasswordRegister.sendKeys(confirmPassword);
    }

    public void clickOnRegistrationButton() {
        buttonZarejestrujSie.click();
    }

    public String registerReturnAlertIncorrectPasswords() {
        customWait.waitForElementToBeClickable(buttonNavZarejestruj);
        clickOnRegisterTab();
        customWait.waitForElementToBeVisible(fieldEmailRegister);
        typeInEmail(email);
        typeInPassword(password);
        typeInConfirmPassword(password.substring(0, 7));
        clickOnRegistrationButton();
        return alertElement.getText();
    }

    @Test
    public String registerReturnAlertPasswordTooShort()  {
        customWait.waitForElementToBeClickable(buttonNavZarejestruj);
        clickOnRegisterTab();
        customWait.waitForElementToBeVisible(fieldEmailRegister);
        typeInEmail(email);
        typeInPassword(password.substring(0, 3));
        typeInConfirmPassword(password.substring(0, 3));
        clickOnRegistrationButton();
        customWait.waitForElementToBeVisible(alertElement);
        return alertElement.getText();
    }
}
