package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomeRegistrationPage {

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

    public void clickOnButtonNavZarejestruj() {
        buttonNavZarejestruj.click();
    }

    public void typeInFieldEmailRegister(String emailRegister) {
        fieldEmailRegister.sendKeys(emailRegister);
    }

    public void typeInFieldFirstPasswordRegister(String password) {
        fieldFirstPasswordRegister.sendKeys(password);
    }

    public void typeInFieldSecondPasswordRegister(String password) {
        fieldSecondPasswordRegister.sendKeys(password);
    }

    public void clickOnButtonZarejestrujSie() {
        buttonZarejestrujSie.click();
    }

    public String getTextButtonNavWylogujSie() {
        return buttonNavWylogujSie.getText();
    }

    public void zarejestrujSie(String emailRegister, String password) {
        buttonNavZarejestruj.click();
        fieldEmailRegister.sendKeys(emailRegister);
        fieldFirstPasswordRegister.sendKeys(password);
        fieldSecondPasswordRegister.sendKeys(password);
        buttonZarejestrujSie.click();
    }
}