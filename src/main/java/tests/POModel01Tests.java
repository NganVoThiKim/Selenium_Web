package tests;

import driver.DriverFactory;
import models.pages.LoginPageModel01;
import org.openqa.selenium.WebDriver;

public class POModel01Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/login");
            LoginPageModel01 loginPage = new LoginPageModel01(driver);
            loginPage.username().sendKeys("nganvothikim270496@gmail.com");
            loginPage.password().sendKeys("1234567890aA@");
            loginPage.rememberCheckbox().click();
            loginPage.loginBtn().click();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
