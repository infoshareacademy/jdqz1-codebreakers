package tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeLoginPage;
import pageobject.pages.HomeRegistrationPage;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    protected static WebDriver driver;

    HomeLoginPage homeLoginPage;
    private HomeRegistrationPage registrationPage;

    @BeforeClass
    public static void setUp() throws MalformedURLException {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chrome/chromedriver_Linux64");

            driver = new ChromeDriver();
    }

    @Before
    public void setUpPages() throws MalformedURLException {

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);
    }

    public HomeLoginPage getHomeLoginPage() {
        return homeLoginPage;
    }
}
