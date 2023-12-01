package tests;
import driver.DriverFactory;
import models.pages.LoginPageModel03;
import org.openqa.selenium.WebDriver;

public class POModel03Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/login");
            LoginPageModel03 loginPage = new LoginPageModel03(driver);
            loginPage.inputUsername("nganvothikim270496@gmail.com")
                    .inputPassword("1234567890aA@")
                    .checkOnRememberCheckbox()
                    .clickOnLoginBtn();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
