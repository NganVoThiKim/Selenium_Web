package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteractionMultipleMatching {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/login";
    private static By LOGIN_INPUT_FIELD_SEL = By.tagName("input");
    private static By LOGIN_BTN = By.cssSelector("#login_ [type='submit']");;

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        try{
            List<WebElement> loginFieldsEles = driver.findElements(LOGIN_INPUT_FIELD_SEL);
            if(!loginFieldsEles.isEmpty()){
                final int USERNAME_INDEX = 0;
                final int PASSWORD_INDEX = 1;
                loginFieldsEles.get(USERNAME_INDEX).sendKeys("a@gmail.com");
                loginFieldsEles.get(PASSWORD_INDEX).sendKeys("12345678");
            } else {
                throw new NoSuchFieldException("Login fields aren't found");
            }
            driver.findElement(LOGIN_BTN).click();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
