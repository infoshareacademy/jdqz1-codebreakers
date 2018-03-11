package ddt;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.RegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class RegistrationDdtPasswordIsTooShort {
    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    private WebDriver driver;

    private RegistrationPage registrationPage;

    /* Instead of String[] we can use Object[] or other type. */
    @DataProvider
    public static Object[][] testDataForRegistration() {
        return new String[][] {
                new String[] {"test@test.pl", "awa", "awa"},
                new String[] {"test@test.com", "aaa", "aaa"},
                new String[] {"test@test.pl", "ba", "ba"},
                new String[] {"test@test.com", "bucc", "bucc"},
                new String[] {"test@test.pl", "ttt", "ttt"},
                new String[] {"test@test.com", "ux", "ux"},
                new String[] {"test2@op.pl", "a", "a"},
        };
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
        registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        driver.get(PAGE_URL);
    }

    @Test
    @UseDataProvider("testDataForRegistration")
    public void registerNewUserTest(String email, String password, String confirmPassword) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        registrationPage.clickOnRegisterTab();
        registrationPage.typeInEmail(email);
        registrationPage.typeInPassword(password);
        registrationPage.typeInConfirmPassword(confirmPassword);
        registrationPage.clickOnRegistrationButton();


        WebElement alertElement = driver.findElement(By.xpath("//div[@role = 'alert']"));

        String alert = alertElement.getText();

        assertTrue(alert, alert.contains("Hasło musi zawierać przynajmniej 6 znaków."));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
