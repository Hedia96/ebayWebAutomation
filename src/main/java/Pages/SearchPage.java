package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SearchPage extends MainPage {
    private By searchcountxpath = By.xpath("(//h1[@class='srp-controls__count-heading']/span)[1]");
    private By filtersTrnxpath =
            By.xpath ("//li[@class='x-refine__main__list--value' and @name='Transmission']//descendant::input");
   // private By transfilter = By.xpath("(//a[@class='srp-carousel-list__item-link']/descendant::span[text()=' - apply Transmission filter']/..)");
    private By titleResultsxpath = By.xpath("//div[@class='s-item__title']//span[@class=\"BOLD\"]");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /***
     * This Test validate the result related to search criteria by checking the title of a result item
     * @param searchkey
     * @return
     */
    public void validateSearchResult(String searchkey) {
        List<WebElement> titles = driver.findElements(titleResultsxpath);
        if (titles.size() > 0) {
            // check if the title contains searchcriteria

            System.out.println(titles.get(0).getText());

            Assert.assertTrue(titles.get(0).getText().toLowerCase().contains(searchkey.toLowerCase()), "Result appears related to search criteria");


        }

    }

    public int getResultSize() {
        return Integer.valueOf(driver.findElement(searchcountxpath).getText());
    }

    public void clickFilter(String option)
    {
        List<WebElement> l= driver.findElements(filtersTrnxpath);
        for (WebElement e : l)
        {
            try {
            System.out.println("in loop "+e.getAttribute("aria-label"));

            if(e.getAttribute("aria-label").equalsIgnoreCase(option)) {

                e.click();
            }
               }catch (StaleElementReferenceException exception)
               {
                   driver.navigate().refresh();

               }
               // System.out.println("the result after selecting is " + getResultSize());
            }

        }


}
////input[@class='checkbox__control']
//and contains(@text,'Transmission')]