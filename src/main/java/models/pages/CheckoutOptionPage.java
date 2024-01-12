package models.pages;

import models.components.cart.CheckoutOptionComponent;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionPage extends BasePage{
    public CheckoutOptionPage(WebDriver driver) {
        super(driver);
    }
    public CheckoutOptionComponent checkoutOptionComp(){
        return findComponent(CheckoutOptionComponent.class);
    }
}
