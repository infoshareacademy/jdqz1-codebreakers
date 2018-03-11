package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.waits.CustomWait;

import java.util.List;

public class HomeSearchPage {

    private CustomWait customWait;

    @FindBy(xpath = "//div[@class='form-group']//input[@type='text']")
    private WebElement fieldZnajdzProdukt;

    @FindBy(xpath = "//form[@class='search--form']//button[@type='submit']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//div[@class='product--container']")
    private List <WebElement> resultsList;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Filtruj')]")
    private WebElement buttonFiltruj;

    @FindBy(xpath = "(//input[@id='formHorizontalEmail'])[1]")
    private WebElement fieldEmailLogin;

    @FindBy(xpath = "(//input[@id='formHorizontalPassword'])[1]")
    private WebElement fieldHasloLogin;

    @FindBy(xpath = "//button[@type='submit'][contains(text(),'Zaloguj się')]")
    private WebElement buttonZalogujSie;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement divAlertLogin;

    public HomeSearchPage(WebDriver driver) {
        customWait = new CustomWait(driver);
    }

    public List<WebElement> getResultsList() {
        return resultsList;
    }

    public void zalogujSie(String emailLogin, String hasloLogin, String search) {
        fieldEmailLogin.sendKeys(emailLogin);
        fieldHasloLogin.sendKeys(hasloLogin);
        customWait.waitForElementToBeClickable(buttonZalogujSie);
        buttonZalogujSie.click();
        customWait.waitForElementToBeVisible(fieldZnajdzProdukt);
        fieldZnajdzProdukt.sendKeys(search);
        buttonSearch.click();
        customWait.waitForElementToBeVisible(buttonFiltruj);
    }
}
