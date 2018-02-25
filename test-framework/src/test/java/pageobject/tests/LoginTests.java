package pageobject.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeLoginPage;
import pageobject.pages.HomeRegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import static org.junit.Assert.*;


public class LoginTests {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    private WebDriver driver;

    private HomeLoginPage homeLoginPage;
    private HomeRegistrationPage registrationPage;
    private CustomWait customWait;


    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void loginWrongCredentialsTest() {
        String emailLogin = "qwerdqwefafaf@sadfasf.safas";
        String hasloLogin = "asdfadsfasdfasfasfasadd";

        homeLoginPage.typeInEmailLogin(emailLogin);
        homeLoginPage.typeInHasloLogin(hasloLogin);
        homeLoginPage.clickOnButtonZalogujSie();

        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponownie", homeLoginPage.getTextDivAlertLogin());
    }

    @Test
    public void loginWrongCredentialsBetterTest() {
        String emailLogin = "qwerdqwefafaf@sadfasf.safas";
        String hasloLogin = "asdfadsfasdfasfasfasadd";

        homeLoginPage.zalogujSie(emailLogin, hasloLogin);

        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponownie", homeLoginPage.getTextDivAlertLogin());
    }

    // tomasz

    @Test
    public void correctLogin() {
        String correctEmailLogin = "12345@123.pl";
        String correctEmailPassword = "123456";


        homeLoginPage.zalogujSie(correctEmailLogin, correctEmailPassword);
//        WebElement loginError = driver.findElement(By.xpath("//*[@id=\"noanim-tab-example-pane-1\"]/div/form/div[5]"));
//        assertFalse(loginError.getText().contains("Nieprawidłowe dane logowania. Spróbuj ponownie"));

        new CustomWait(driver).waitForElementToBeVisible(driver.findElement((By.xpath
                ("//*[@id=\"root\"]/div/div/div/div/div[1]/div/nav[1]/div/div[2]/p/a[2]"))));
        WebElement logOutButton = driver.findElement((By.xpath
                ("//*[@id=\"root\"]/div/div/div/div/div[1]/div/nav[1]/div/div[2]/p/a[2]")));

        assertTrue(logOutButton.isDisplayed());



        }



//    @After
//    public void tearDown() {
//        driver.close();
//    }

}