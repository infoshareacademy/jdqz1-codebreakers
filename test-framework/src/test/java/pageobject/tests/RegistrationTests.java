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

import static org.junit.Assert.*;


public class RegistrationTests {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    private WebDriver driver;

    private HomeLoginPage homeLoginPage;
    private HomeRegistrationPage registrationPage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void registerWrongEmailCountryTest() {
        String emailLogin = "aaa@bbb.sdfsdf";
        String hasloLogin = "asdfadsfasdfasfasfasadd";

        registrationPage.zarejestrujSie(emailLogin, hasloLogin);

        assertEquals("buttonNavWylogusSie is not correct", "Wyloguj się", registrationPage.getTextButtonNavWylogujSie());
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
