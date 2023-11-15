package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinkTextInteraction {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static By LINK_TEXT_SEL = By.linkText("Elemental Selenium");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            driver.findElement(LINK_TEXT_SEL).click();
            try {
                Thread.sleep(2000);
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
