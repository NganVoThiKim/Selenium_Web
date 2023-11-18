package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitingForElementEnabled;

import java.time.Duration;

public class JSAlerts {
    private static String TARGET_URL = "https://the-internet.herokuapp.com/javascript_alerts";
    private static By JSALERT_SEL = By.cssSelector("[onclick='jsAlert()']");
    private static By JSALERT_CONFIRM_SEL = By.cssSelector("[onclick='jsConfirm()']");
    private static By JSALERT_PROMPT_SEL = By.cssSelector("[onclick='jsPrompt()']");
    private static By RESULT_SEL = By.cssSelector("#result");

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(TARGET_URL);
        try {
            WebElement jsAlertResult = driver.findElement(RESULT_SEL);

            // JS Alert interaction
//            driver.findElement(JSALERT_SEL).click();
//            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//            alert.accept();
            handleAlerts(driver, JSALERT_SEL, true);
            System.out.println("jsAlert :" + jsAlertResult.getText());

            // JS Alert Confirm interaction
//            driver.findElement(JSALERT_CONFIRM_SEL).click();
//            Alert alertConfirm = wait.until(ExpectedConditions.alertIsPresent());
//            alertConfirm.dismiss();
            handleAlerts(driver, JSALERT_CONFIRM_SEL, false);
            System.out.println("jsAlert Confirm: " + jsAlertResult.getText());

            // JS Alert Prompt interaction
//            driver.findElement(JSALERT_PROMPT_SEL).click();
//            Alert alertPrompt = wait.until(ExpectedConditions.alertIsPresent());
//            alertPrompt.sendKeys("Hellu, I'm teo");
//            alertPrompt.accept();
            handleAlerts(driver, JSALERT_PROMPT_SEL, true, "Hellu, I'm teo");
            System.out.println("jsAlert Prompt: " + jsAlertResult.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void handleAlerts(WebDriver driver, By triggerBtn, boolean isAccepting, String... messages) {
        driver.findElement(triggerBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert jsAlert = wait.until(ExpectedConditions.alertIsPresent());
        if (messages.length > 0) {
            jsAlert.sendKeys(messages[0]);
            makeJSAlertDecision(jsAlert,isAccepting);

        } else {
            makeJSAlertDecision(jsAlert,isAccepting);
        }
    }

    private static void makeJSAlertDecision(Alert jsAlert, boolean isAccepting) {
        if(isAccepting){
            jsAlert.accept();
        }else {
            jsAlert.dismiss();
        }
    }
}

