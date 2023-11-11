package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver getWebDriver(){
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait();
        return driver;
    }
}
