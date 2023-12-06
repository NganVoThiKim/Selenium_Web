package test_flows.global;

import models.components.global.footer.*;
import models.components.global.header.TopMenuComponent;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestRunnerFactory;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {
    private final WebDriver driver;
    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }
    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(this.driver);
        FooterColumnComponent informationColumnComp = basePage.footerComp().findComponent(InformationColumnComponent.class);
        FooterColumnComponent customerServiceColumnComp = basePage.footerComp().findComponent(CustomerServiceColumnComponent.class);
        FooterColumnComponent myAccountColumnComp = basePage.footerComp().findComponent(MyAccountColumnComponent.class);
        FooterColumnComponent followUsColumnComp = basePage.footerComp().findComponent(FollowUsColumnComponent.class);

        verifyInformationColumn(informationColumnComp);
        verifyCustomerServiceColumn(customerServiceColumnComp);
        verifyMyAccountColumn(myAccountColumnComp);
        verifyFollowUsColumn(followUsColumnComp);
    }
    public void verifyRandomMenuFooterComponent(){
        // Randomly pickup main menu from TopMenuComponent
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<TopMenuComponent.MainMenuComp> mainMenuCompList = topMenuComp.mainMenusComp();
        Assert.assertFalse(mainMenuCompList.isEmpty(),"[ERR] There is no menu on top menu");
        TopMenuComponent.MainMenuComp randomMainMenu = mainMenuCompList.get(new SecureRandom().nextInt(mainMenuCompList.size()));
        String randomMenuHref = randomMainMenu.mainMenuLinkEle().getAttribute("href");
        randomMainMenu.mainMenuLinkEle().click();

        // Get sub-menu list (if any) then click on a random sub-menu / main menu (If has no sub-menu)
//        List<TopMenuComponent.SubMenuComp> subMenuCompList = randomMainMenu.subMenusComp();
//        if(subMenuCompList.isEmpty()){
//            randomMainMenu.mainMenuLinkEle().click();
//        }else {
//            int randomIndex = new SecureRandom().nextInt(subMenuCompList.size());
//            TopMenuComponent.SubMenuComp randomSubMenu = subMenuCompList.get(randomIndex);
//            randomMenuHref = randomSubMenu.getComponent().getAttribute("href");
//            randomSubMenu.getComponent().click();
//        }

        // Make sure wait -> page is navigated correctly
        try {
            WebDriverWait wait = randomMainMenu.waitComponent();
            wait.until(ExpectedConditions.urlContains(randomMenuHref));
        } catch (Exception e){
            Assert.fail("[ERR] Target page is not matched");
        }

        // Call common verify method
        verifyFooterComponent();
    }
    private void verifyInformationColumn(FooterColumnComponent informationColumnComp) {
        List<String> expectedLinkTexts =
                Arrays.asList("Sitemap", "Shipping & Returns", "Privacy Notice", "Conditions of Use", "About us", "Contact us");
        List<String> expectedHrefs =
                Arrays.asList("/sitemap", "/shipping-returns", "/privacy-policy", "/conditions-of-use", "/about-us", "/contactus");
        testFooterColumn(informationColumnComp, expectedLinkTexts, expectedHrefs,false);
    }
    private void verifyCustomerServiceColumn(FooterColumnComponent customerServiceColumnComp) {
        List<String> expectedLinkTexts =
                Arrays.asList("Search", "News", "Blog", "Recently viewed products", "Compare products list", "New products");
        List<String> expectedHrefs =
                Arrays.asList("/search", "/news", "/blog", "/recentlyviewedproducts", "/compareproducts", "/newproducts");
        testFooterColumn(customerServiceColumnComp, expectedLinkTexts, expectedHrefs,false);
    }
    private void verifyMyAccountColumn(FooterColumnComponent myAccountColumnComp) {
        List<String> expectedLinkTexts =
                Arrays.asList("My account", "Orders", "Addresses", "Shopping cart", "Wishlist");
        List<String> expectedHrefs =
                Arrays.asList("/customer/info", "/customer/orders", "/customer/addresses", "/cart", "/wishlist");
        testFooterColumn(myAccountColumnComp, expectedLinkTexts, expectedHrefs, false);
    }
    private void verifyFollowUsColumn(FooterColumnComponent followUsColumnComp) {
        List<String> expectedLinkTexts =
                Arrays.asList("Facebook", "Twitter", "RSS", "YouTube", "Google+");
        List<String> expectedHrefs =
                Arrays.asList("http://www.facebook.com/nopCommerce", "https://twitter.com/nopCommerce", "https://demowebshop.tricentis.com/news/rss/1", "http://www.youtube.com/user/nopCommerce", "https://plus.google.com/+nopcommerce");
        testFooterColumn(followUsColumnComp, expectedLinkTexts, expectedHrefs,true);
    }
    private void testFooterColumn(FooterColumnComponent footerColumnComp, List<String> expectedLinkTexts, List<String> expectedHrefs, boolean isFollowUs){
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        if(!isFollowUs){
            expectedHrefs.replaceAll(originalHref -> "https://demowebshop.tricentis.com" + originalHref);
        }
        footerColumnComp.linksEle().forEach(columnItem ->{
            actualLinkTexts.add(columnItem.getText());
            actualHrefs.add(columnItem.getAttribute("href"));
        });
        if(actualLinkTexts.isEmpty() || actualHrefs.isEmpty()){
            Assert.fail("Footer column texts OR hyperlinks is empty");
        }
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Footer column link texts are different");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Footer column hyperlink are different");

        /*System.out.println(footerColumnComp.headerEle().getText());
        for (WebElement linkEle : footerColumnComp.linksEle()) {
            System.out.println(linkEle.getText() + ": " + linkEle.getAttribute("href"));
        }
        System.out.println("======");*/
    }
}
