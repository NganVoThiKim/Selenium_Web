package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;

public class DropdownInteraction {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/dropdown";

    private static By DROPDOWN_SEL = By.cssSelector("#dropdown");
    //private static By DROPDOWN_SEL = By.cssSelector("select[id='dropdown']");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try {
            // Locate and select dropdown element
            WebElement dropdownEle = driver.findElement(DROPDOWN_SEL);
            //Select select = new Select(dropdownEle);
            SelectEx select = new SelectEx(dropdownEle);

            // by visible text
            //select.selectByVisibleText("Option 1");

            select.selectOption1();
            debugWait();
            // by index
            select.selectByIndex(2);
            debugWait();
            // by value
            select.selectByValue("1");
            debugWait();

            // DeSelect
            /* select.deselectByValue("1");
            debugWait();
            => Show "You may only deselect options of a multi-select" error
             */


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void debugWait() {
        try {
            Thread.sleep(2000);
        } catch (Exception ignored) {
        }
    }

}
