package tests;

import driver.DriverFactory;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FooterComponentTests {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getWebDriver();
        driver.get("https://demowebshop.tricentis.com/");
        try {
            HomePage homePage = new HomePage(driver);
            FooterComponent footerComp = homePage.footerComp();
            FooterColumnComponent informationColumnComp = footerComp.informationColumnComp();
            FooterColumnComponent customerServiceColumnComp = footerComp.customerServiceColumnComp();
            FooterColumnComponent myAccountColumnComp = footerComp.myAccountColumnComp();
            FooterColumnComponent followUsColumnComp = footerComp.followUsColumnComp();
            testFooterComp(informationColumnComp);
            testFooterComp(customerServiceColumnComp);
            testFooterComp(myAccountColumnComp);
            testFooterComp(followUsColumnComp);
        } catch (Exception e) {
            String err = e.getMessage();

        } finally {
            driver.quit();
        }
    }

    private static void testFooterComp(FooterColumnComponent footerColumnComp) {
        System.out.println(footerColumnComp.headerEle().getText());
        for (WebElement linkEle : footerColumnComp.linksEle()) {
            System.out.println(linkEle.getText() + ": " + linkEle.getAttribute("href"));
        }
        System.out.println("======");
    }
}
