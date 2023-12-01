package models.components.global.footer;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCSSSelector(".footer-menu-wrapper")
public class FooterComponent extends Component {
    public FooterComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }
    public InformationColumnComponent informationColumnComp(){
        return findComponent(InformationColumnComponent.class);
    }
    public CustomerServiceColumnComponent customerServiceColumnComp(){
        return findComponent(CustomerServiceColumnComponent.class);
    }
    public MyAccountColumnComponent myAccountColumnComp(){
        return findComponent(MyAccountColumnComponent.class);
    }
    public FollowUsColumnComponent followUsColumnComp(){
        return findComponent(FollowUsColumnComponent.class);
    }
}
