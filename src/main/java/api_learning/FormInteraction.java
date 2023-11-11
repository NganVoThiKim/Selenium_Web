package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static By USERNAME_SEL = By.cssSelector("#username");
    private static By PASSWORD_SEL = By.cssSelector("#password");
    private static By LOGIN_BTN = By.cssSelector("#login [type='submit']");
    private static String USERNAME_STR = "tomsmith";
    private static String PASSWORD_STR = "SuperSecretPassword!";

    public static void main(String[] args) {
        /*
         * 1. Init web driver instance
         * 2. Launch with page
         * 3. Find elements
         * 4. Interact with elements
         * 5. Close the session | what if we have exception
         **/

        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try{
            WebElement usernameEle = driver.findElement(USERNAME_SEL);
            WebElement passwordEle = driver.findElement(PASSWORD_SEL);
            WebElement loginBtnEle = driver.findElement(LOGIN_BTN);

            usernameEle.sendKeys(USERNAME_STR);
            passwordEle.sendKeys(PASSWORD_STR);
            loginBtnEle.click();

            // Navigate back page
            driver.navigate().back();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
