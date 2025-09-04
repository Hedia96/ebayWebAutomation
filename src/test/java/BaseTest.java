
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    public WebDriver driver;
    public WebDriver getDriver(){
        return driver;
    }

}
