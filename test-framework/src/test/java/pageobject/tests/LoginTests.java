package pageobject.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeLoginPage;
import pageobject.pages.HomeRegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoginTests {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    private WebDriver driver;

    private HomeLoginPage homeLoginPage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        driver.get(PAGE_URL);
    }

    @Test
    public void loginWrongCredentialsTest() {
        String emailLogin = UUID.randomUUID().toString().replace("-", "") +"@aaa.pl";
        String hasloLogin = UUID.randomUUID().toString().replace("-", "");

        homeLoginPage.zalogujSie(emailLogin, hasloLogin);

        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponownie", homeLoginPage.getTextDivAlertLogin());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
