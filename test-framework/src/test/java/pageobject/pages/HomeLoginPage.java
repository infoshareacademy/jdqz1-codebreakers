package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.waits.CustomWait;
import static utils.data.Constants.*;

import java.util.List;

public class HomeLoginPage {

    private CustomWait customWait;

    @FindBy(xpath = "(//input[@id='formHorizontalEmail'])[1]")
    private WebElement fieldEmailLogin;

    @FindBy(xpath = "(//input[@id='formHorizontalPassword'])[1]")
    private WebElement fieldHasloLogin;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Zaloguj się')]")
    private WebElement buttonZalogujSie;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement divAlertLogin;

    @FindBy(xpath = "//*[contains(text(), 'Wyloguj się')]")
    private WebElement logOutButton;

    @FindBy(xpath = "//*[contains(text(), 'RTV')]")
    private WebElement searchRTV;

    @FindBy(xpath = "//input[@placeholder = 'Znajdź produkt']")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='product--name']")
    private List <WebElement> productNames;

    public HomeLoginPage(WebDriver driver) {
        customWait = new CustomWait(driver);
    }

    private void typeInEmailLogin(String emailLogin) {
        fieldEmailLogin.sendKeys(emailLogin);
    }

    private void typeInHasloLogin(String hasloLogin) {
        fieldHasloLogin.sendKeys(hasloLogin);
    }

    private void clickOnButtonZalogujSie() {
        customWait.waitForElementToBeClickable(buttonZalogujSie);
        buttonZalogujSie.click();
    }

    public String getTextDivAlertLogin() {
        customWait.waitForElementToBeVisible(divAlertLogin);
        return divAlertLogin.getText();
    }

    public void zalogujSie(String emailLogin, String hasloLogin) {
        typeInEmailLogin(emailLogin);
        typeInHasloLogin(hasloLogin);
        clickOnButtonZalogujSie();
    }

    public void wylogujSie() {
        logOutButton.click();
    }

    public WebElement getLogOutButton() {
        return logOutButton;
    }

    public String getTextLogOutButton() {
        customWait.waitForElementToBeVisible(logOutButton);
        return logOutButton.getText();
    }

    public List<WebElement> getProductNames() {
        customWait.waitForElementToBeVisible(logOutButton);
        searchRTV.click();
        searchBar.sendKeys(searchPhrase);
        searchButton.click();
        return productNames;
    }
}
