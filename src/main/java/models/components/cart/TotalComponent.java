package models.components.cart;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCSSSelector(".totals .cart-total")
public class TotalComponent extends Component {
    private final static By priceTableSel = By.cssSelector("table tr");
    private final static By priceTypeSel = By.cssSelector(".cart-total .cart-total-left");
    private final static By priceValueSel = By.cssSelector(".cart-total .cart-total-right");
    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    /*
    * <Sub-Total:, 1365.00>
    * <Shipping:, 0.00>
    * <Tax:, 0.00>
    * <Total:, 1365.00>
    * */
    public Map<String, Double> priceCategories(){
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableEles = findElements(priceTableSel);
        Assert.assertFalse(priceTableEles.isEmpty(), "[ERR] Price Table is empty");
        for (WebElement priceTableRowEle : priceTableEles) {
            WebElement priceTypeEle = priceTableRowEle.findElement(priceTypeSel);
            WebElement priceValueEle = priceTableRowEle.findElement(priceValueSel);
            // Key, Value
            String priceType = priceTypeEle.getText().replace(":","").trim();
            double priceValue = Double.parseDouble(priceValueEle.getText().trim());
            priceCategories.put(priceType, priceValue);
        }
        return priceCategories;
    }
}
