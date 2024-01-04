package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BaseItemComponent extends Component {
    private static final By priceProductSel = By.cssSelector(".product-price");
    private static final By quantityProductSel = By.cssSelector(".add-to-cart input[class^='qty-input']");
    private static final By addToCartBtnSel = By.cssSelector("input[id^='add-to-cart-button']");
    private static final By barNotificationSel = By.cssSelector("#bar-notification");
    private static final By shoppingCartLinkOnBarNotifySel = By.cssSelector("#bar-notification a");
    public BaseItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public double priceProduct(){
        String priceProductStr = findElement(priceProductSel).getText();
        return Double.parseDouble(priceProductStr);
    }
    public void setProductQuantity(int quantity){
        findElement(quantityProductSel).clear();
        findElement(quantityProductSel).sendKeys(String.valueOf(quantity));
    }
    public void clickingOnAddToCardBtn(){
        findElement(addToCartBtnSel).click();
    }
    public void waitUntilItemAddedToCart(){
        String successMessage = "The product has been added to your";
        wait.until(ExpectedConditions.textToBe(barNotificationSel,successMessage));
    }
    public void clickingShoppingCartLinkOnBarNotify(){
        findElement(shoppingCartLinkOnBarNotifySel).click();
    }
}
