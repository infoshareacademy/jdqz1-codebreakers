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
import pageobject.pages.RegistrationPageMK;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

    @RunWith(DataProviderRunner.class)
    public class RegistrationDdtIncorrectConfirmPassword {

        private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

        private WebDriver driver;

        private RegistrationPageMK registrationPage;

        /* Instead of String[] we can use Object[] or other type. */
        @DataProvider
        public static Object[][] testDataForRegistration() {
            return new String[][] {
                    new String[] {"test@test.pl", "test12345", "test1234"},
                    new String[] {"test@test.com", "test12345", "test123456"},
                    new String[] {"test@test.pl", "test123456", "test1234"},
                    new String[] {"test@test.com", "test1234567", "test123456"},
                    new String[] {"test@test.pl", "12345test", "1234test"},
                    new String[] {"test@test.com", "12345test", "test123456test"},
                    new String[] {"test2@op.pl", "test12345", "test123"},
            };
        }

        @Before
        public void setUp() {
            driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
            driver.manage().window().maximize();
            registrationPage = PageFactory.initElements(driver, RegistrationPageMK.class);
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

            WebElement alertElement = driver.findElement(By.xpath("//div[@role='alert']"));

            String alert = alertElement.getText();

            assertTrue(alert, alert.contains("Podane hasła różnią się od siebie"));
        }

        @After
        public void tearDown() {
            driver.close();
        }
}
