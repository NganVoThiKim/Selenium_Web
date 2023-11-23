package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageModel01 {
    private final WebDriver driver;
    private static final By USERNAME_SEL = By.id("Email");
    private static final By PASSWORD_SEL = By.id("Password");
    private static final By REMEMBER_CHECKBOX_SEL = By.id("RememberMe");
    private static final By LOGIN_BTN_SEL = By.cssSelector(".button-1.login-button");

    public LoginPageModel01(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement username(){
        return this.driver.findElement(USERNAME_SEL);
    }
    public WebElement password(){
        return this.driver.findElement(PASSWORD_SEL);
    }
    public WebElement rememberCheckbox(){
        return this.driver.findElement(REMEMBER_CHECKBOX_SEL);
    }
    public WebElement loginBtn(){
        return this.driver.findElement(LOGIN_BTN_SEL);
    }
}
