package tests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeRegistrationPage;
import utils.StringGenerator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class RegistrationTests extends BaseTest {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private static int DOMAIN_LENGTH = 3;
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

        homeLoginPage.wylogujSie();
    }

    @Test
    public void registrationPasswordIsTooShort() {
        assertThat(registrationPage.registerReturnAlertPasswordTooShort()).contains("Hasło musi zawierać przynajmniej 6 znaków.").as
                ("Brak komunikatu o tym, że hasło musi zawierać przynajmniej 6 znaków");
    }

    @Test
    public void registerInCorrectPassword() {
        assertThat(registrationPage.registerReturnAlertIncorrectPasswords()).contains("Podane hasła różnią się od siebie").as("Brak komunikatu o tym, że " +
                "podane hasła " +
                "różnią się");
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
