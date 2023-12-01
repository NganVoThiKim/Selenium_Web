package test_flows.global;

import models.components.global.footer.*;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    private void verifyInformationColumn(FooterColumnComponent informationColumnComp) {
        testFooterColumn(informationColumnComp);
    }

    private void verifyCustomerServiceColumn(FooterColumnComponent customerServiceColumnComp) {
        testFooterColumn(customerServiceColumnComp);
    }

    private void verifyMyAccountColumn(FooterColumnComponent myAccountColumnComp) {
        testFooterColumn(myAccountColumnComp);
    }

    private void verifyFollowUsColumn(FooterColumnComponent followUsColumnComp) {
        testFooterColumn(followUsColumnComp);
    }
    private void testFooterColumn(FooterColumnComponent footerColumnComp){
        System.out.println(footerColumnComp.headerEle().getText());
        for (WebElement linkEle : footerColumnComp.linksEle()) {
            System.out.println(linkEle.getText() + ": " + linkEle.getAttribute("href"));
        }
        System.out.println("======");
    }
}
