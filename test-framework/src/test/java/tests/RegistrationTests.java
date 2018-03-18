package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeLoginPage;
import pageobject.pages.HomeRegistrationPage;
import utils.StringGenerator;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import static org.junit.Assert.*;


public class RegistrationTests extends BaseTest{

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private static int DOMAIN_LENGTH = 2;
    private static int PASSWORD_LENGTH = 12;

    private HomeRegistrationPage registrationPage;

    @Before
    public void setUpForTest() {

        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void registerWrongEmailCountryTest() {
        String emailLogin = "aaa@bbb." + StringGenerator.randomString(DOMAIN_LENGTH);
        String hasloLogin = StringGenerator.randomString(PASSWORD_LENGTH);

        registrationPage.zarejestrujSie(emailLogin, hasloLogin);

        assertEquals("buttonNavWylogusSie is not correct", "Wyloguj się", registrationPage.getTextButtonNavWylogujSie());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
