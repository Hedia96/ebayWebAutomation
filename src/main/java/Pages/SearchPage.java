package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends MainPage {
    private By searchcountxpath = By.xpath("(//div[@class=\"srp-controls__control srp-controls__count\"]//span)[1]");
    private By filtersTrnxpath =
            By.xpath ("//li[@class='x-refine__main__list--value' and @name='Transmission']//descendant::input");
   // private By transfilter = By.xpath("(//a[@class='srp-carousel-list__item-link']/descendant::span[text()=' - apply Transmission filter']/..)");
    private By titleResultsxpath = By.xpath("//div[@role=\"heading\"]//span");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /***
     * This Test validate the result related to search criteria by checking the title of a result item
     * @param searchkey
     * @return
     */
    public List<String> validateSearchResult(String searchkey) {
        List<WebElement> titles = driver.findElements(titleResultsxpath);
        List<String> textresults= new ArrayList<>();
        if (titles.size() > 0) {
            // check if the title contains searchcriteria

          log.info("The Search result  is {}", titles.size());
          for(WebElement res :titles)
          {
              textresults.add(res.getText());

          }

        }
return  textresults;
    }

    public int getResultSize() {
        return Integer.valueOf(driver.findElement(searchcountxpath).getText());
    }

    public void clickFilter(String option)
    {
        List<WebElement> l= driver.findElements(filtersTrnxpath);
        int oldvalue,newvalue;
        for (WebElement e : l)
        {
            try {
            System.out.println("in loop "+e.getAttribute("aria-label"));
            oldvalue=getResultSize();
            log.info("The result value before clicking is  {}", oldvalue);
            if(e.getAttribute("aria-label").equalsIgnoreCase(option)) {

                e.click();
                newvalue=getResultSize();
                Assert.assertTrue(oldvalue>newvalue,"the result of manual is not  less than the total");
                log.info("The new value after clicking on option is {}",newvalue );
            }
               }catch (StaleElementReferenceException exception)
               {
                   log.error("there is exception is  {}", exception);
                   driver.navigate().refresh();

               }
            }

        }


}
