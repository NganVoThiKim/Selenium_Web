package test_data;

import com.google.gson.Gson;

public class TestGSON {
    public static void main(String[] args) {
        testDataArray();
        //testGsonSample();
        //testBuilderMethod();
    }
    private static void testDataArray(){
        String relativeDataFileLocation = "/src/main/java/test_data/data.json";
        ComputerData[] computerDatas = DataObjectBuilder.builderDataObjectFrom(relativeDataFileLocation, ComputerData[].class);
        for (ComputerData computerData : computerDatas) {
            System.out.println(computerData.getProcessor());
            System.out.println(computerData.getRam());
            System.out.println(computerData.getHdd());
            System.out.println(computerData.getOs());
            System.out.println(computerData.getSoftware());
        }
    }
    private static void testBuilderMethod(){
        String relativeDataFileLocation = "/src/main/java/test_data/data.json";
        ComputerData computerData = DataObjectBuilder.builderDataObjectFrom(relativeDataFileLocation, ComputerData.class);
        //System.out.println(computerData);
        System.out.println(computerData.getProcessor());
        System.out.println(computerData.getRam());
        System.out.println(computerData.getHdd());
        System.out.println(computerData.getOs());
        System.out.println(computerData.getSoftware());
    }
    private static void testGsonSample(){
        String JSONString = "{\n" +
                "\"processor\": \"25GHz\",\n" +
                "\"ram\": \"4GB\",\n" +
                "\"hdd\": \"256GB\",\n" +
                "\"os\": \"macOS\",\n" +
                "\"software\": \"photoshop\"\n" +
                "}";
        Gson gson = new Gson();

        // From JSON string to Object
        ComputerData computerData = gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);

        // From Object to JSON string
        System.out.println(gson.toJson(computerData));
    }
}
