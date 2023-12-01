package models.pages;

import org.openqa.selenium.WebDriver;

public class InternalLoginPage extends LoginPage{

    @Override
    public void inputUserName(String usernameStr) {
        System.out.println(usernameStr);
    }
}
