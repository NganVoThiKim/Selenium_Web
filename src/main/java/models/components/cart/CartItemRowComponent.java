package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".cart-item-row")
public class CartItemRowComponent extends Component {
    private final static By productUnitPriceSel = By.cssSelector(".product-unit-price");
    private final static By productQuantitySel = By.cssSelector("input[class='qty-input']");
    private final static By productSubTotalSel = By.cssSelector(".product-subtotal");
    public CartItemRowComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public double productUnitPrice(){
        return Double.parseDouble(findElement(productUnitPriceSel).getText().trim());
    }
    public double productQuantity(){
        return Double.parseDouble(findElement(productQuantitySel).getAttribute("value").trim());
    }
    public double productSubTotal(){
        return Double.parseDouble(findElement(productSubTotalSel).getText().trim());
    }
}
