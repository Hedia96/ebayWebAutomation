import Pages.HomePage;
import Pages.SearchPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.crypto.digests.ParallelHash;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
@Listeners(TestListener.class)
/***
 *
 */
public class OpenBrowserTest extends BaseTest{

    @BeforeClass
    public void setup()
    {

        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.ebay.com/");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void TC1_validateMainPage()
    {
        HomePage homePage=new HomePage(driver);
        Assert.assertTrue( homePage.validateHomeTab());

    }

    @Parameters({"SearchCriteria"})
    @Test
    public void TC2_validateSearch(String searchkey)
    {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage= homePage.enterSearchItem(searchkey);
        searchPage.validateSearchResult(searchkey);
    //    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

    }
    @Test
    public void TC3_validateResultSize()
    {
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(
        searchPage.getResultSize() >0
        );
      //  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);


    }
    @Test
    @Parameters({"trnOption"})
    public void TC4_validateFilterbyManual(String option)
    {
        SearchPage searchPage= new SearchPage(driver);
        searchPage.clickFilter(option);

    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
    /* command used to generate allure report :
            allure generate allure-results --clean -o allure-report
     */
}

