package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends MainPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By hometab = By.xpath("//*[@id=\"mainContent\"]/div[1]/ul/li[1]/span");
    private By searchFld = By.xpath("//label[@class='gh-ar-hdn']/following::input[@type='text']");
    private By searchBtn=By.xpath("//input[@value='Search']");

    public boolean validateHomeTab() {
        return driver.findElement(hometab).isDisplayed();
    }

    public SearchPage enterSearchItem(String searchcriteria) {
        boolean resultstatus = true;
        try {
            driver.findElement(searchFld).sendKeys(searchcriteria);
            driver.findElement(searchBtn).click();
        } catch (Exception e) {
            resultstatus = false;
        }
        if (resultstatus) {
            return new SearchPage(driver);
        }
        return null;
    }

}
