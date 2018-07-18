package ddt;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.HomeSearchPage;
import utils.driver.WebDriverCreators;
import utils.driver.WebDriverProvider;

@RunWith(DataProviderRunner.class)
public class PricelessDDTWithTngTech {

    private static final String PAGE_URL = "http://app.codebreakers.jdqz1.is-academy.pl/";

    private WebDriver driver;
    private HomeSearchPage homeSearchPage;

    @DataProvider
    public static Object[][] pricelessSearchSingleWordTest() {
        return new String[][] {
                new String[] {"aaa@aaa.pl", "123456", "Nike", "2"},
                new String[] {"aaa@aaa.pl", "123456", "Air", "1"},
                new String[] {"aaa@aaa.pl", "123456", "Apple", "4"}};
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();
        driver.manage().window().maximize();

        homeSearchPage = PageFactory.initElements(driver, HomeSearchPage.class);

        driver.get(PAGE_URL);
    }

    @Test
    @UseDataProvider("pricelessSearchSingleWordTest")
    public void pricelessSearchSingleWordTest(String login, String password, String search, String
            results) {
        homeSearchPage.zalogujSie(login, password, search);

        String sizeOfResultsList = Integer.toString(homeSearchPage.getResultsList().size());

        AssertionsForClassTypes.assertThat(sizeOfResultsList).isEqualTo(results).as("Wrong " +
                "number of results");
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
