package models.pages;

import models.components.FooterComponent;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public FooterComponent footerComp(){
        return new FooterComponent(driver);
    }
}
