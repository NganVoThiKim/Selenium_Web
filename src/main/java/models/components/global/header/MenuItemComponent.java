package models.components.global.header;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

//@ComponentCSSSelector(".top-menu")
//public class TopMenuComponent extends Component {
//    public TopMenuComponent(WebDriver driver, WebElement component) {
//        super(driver, component);
//    }
//    public List<MainMenuComp> mainMenusComp(){
//        return findComponents(MainMenuComp.class);
//    }
//
//    @ComponentCSSSelector(".top-menu > li")
//    public static class MainMenuComp extends Component{
//
//        public MainMenuComp(WebDriver driver, WebElement component) {
//            super(driver, component);
//        }
//        public WebElement mainMenuLinkEle(){
//            return component.findElement(By.tagName("a"));
//        }
//        public List<SubMenuComp> subMenusComp(){
//            Actions actions = new Actions(driver);
//            actions.moveToElement(component).perform();
//            return findComponents(SubMenuComp.class);
//        }
//    }
//    @ComponentCSSSelector(".sublist li a")
//    public static class SubMenuComp extends Component{
//
//        public SubMenuComp(WebDriver driver, WebElement component) {
//            super(driver, component);
//        }
//    }
//}

// Refactor Code
@ComponentCSSSelector(".top-menu > li")
public class MenuItemComponent extends Component {
    public MenuItemComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public WebElement menuItemLink(){
        return component.findElement(By.tagName("a"));
    }
    public List<WebElement> subMenuItems(){
        Actions actions = new Actions(driver);
        actions.moveToElement(component).perform();
        return component.findElements(By.cssSelector(".sublist li a"));
    }
}