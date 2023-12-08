package models.pages;

import models.components.Component;
import models.components.global.footer.FooterComponent;
import models.components.global.header.MenuItemComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BasePage extends Component {
    public final WebDriver driver;

    public BasePage(WebDriver driver) {
        super(driver, driver.findElement(By.tagName("html")));
        this.driver = driver;
    }

    public FooterComponent footerComp(){
        return findComponent(FooterComponent.class);
    }
//    public TopMenuComponent topMenuComp(){
//        return findComponent(TopMenuComponent.class);
//    }
    public List<MenuItemComponent> menuItemComponents(){
        return findComponents(MenuItemComponent.class);
    }
}
