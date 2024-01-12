package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@ComponentCSSSelector(".page-body .customer-blocks")
public class CheckoutOptionComponent extends Component {
    public final static By checkoutAsGuestSel = By.cssSelector("input[class*='checkout-as-guest-button']");

    public CheckoutOptionComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickCheckoutAsGuestBtn(){
        findElement(checkoutAsGuestSel).click();
    }
}
