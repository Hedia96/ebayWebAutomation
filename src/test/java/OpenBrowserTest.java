import Pages.HomePage;
import Pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.digests.ParallelHash;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

/***
 *
 */
@Epic("Testing eBay Website")
public class OpenBrowserTest extends BaseTest {

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.ebay.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public void logDetails(Object object)
    {
        ITestResult result = Reporter.getCurrentTestResult();
        String methodname= result.getMethod().getMethodName();
        Allure.step(methodname+ "Details",step->{
            Allure.addAttachment("Value of result is ",object.toString());
        });
    }
    @Feature("Validate Main Page")
    @Step("Validate main page step by checking element is displayed or not")
    @Test
    public void TC1_validateMainPage() {
        HomePage homePage = new HomePage(driver);
        Allure.step("Assert main page is loading normally ");
        logDetails(homePage.validateHomeTab());
        Assert.assertTrue(homePage.validateHomeTab());
    }

    @Parameters({"SearchCriteria"})
    @Test
    @Feature("Validate Search with search key")
    public void TC2_validateSearch(String searchkey) {
        HomePage homePage = new HomePage(driver);
        Allure.step("Search by keyword" + searchkey + "in search bar ");

        SearchPage searchPage = homePage.enterSearchItem(searchkey);
        Allure.step("Checking the headers of search result ");
        List<String> titles = searchPage.validateSearchResult(searchkey);
        SoftAssert softAssert = new SoftAssert();
        for (String res : titles)
            //  softAssert.assertTrue(res.toLowerCase().contains(searchkey.toLowerCase()), "result doesn't contain the search key");
            Assert.assertTrue(res.toLowerCase().contains(searchkey.toLowerCase()));
        Allure.step("Hard Assertion for each header title ");

    }

    @Test
    @Feature("Validate Result Size of Search")
    public void TC3_validateResultSize() {
        SearchPage searchPage = new SearchPage(driver);
        Allure.step("Assert the search result ");
        Assert.assertTrue(
                searchPage.getResultSize() > 0
        );


    }

    @Test
    @Parameters({"trnOption"})
    @Feature("Validate Choosing Filter Manual from LeftSide Menu")
    public void TC4_validateFilterbyManual(String option) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.clickFilter(option);


    }

    @AfterClass
    public void tearDown() {

        driver.quit();
    }
    /* command used to generate allure report :
            allure generate allure-results --clean -o allure-report
            allure serve
     */
}

