package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageModel02 {
    private final WebDriver driver;
    private static final By USERNAME_SEL = By.id("Email");
    private static final By PASSWORD_SEL = By.id("Password");
    private static final By REMEMBER_CHECKBOX_SEL = By.id("RememberMe");
    private static final By LOGIN_BTN_SEL = By.cssSelector(".button-1.login-button");

    public LoginPageModel02(WebDriver driver) {
        this.driver = driver;
    }
    public void inputUsername(String usernameStr){
        this.driver.findElement(USERNAME_SEL).sendKeys(usernameStr);
    }
    public void inputPassword(String passwordStr){
        this.driver.findElement(PASSWORD_SEL).sendKeys(passwordStr);
    }
    public void checkOnRememberCheckbox(){
        this.driver.findElement(REMEMBER_CHECKBOX_SEL).click();
    }
    public void clickOnLoginBtn(){
        this.driver.findElement(LOGIN_BTN_SEL).click();
    }
}
