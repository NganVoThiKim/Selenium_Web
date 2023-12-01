package tests;

import driver.DriverFactory;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class ProductComponentTests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get("https://demowebshop.tricentis.com/");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.productItemComponentList().forEach(productItemComponent -> {
                System.out.println("Product title = " + productItemComponent.productTitleEle().getText());
            });
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
