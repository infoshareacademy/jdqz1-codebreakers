package tests;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ScreenshotWhenFail.ScreenShot;
import utils.waits.CustomWait;
import java.net.MalformedURLException;
import java.util.UUID;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        String emailLogin = UUID.randomUUID().toString().replace("-", "") +"@aaa.pl";
        String hasloLogin = UUID.randomUUID().toString().replace("-", "");

        homeLoginPage.zalogujSie(emailLogin, hasloLogin);

        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponown", homeLoginPage.getTextDivAlertLogin());
    }

    // tomasz

    @Test
    public void correctLogin() {


        homeLoginPage.zalogujSie(correctEmailLogin, correctEmailPassword);
        new CustomWait(driver).waitForElementToBeVisible((By.xpath
                ("//*[contains(text(), 'Wyloguj się')]")));
        WebElement logOutButton = driver.findElement((By.xpath
                ("//*[contains(text(), 'Wyloguj się')]")));
        assertTrue(logOutButton.isDisplayed());


    }

    @Test
    public void searchSomething() {

        homeLoginPage.zalogujSie(correctEmailLogin, correctEmailPassword);

        new CustomWait(driver).waitForElementToBeVisible((By.xpath
                ("//*[contains(text(), 'Wyloguj się')]")));
        WebElement searchRTV = driver.findElement(By.xpath
                ("//*[contains(text(), 'RTV')]"));
        searchRTV.click();

        WebElement searchBar = driver.findElement(By.xpath
                ("//input[@placeholder = 'Znajdź produkt']"));
        WebElement searchButton = driver.findElement(By.xpath
                ("//*[@id=\"root\"]/div/div/div/div/div[1]/div/nav[2]/div/div/div[2]/div/form/button"));

        searchBar.sendKeys(searchPhrase);
        searchButton.click();
        List<String> records = driver.findElements(By.xpath("//div[@class='product--name']")).stream().map(WebElement::getText).collect
                (Collectors.toList());

        assertThat(records).allMatch(text -> text.contains(searchPhrase));


    }


    @AfterClass
    public static void tearDown() {
        driver.close();
    }

}
