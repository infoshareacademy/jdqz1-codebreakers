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


public class RegistrationTest {

    private static final String PAGE_URL = "http://newtours.demoaut.com";

    private WebDriver driver;

    private HomeLoginPage homeLoginPage;
    private HomeRegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);
        registrationConfirmationPage = PageFactory.initElements(driver, RegistrationConfirmationPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void registerNewUserTest() {
        String userName = "jan12345";
        String password = "pass12345";
        String firstName = "Jan";
        String lastName = "Nowak";
        String phoneNumber = "505505505";
        String email = "jannowak@gmail.com";
        String address1 = "ul. Grunwaldzka 452";
        String address2 = "Mieszkanie nr 12";
        String city = "Gdansk";
        String state = "Pomorskie";
        String zipCode = "12345";
        String country = "POLAND";

        homeLoginPage.clickOnRegisterLink();
        registrationPage.inputContactInformationForm(firstName, lastName,
                phoneNumber, email);
        registrationPage.inputMailingInformationForm(address1, address2, city,
                state, zipCode, country);
        registrationPage.inputUserInformationForm(userName, password, password);
        registrationPage.clickOnSubmitButton();
        registrationConfirmationPage.clickOnSignInLink();
        loginPage.userLogin(userName, password);

        assertTrue("User is not logged in.", homeLoginPage.isUserIsLoggedIn());
    }

    @After
    public void tearDown() {
        driver.close();
    }

}
