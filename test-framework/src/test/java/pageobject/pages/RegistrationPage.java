package pageobject.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GetRandomEmailAndPassword.GetRandomPassword;


public class RegistrationPage {
    GetRandomPassword password = new GetRandomPassword();

    @FindBy(xpath = "//a[@id='noanim-tab-example-tab-2']")
    private WebElement registrationTab;

    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement fieldEmail;

    @FindBy(xpath = "//input[@name = 'password']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//input[@name = 'confirmPassword']")
    private WebElement fieldConfirmPassword;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement registrationButton;

    public void clickOnRegisterTab() {
        registrationTab.click();
    }

    public void typeInEmail(String email) {
        fieldEmail.sendKeys(email);
    }

    public void typeInPassword(String password) { fieldPassword.sendKeys(password); }

    public void typeInConfirmPassword(String confirmPassword) {
        fieldConfirmPassword.sendKeys(confirmPassword);
    }

    public void clickOnRegistrationButton() {
        registrationButton.click();
    }






}
