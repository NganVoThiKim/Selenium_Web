package tests;
import driver.DriverFactory;
import models.pages.BasePage;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class POModel04Tests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/login");
            HomePage homePage = new HomePage(driver);
            //homePage.footerComp().doSth();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            driver.quit();
        }
    }

}
