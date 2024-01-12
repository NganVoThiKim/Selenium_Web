package models.components.global.header;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import support.ui.Scroll;


@ComponentCSSSelector(".header")
public class HeaderComponent extends Component {
    private static final By shoppingCartLinkSel = By.id("topcartlink");
    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public void clickingOnShoppingCartLink(){
        Scroll.toTop(driver);
        findElement(shoppingCartLinkSel).click();
    }
}
