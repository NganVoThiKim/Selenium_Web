package support.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectEx extends Select {
    private static String OPTION_1 = "Option 1";
    private static String OPTION_2 = "Option 2";
    public SelectEx(WebElement element) {
        super(element);
    }
    public void selectOption1()
    {
        selectByVisibleText(OPTION_1);
    }
    public void selectOption2()
    {
        selectByVisibleText(OPTION_2);
    }
}
