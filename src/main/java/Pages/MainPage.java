package Pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    public Logger log = LogManager.getLogger();

    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntillElementtobeVisible(WebDriver driver, WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitJQuery() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(webDriver -> (Boolean) ((JavascriptExecutor) webDriver)
                .executeScript("return jQuery.active == 0"));
    }

    public void waitUntillElementtobePresenceByID(WebDriver driver, String id) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
    }

    public static void performJsClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public WebElement selectVisibleElement(WebDriver driver, List<WebElement> elements) {
        WebElement visibleElement = null;
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                visibleElement = element;
                break;
            }
        }
        return visibleElement;
    }

    public static void selectDropdownByText(WebDriver driver, WebElement select, String text) {
        ((JavascriptExecutor) driver).executeScript(
                "var select = arguments[0]; for(var i = 0; i < select.options.length; i++) {if(select.options[i].text == arguments[1]) {select.options[i].selected = true; }}", select, text);
    }


}
