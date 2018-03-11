package pageobject.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.concurrent.TimeUnit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class RegistrationIncorrectConfirmPassword {
    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private RegistrationPage registrationPage;
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(PAGE_URL);

    }

    @Test
    public void registerInCorrectPassword()  {
        String email = "test@test.com";
        String password = "test12345";
        String confirmPassword = "test1234";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        registrationPage.clickOnRegisterTab();
        registrationPage.typeInEmail(email);
        registrationPage.typeInPassword(password);
        registrationPage.typeInConfirmPassword(confirmPassword);
        registrationPage.clickOnRegistrationButton();

        WebElement alertElement = driver.findElement(By.xpath("//div[@role='alert']"));

        String alert = alertElement.getText();

        //assertTrue(alert, alert.contains("Podane hasła różnią się od siebie"));
        assertThat(alert).contains("Podane hasła różnią się od siebie").as("Brak komunikatu o tym, że podane hasła różnią się");

    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

}
