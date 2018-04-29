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

    protected WebDriver driver;

    protected HomeLoginPage homeLoginPage;
    protected HomeRegistrationPage registrationPage;

    @Before
    public void setUp() throws MalformedURLException {

        System.setProperty("webdriver.chrome.driver",
                "/home/miloszwozniak/projects/jdqz1-codebreakers/test-framework/src/test/resources/drivers/chrome/chromedriver_Linux64");
        String buildEnv = System.getProperty("buildEnv");

        if(buildEnv.equals("CI")){
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), new DesiredCapabilities());
        }
        if(buildEnv.equals("DEV")){
            driver = new ChromeDriver();
        }

        homeLoginPage = PageFactory.initElements(driver, HomeLoginPage.class);
        registrationPage = PageFactory.initElements(driver, HomeRegistrationPage.class);

    }

}
