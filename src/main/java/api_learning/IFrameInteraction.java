package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.SelectEx;

public class IFrameInteraction {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/iframe";
    private static By IFRAME_SEL = By.cssSelector("iframe[id^='mce']");
    private static By IFRAME_INPUT_SEL = By.cssSelector("#tinymce");
    //private static By IFRAME_SEL = By.cssSelector("iframe[id*='mce']");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            WebElement iFrameEle = driver.findElement(IFRAME_SEL);
            // Switch to the target iframe
            driver.switchTo().frame(iFrameEle);
            // Interact with iframe's elements
            WebElement inputFieldEle = driver.findElement(IFRAME_INPUT_SEL);
            inputFieldEle.click();
            inputFieldEle.clear();
            inputFieldEle.sendKeys("Hello, I'm Nena");
            Thread.sleep(2000);
            // Switch back to the parent iframe
            driver.switchTo().defaultContent();
            // Continue interact with other eles without iframe

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
