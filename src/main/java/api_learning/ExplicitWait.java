package api_learning;

import dev.failsafe.internal.util.Assert;
import dev.failsafe.internal.util.Durations;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitingForElementEnabled;

import java.time.Duration;

public class ExplicitWait {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/login";
    //private static By USERNAME_SEL = By.cssSelector("#username");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try{
            /*Implicit wait: Applied globally for the whole session when finding the elements | Interval time 500 miliseconds
              Explicit wait: Applied locally/ for a specific element | Interval time 500 miliseconds */
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

            //--Throw "TimeoutException" if condition isn't matched
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tao lao")));

            //--Throw "NoSuchElementException" if condition isn't matched
            //webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("tao lao"))));

            //--Trigger and verify another element disable
//            try{
//                webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("tao lao")));
//            }catch (TimeoutException timeoutException){
//                throw new TimeoutException("Element is visible");
//            }

            //--Using customized explicit wait class
            webDriverWait.until(new WaitingForElementEnabled(By.cssSelector("aaa")));

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
