package tests.global.footer;

import org.testng.annotations.Test;
import test_flows.global.FooterTestFlow;
import tests.BaseTest;



public class FooterTest extends BaseTest {
    @Test
    public void testHomePageFooter(){
        /*WebDriver driver = DriverFactory.getWebDriver();
        try {
            driver.get("https://demowebshop.tricentis.com/");
            FooterTestFlow footerTestFlow= new FooterTestFlow(driver);
            //Assert.fail("I want to");
            footerTestFlow.verifyFooterComponent();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }*/

        driver.get("https://demowebshop.tricentis.com/");
        FooterTestFlow footerTestFlow= new FooterTestFlow(driver);
        //Assert.fail("I want to");
        footerTestFlow.verifyFooterComponent();
    }
    @Test
    public void testMenuCategoryFooter(){
        driver.get("https://demowebshop.tricentis.com/");
        FooterTestFlow menuCategoryFooterTestFlow= new FooterTestFlow(driver);
        menuCategoryFooterTestFlow.verifyRandomMenuFooterComponent();
    }
}
