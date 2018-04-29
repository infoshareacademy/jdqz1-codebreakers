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


public class RegistrationPasswordIsTooShort extends BaseTest{
    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private HomeRegistrationPage registrationPage;

    @Before
    public void setUpForTest() {

        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    public void register()  {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        registrationPage.clickOnRegisterTab();
        registrationPage.typeInEmail(email);
        registrationPage.typeInPassword(password.substring(0, 3));
        registrationPage.typeInConfirmPassword(password.substring(0, 3));
        registrationPage.clickOnRegistrationButton();

        WebElement alertElement = driver.findElement(By.xpath("//div[@role = 'alert']"));

        String alert = alertElement.getText();

        assertThat(alert).contains("Hasło musi zawierać przynajmniej 6 znaków.").as
                ("Brak komunikatu o tym, że hasło musi zawierać przynajmniej 6 znaków");
        
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
}
