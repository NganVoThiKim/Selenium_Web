package tests;

import driver.DriverFactory;
import models.pages.LoginPageModel01;
import models.pages.LoginPageModel02;
import org.openqa.selenium.WebDriver;
public class POModel02Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/login");
            LoginPageModel02 loginPage = new LoginPageModel02(driver);
            loginPage.inputUsername("nganvothikim270496@gmail.com");
            loginPage.inputPassword("1234567890aA@");
            loginPage.checkOnRememberCheckbox();
            loginPage.clickOnLoginBtn();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
