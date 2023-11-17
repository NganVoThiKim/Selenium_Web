package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;

public class MouseHoverAndNarrowDownSearchingScope {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/hovers";
    private static By FIGURE_SEL = By.cssSelector(".figure");
    private static By PROFILE_NAME_SEL = By.cssSelector(".figcaption h5");
    private static By PROFILE_LINK_SEL = By.cssSelector(".figcaption a");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            /** Searching globally - means from html tag
             * driver.findElement/s (locator)
             *
             * Narrow down searching scope from with the parent scope is now the element
             * WebElement.findElement/s (locator)
             * */

            // Target parent element
            List<WebElement> figureEles = driver.findElements(FIGURE_SEL);
            if(figureEles.isEmpty()){
                throw new NoSuchElementException("No figure on the page");
            }

            Actions actions = new Actions(driver);
            for (WebElement figureEle : figureEles) {
                WebElement profileNameEle = figureEle.findElement(PROFILE_NAME_SEL);
                WebElement profileLinkEle = figureEle.findElement(PROFILE_LINK_SEL);
                // Finding child eles
                boolean isNameDisplayed = profileNameEle.isDisplayed();
                boolean isLinkDisplayed = profileLinkEle.isDisplayed();
                System.out.println("BEFORE | isNameDisplayed: " + isNameDisplayed);
                System.out.println("BEFORE | isLinkDisplayed: " + isLinkDisplayed);

                // Mouse hover action
                actions.moveToElement(figureEle).perform();
                isNameDisplayed = profileNameEle.isDisplayed();
                isLinkDisplayed = profileLinkEle.isDisplayed();
                System.out.println("AFTER | isNameDisplayed: " + isNameDisplayed);
                System.out.println("AFTER | isLinkDisplayed: " + isLinkDisplayed);
                System.out.println("Profile Name: " + profileNameEle.getText());
                System.out.println("Profile Link: " + profileLinkEle.getAttribute("href"));
                System.out.println("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
