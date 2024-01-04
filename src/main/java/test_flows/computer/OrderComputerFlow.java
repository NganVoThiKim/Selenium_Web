package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import test_data.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private Double itemTotalPrice;
    private int quantity;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialCompClass, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialCompClass = computerEssentialCompClass;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpec() {
        //System.out.println(this.computerData);

        // Other steps...
        try {
            ComputerEssentialComponent computerEssentialComp = new ComputerItemDetailPage(driver).computerComp(computerEssentialCompClass);
            computerEssentialComp.unselectDefaultOptions();
            String processorFullStr = computerEssentialComp.selectProcessorType(this.computerData.getProcessor());
            double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
            String ramFullStr = computerEssentialComp.selectRAMType(this.computerData.getRam());
            double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
            String hddFullStr = computerEssentialComp.selectHDD(this.computerData.getHdd());
            double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
            String softwareFullStr = computerEssentialComp.selectSoftware(this.computerData.getSoftware());
            double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);
            double osAdditionalPrice = 0;
            String osDataOption = this.computerData.getOs();
            if (osDataOption != null) {
                String osFullStr = computerEssentialComp.selectOS(osDataOption);
                osAdditionalPrice = extractAdditionalPrice(osFullStr);
            }
            // Set product quantity
            computerEssentialComp.setProductQuantity(this.quantity);

            // Calculate item total price = (base item + additional prices) * quantity
            double additionalPrices = processorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice + softwareAdditionalPrice + osAdditionalPrice;
            double baseItemPrice = computerEssentialComp.priceProduct();
            this.itemTotalPrice = (baseItemPrice + additionalPrices) * this.quantity;
            System.out.println("itemTotalPrice = " + itemTotalPrice);

        } catch (Exception e) {
            e.printStackTrace();
        }
        // The rest steps
    }
    public void addItemToCart(){
        ComputerItemDetailPage computerItemDetailPage = new ComputerItemDetailPage(driver);
        ComputerEssentialComponent computerEssentialComp = computerItemDetailPage.computerComp(computerEssentialCompClass);
        computerEssentialComp.clickingOnAddToCardBtn();
        //computerEssentialComp.waitUntilItemAddedToCart();
        computerItemDetailPage.headerComp().clickingOnShoppingCartLink();
        try {
            Thread.sleep(2000);
        }catch (Exception ignored){}
    }
    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComponent = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.printf("%s: %f \n", priceType, priceCategories.get(priceType));
        }
    }
    private double extractAdditionalPrice(String optionStr) {
        // Medium [+15.00]
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(optionStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[+-]", ""));
        }
        return price;
    }
}