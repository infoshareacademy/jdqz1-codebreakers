package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeRegistrationPage;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GetRandomEmailAndPassword.GetRandomEmail.email;
import static utils.GetRandomEmailAndPassword.GetRandomPassword.password;

public class RegistrationIncorrectConfirmPassword extends BaseTest {
    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private HomeRegistrationPage registrationPage;

    @Before
    public void setUpForTest() {

        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void registerInCorrectPassword()  {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        registrationPage.clickOnRegisterTab();
        registrationPage.typeInEmail(email);
        registrationPage.typeInPassword(password);
        registrationPage.typeInConfirmPassword(password.substring(0, 7));
        registrationPage.clickOnRegistrationButton();

        WebElement alertElement = driver.findElement(By.xpath("//div[@role='alert']"));

        String alert = alertElement.getText();

        assertThat(alert).contains("Podane hasła różnią się od siebie").as("Brak komunikatu o tym, że podane hasła różnią się");

    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }

}
