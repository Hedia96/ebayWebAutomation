package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends MainPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By hometab = By.xpath("//*[@id=\"mainContent\"]");
    private By searchFld = By.xpath("//input[@title='Search']");
    private By searchBtn=By.xpath("//button[@value='Search']");

    public boolean validateHomeTab() {
        return driver.findElement(hometab).isDisplayed();
    }

    public SearchPage enterSearchItem(String searchcriteria) {
        log.info("In enter Search Item .....");
        driver.findElement(searchFld).sendKeys(searchcriteria);
        log.info("After search the text  .....");
        driver.findElement(searchBtn).click();
         return new SearchPage(driver);
      
    }

}
