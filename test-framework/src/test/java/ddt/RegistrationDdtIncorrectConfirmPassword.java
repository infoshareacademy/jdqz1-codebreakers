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
import pageobject.pages.HomeRegistrationPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.GetRandomEmailAndPassword.GetRandomEmail.email;
import static utils.GetRandomEmailAndPassword.GetRandomPassword.password;

@RunWith(DataProviderRunner.class)
    public class
    RegistrationDdtIncorrectConfirmPassword {

        private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

        private WebDriver driver;

        private HomeRegistrationPage registrationPage;

        @DataProvider
        public static Object[][] testDataForRegistration() {
            return new String[][] {
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
                    new String[] {email, password, password.substring(0, 7)},
            };
        }

        @Before
        public void setUp() {
            driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
            driver.manage().window().maximize();
            registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);
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

            assertThat(alert).contains("Podane hasła różnią się od siebie").as("Brak komunikatu o tym, że podane hasła różnią się");
        }

        @After
        public void tearDown() {
            driver.close();
        }
}
