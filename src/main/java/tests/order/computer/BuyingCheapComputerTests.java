package tests.order.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.ComputerData;
import test_data.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import tests.BaseTest;

public class BuyingCheapComputerTests extends BaseTest {
    //    @Test
//    public void testCheapComputerBuying(){
//        // Data Driven
//        ComputerData[] computerDatas = new ComputerData[]{};
//        for (ComputerData computerData : computerDatas) {
//            driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
//            OrderComputerFlow orderComputerFlow = new OrderComputerFlow(driver, CheapComputerComponent.class);
//            orderComputerFlow.buildCompSpecAndAddToCart();
//        }
//    }
    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData) {
        //System.out.println(computerData);
        driver.get("https://demowebshop.tricentis.com/build-your-cheap-own-computer");
        int quantity = 1;
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData, quantity);
        orderComputerFlow.buildCompSpec();
        orderComputerFlow.addItemToCart();
        orderComputerFlow.verifyShoppingCartPage();
    }

    @DataProvider(name = "computerData")
    public ComputerData[] computerData_() {
//        ComputerData computerData = new ComputerData("fast", "4GB", "1TB", "Window", "Adobe photoshop");
//        return new ComputerData[]{computerData, computerData};

        // Read data from CheapComputerDataList.json
        String relativeDataFileLocation = "/src/main/java/test_data/CheapComputerDataList.json";
        return DataObjectBuilder.builderDataObjectFrom(relativeDataFileLocation, ComputerData[].class);
    }
}

