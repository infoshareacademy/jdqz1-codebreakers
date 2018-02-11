package basics;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.waits.CustomWait;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Module2_SeleniumFeatures {

    private final static String PAGE_HEADER_TEXT = "Selenium: Beginners Guide";
    private final static String CONFIRMATION_BOX_MESSAGE = "This is double click";
    private final static String SCREENSHOT_FILE_PATH = "Screenshot/screenshot.png";

    private WebDriver driver;


    @Before
    public void setUp() {
        /*
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();
        */
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void verifyTextOnPage() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement pageHeader = driver.findElement(By.xpath("//div[@class= 'mainheading']"));

        // This is a good practice to add negative message showing reason why test fails.
        assertEquals("Page header is incorrect.", PAGE_HEADER_TEXT, pageHeader.getText());
    }

    @Test
    public void doubleClickOnButtonTest() {
        driver.get("http://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        WebElement buttonToDoubleClick = driver.findElement(By.xpath("//input[contains(@value,'Double')]"));

        Actions builder = new Actions(driver);
        builder.doubleClick(buttonToDoubleClick).build().perform();

        WebElement confirmationBox = driver.findElement(By.id("box"));

        assertEquals("Button was not double clicked.", CONFIRMATION_BOX_MESSAGE, confirmationBox.getText());
    }

    @Test
    public void dragAndDropTest() {
        driver.get("http://marcojakob.github.io/dart-dnd/basic/web/");

        List<WebElement> listOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        WebElement firstDocument = listOfDocuments.get(0);
        WebElement trash = driver.findElement(By.xpath("//div[@class = 'trash']"));

        Actions builder = new Actions(driver);
        builder.dragAndDrop(firstDocument, trash).build().perform();

        // After removing elements list needs to be refreshed.
        List<WebElement> refreshedListOfDocuments = driver.findElements(By.xpath("//img[@class = 'document']"));

        CustomWait customWait = new CustomWait(driver);
        customWait.waitForDocumentDisappear(refreshedListOfDocuments);

        assertEquals("Document has not been deleted.", 3, refreshedListOfDocuments.size());
    }

    @Test
    public void javaScriptCallTest() {
        driver.get("http://www.google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = (String) js.executeScript("return document.title");

        assertEquals("Google", title);

        long links = (Long) js.executeScript("var links = document.getElementsByTagName ('A'); return links.length");

        assertEquals("Wrong number of links.",47, links);
    }

    @Test
    public void screenShotTest() {
        driver.get("http://book.theautomatedtester.co.uk/chapter1");
        WebElement pageHeader = driver.findElement(By.xpath("//div[@class= 'mainheading']"));

        try {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Page header is incorrect.", PAGE_HEADER_TEXT, pageHeader.getText());
    }

    @Test
    public void selectFromDropDownTest() {
        driver.get("http://demoqa.com/registration/");

        Select dropDownCountry = new Select(driver.findElement(By.id("dropdown_7")));

        // Verify Dropdown does not support multiple selection
        assertFalse("Dropdown supports multiple choice.", dropDownCountry.isMultiple());

        // Verify Dropdown has 204 options for selection
        assertEquals("There are no 204 countries on the list.", 204, dropDownCountry.getOptions().size());

        dropDownCountry.selectByVisibleText("Poland");
        assertEquals("Poland is not selected country.", "Poland", dropDownCountry.getFirstSelectedOption().getText());
    }

    @Test
    public void setRadioButtonTest() {
        driver.get("http://demoqa.com/registration/");

        WebElement singleRadioButton = driver.findElement(By.xpath("//label[text() = 'Single']/following-sibling::input[1]"));

        if (!singleRadioButton.isSelected())
            singleRadioButton.click();

        assertTrue("Radio button is not selected", singleRadioButton.isSelected());
    }

    @Test
    public void setCheckBoxTest() {
        driver.get("http://demoqa.com/registration/");

        WebElement danceCheckBox = driver.findElement(By.xpath("//input[@value = 'dance']"));

        if (!danceCheckBox.isSelected())
            danceCheckBox.click();

        assertTrue("Checkbox is not selected", danceCheckBox.isSelected());
    }

    @Test
    public void registerDemoqa() {
        driver.get("http://demoqa.com/registration/");

        WebElement fieldName = driver.findElement(By.xpath("//input[@id='name_3_firstname']"));

        fieldName.sendKeys("Name");

        WebElement checkFieldName = driver.findElement(By.xpath("//input[@id='name_3_firstname']"));
        assertEquals("Name", checkFieldName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.xpath("//input[@id='name_3_lastname']"));
        fieldLastName.sendKeys("Lastname");

        WebElement clickSingleRadioButton = driver.findElement(By.xpath("//input[@value='single']"));
        clickSingleRadioButton.click();
    }

    @Test
    public void priceLessRegisterWrongEmailCountry() {
        driver.get("http://app.codebreakers.jdqz1.is-academy.pl/");

        waitCustom();

        WebElement buttonNavZarejestruj = driver.findElement(By.xpath("//a[@id='noanim-tab-example-tab-2']"));
        buttonNavZarejestruj.click();

        waitCustom();

        WebElement fieldEmailRegister = driver.findElement(By.xpath("(//input[@id='formHorizontalEmail'])[2]"));

        waitCustom();

        String randomStringTwoChar;
        randomStringTwoChar = randomString();

        fieldEmailRegister.sendKeys("aaa@bbb." + randomStringTwoChar);

        WebElement fieldFirstPasswordRegister = driver.findElement(By.xpath("(//div[2]/input[1])[2]"));
        fieldFirstPasswordRegister.sendKeys("123456");

        waitCustom();

        WebElement fieldSecondPasswordRegister = driver.findElement(By.xpath("//div[3]/input[1]"));
        fieldSecondPasswordRegister.sendKeys("123456");

        waitCustom();

        WebElement buttonZarejestrujSie = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Zarejestruj się')]"));
        buttonZarejestrujSie.click();

        waitCustom();

        WebElement buttonNavWylogujSie = driver.findElement(By.xpath("//a[@href='/'][contains(text(),'Wyloguj się')]"));
        assertEquals("buttonNavWylogusSie is not correct", "Wyloguj się", buttonNavWylogujSie.getText());
    }

    @Test
    public void priceLessLoginWrongCredentials() {

        driver.get("http://app.codebreakers.jdqz1.is-academy.pl/");

        waitCustom();

        WebElement fieldEmailLogin = driver.findElement(By.xpath("(//input[@id='formHorizontalEmail'])[1]"));
        fieldEmailLogin.sendKeys("qwerdqwefafaf@sadfasf.safas");

        WebElement fieldHasloLogin = driver.findElement(By.xpath("(//input[@id='formHorizontalPassword'])[1]"));
        fieldHasloLogin.sendKeys("asdfadsfasdfasfasfasadd");

        WebElement buttonZalogujSie = driver.findElement(By.xpath("//button[@type='submit'][contains(text(),'Zaloguj " +
                "się')]"));
        buttonZalogujSie.click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement divAlert = driver.findElement(By.xpath("//div[@role='alert']"));
        assertEquals("Alert text is not correct.", "Nieprawidłowe dane logowania. Spróbuj ponownie", divAlert.getText());
    }

    @After
    public void tearDown() {
        // driver.close();
            if (driver != null) {
                driver.quit();
            }
    }

    private void waitCustom() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected String randomString() {
        String SALTCHARS = "abcdefghijklmnopqrstqwxyz";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}
