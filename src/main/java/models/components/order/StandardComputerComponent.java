package models.components.order;

import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;

import java.util.List;

@ComponentCSSSelector(".product-essential")
public class StandardComputerComponent extends ComputerEssentialComponent {

    public final static By productAttrSel = By.cssSelector("select[id^='product_attribute']");
    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {
        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processorDropdownEle = component.findElements(productAttrSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processorDropdownEle, type);
    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownEle = component.findElements(productAttrSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownEle, type);
    }

    private String selectOption(WebElement dropdownEle, String type) {
        SelectEx select = new SelectEx(dropdownEle);
        List<WebElement> allOptionsEle = select.getOptions();
        String fullStrOption = null;

        // Logic
        for (WebElement optionEle : allOptionsEle) {
            String currentOptionText = optionEle.getText();
            String optionTextWithoutSpaces = currentOptionText.trim().replace(" ", "");
            if (optionTextWithoutSpaces.startsWith(type)) {
                fullStrOption = currentOptionText;
                break;
            }
        }

        // IF null -> Exception
        if (fullStrOption == null) {
            throw new RuntimeException("[ERR] The option " + type + "is not existing");
        }
        select.selectByVisibleText(fullStrOption);
        return fullStrOption;
    }
}
