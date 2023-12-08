package tests.order.computer;

import models.components.order.StandardComputerComponent;
import org.testng.annotations.Test;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;

public class BuyingStandardComputerTests extends BaseTest {
    @Test
    public void testStandardComputerBuying(){
        driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
        OrderComputerFlow orderComputerFlow = new OrderComputerFlow(driver, StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCart();
    }
}
