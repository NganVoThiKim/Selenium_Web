package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckoutOptionPage;
import models.pages.CheckoutPage;
import models.pages.ComputerItemDetailPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {
    private Class<T> computerEssentialCompClass;
    private WebDriver driver;
    private ComputerData computerData;
    private Double itemTotalPrice;
    private int quantity;
    private UserDataObject defaultCheckoutUser;

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
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.cartItemRowComps();
        Assert.assertFalse(cartItemRowComps.isEmpty(), "[ERR] There isn't items in the shopping card!");

        // Verify shopping card detail
        double currentSubTotals = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubTotals = currentSubTotals + cartItemRowComp.productSubTotal();
            currentTotalUnitPrices = currentTotalUnitPrices + (cartItemRowComp.productUnitPrice() * cartItemRowComp.productQuantity());
        }
        Assert.assertEquals(currentSubTotals, currentTotalUnitPrices, "[ERR] Sub-Total of shopping card is incorrect");

        // Verify checkout prices
        TotalComponent totalComponent = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        Assert.assertFalse(priceCategories.keySet().isEmpty(), "[ERR] Checkout price info is empty!");
        double checkoutSubTotal = 0;
        double checkoutTotal = 0;
        double checkoutOtherFees = 0;
        for (String priceType : priceCategories.keySet()) {
            System.out.printf("%s: %f \n", priceType, priceCategories.get(priceType));
            Double priceValue = priceCategories.get(priceType);
            if(priceType.startsWith("Sub-Total")){
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFees += priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal,currentSubTotals, "[ERR] Checkout sub-total is incorrect");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFees), "[ERR] Checkout total is incorrect");
    }
    public void agreeTOSAndCheckout(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalCompt = shoppingCartPage.totalComp();
        totalCompt.checkAgreeTOS();
        totalCompt.clickCheckoutBtn();

        // This is exception case, please do not use one flow method for more than one page
        new CheckoutOptionPage(driver).checkoutOptionComp().clickCheckoutAsGuestBtn();
    }
    public void inputBillingAddress(){
        String defaultCheckoutUserDataLocation = "/src/main/java/test_data/user/DefaultCheckoutUser.json";
        this.defaultCheckoutUser = DataObjectBuilder.builderDataObjectFrom(defaultCheckoutUserDataLocation, UserDataObject.class);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        BillingAddressComponent billingAddressComp = checkoutPage.billingAddressComp();
        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstName(defaultCheckoutUser.getFirstName());
        billingAddressComp.inputLastName(defaultCheckoutUser.getLastName());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.inputCompany(defaultCheckoutUser.getCompany());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAddress1(defaultCheckoutUser.getAdd1());
        billingAddressComp.inputAddress2(defaultCheckoutUser.getAdd2());
        billingAddressComp.inputZipcode(defaultCheckoutUser.getZipcode());
        billingAddressComp.inputPhoneNumber(defaultCheckoutUser.getPhoneNumber());
        billingAddressComp.inputFaxNumber(defaultCheckoutUser.getFaxNumber());
        billingAddressComp.clickContinueBtn();
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