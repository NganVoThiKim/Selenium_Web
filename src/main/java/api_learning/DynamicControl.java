package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitingForElementEnabled;

import java.time.Duration;

public class DynamicControl {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/dynamic_controls";
    private static final By CHECKBOX_FORM_SEL = By.id("checkbox-example");
    private static final By INPUT_FORM_SEL = By.id("input-example");
    private static final By CHECKBOX_INPUT_ELE_SEL = By.tagName("input");
    private static final By BTN_ELE_SEL = By.tagName("button");

    // private static final By INPUT_TEST_ELE_SEL = By.cssSelector("#input-example input");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get(TARGET_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            //---Locate the parent element of checkbox form
            WebElement checkboxFormEle = driver.findElement(CHECKBOX_FORM_SEL);
            WebElement inputFormEle = driver.findElement(INPUT_FORM_SEL);
            
            //---Checkbox form interaction
            WebElement checkboxEle = checkboxFormEle.findElement(CHECKBOX_INPUT_ELE_SEL);
            WebElement removeBtnEle = checkboxFormEle.findElement(BTN_ELE_SEL);
            System.out.println("BEFORE | is checkbox checked: " + checkboxEle.isSelected());
            checkboxEle.click();
            System.out.println("AFTER | is checkbox checked: " + checkboxEle.isSelected());
            removeBtnEle.click();
            Thread.sleep(2000);
            //wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKBOX_ELE_SEL);
            wait.until(ExpectedConditions.invisibilityOf(checkboxEle));

//            removeBtnEle.click();
//            wait.until(ExpectedConditions.visibilityOf(checkboxEle));
//            checkboxEle.click();
//            System.out.println("CHECKED | is checkbox checked: " + checkboxEle.isSelected());
            System.out.println("\n");

            //---Input form interaction
            WebElement inputEle = inputFormEle.findElement(CHECKBOX_INPUT_ELE_SEL);
            WebElement enableBtnEle = inputFormEle.findElement(BTN_ELE_SEL);
            System.out.println("BEFORE | is input enabled: " + inputEle.isEnabled());
            enableBtnEle.click();
            Thread.sleep(2000);
            wait.until(new WaitingForElementEnabled(CHECKBOX_INPUT_ELE_SEL));
            System.out.println("AFTER | is input enabled: " + inputEle.isEnabled());
            inputEle.sendKeys("Something...");
            Thread.sleep(2000);

            //---Show error "ElementNotInteractableException"
//            wait.until(ExpectedConditions.visibilityOf(inputEle));
//            // wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_TEST_ELE_SEL));
//            System.out.println("AFTER | is input enabled: " + inputEle.isEnabled());
//            inputEle.sendKeys("I'm teo");
//            Thread.sleep(2000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
