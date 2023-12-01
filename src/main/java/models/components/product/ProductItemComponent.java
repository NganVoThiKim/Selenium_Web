package models.components.product;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(value = ".item-box")
public class ProductItemComponent extends Component {
    private static By PRODUCT_TITLE_SEL = By.cssSelector(".product-title");
    public ProductItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public WebElement productTitleEle(){
        return this.component.findElement(PRODUCT_TITLE_SEL);
    }
}
