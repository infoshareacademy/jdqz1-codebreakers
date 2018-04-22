package tests;

import org.junit.*;
import utils.ScreenshotWhenFail.ScreenShot;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static utils.data.Constants.*;


public class LoginTests extends BaseTest {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    @Rule
    public ScreenShot screenShot = new ScreenShot(driver);

    @Before
    public void setUpForTest() throws MalformedURLException {
        driver.get(PAGE_URL);
    }

    @Test
    public void loginWrongCredentialsTest() {
        String emailLogin = UUID.randomUUID().toString().replace("-", "") + "@aaa.pl";
        String hasloLogin = UUID.randomUUID().toString().replace("-", "");

        homeLoginPage.zalogujSie(emailLogin, hasloLogin);

        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponownie", homeLoginPage
                .getTextDivAlertLogin());
    }

    @Test
    public void correctLogin() {
        homeLoginPage.zalogujSie(correctEmailLogin, correctEmailPassword);

        assertEquals("Wyloguj się text is not correct.", "Wyloguj się", homeLoginPage.getTextLogOutButton());
    }

    @Test
    public void searchSomething() {
        List<String> textlist = new ArrayList<>();
        homeLoginPage.getProductNames().forEach(we -> {
            textlist.add(we.getText());
        });

        assertThat(textlist).allMatch(text -> text.contains(searchPhrase));
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }
}
