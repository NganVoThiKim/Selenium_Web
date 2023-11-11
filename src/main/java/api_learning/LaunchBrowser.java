package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {
    public static void main(String[] args) {
        /*
        * 1. Check the current browser version
        * 2. Find the compatible browser version from the central and then download
        * 3. Use it as browser driver
        **/

        WebDriver driver = DriverFactory.getWebDriver();
        // Open web page
        driver.get("https://sdetpro.com");
        // Close the window
        driver.close();
        //Quit the session
        driver.quit();
    }

}
