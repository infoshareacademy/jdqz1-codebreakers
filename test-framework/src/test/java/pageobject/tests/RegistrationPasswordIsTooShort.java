package pageobject.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.RegistrationPageMK;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;
import utils.waits.CustomWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


public class RegistrationPasswordIsTooShort {
    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";
    private RegistrationPageMK registrationPage;
    private WebDriver driver;
    private CustomWait customWait;

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
        customWait = new CustomWait(driver);
        registrationPage = PageFactory.initElements(driver, RegistrationPageMK.class);
        driver.get(PAGE_URL);

    }

    @Test
    public void register()  {
        String email = "test@test.com";
        String password = "awa";
        String confirmPassword = "awa";

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        registrationPage.clickOnRegisterTab();
        registrationPage.typeInEmail(email);
        registrationPage.typeInPassword(password);
        registrationPage.typeInConfirmPassword(confirmPassword);
        registrationPage.clickOnRegistrationButton();


        WebElement alertElement = driver.findElement(By.xpath("//div[@role = 'alert']"));

        String alert = alertElement.getText();

        //assertTrue(alert, alert.contains("Hasło musi zawierać przynajmniej 6 znaków."));
        assertThat(alert).contains("Hasło musi zawierać przynajmniej 6 znaków.").as("Brak komunikatu o tym, że hasło musi zawierać przynajmniej 6 znaków");
        
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
}
