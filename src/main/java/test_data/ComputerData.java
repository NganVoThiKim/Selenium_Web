package test_data;

public class ComputerData {
    private String processor;
    private String ram;
    private String hdd;
    private String os;
    private String software;

    public ComputerData(String processor, String ram, String hdd, String os, String software) {
        this.processor = processor;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.software = software;
    }

    public String getProcessor() {
        return processor;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getSoftware() {
        return software;
    }

    @Override
    public String toString() {
        return "ComputerData{" +
                "processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", hdd='" + hdd + '\'' +
                ", os='" + os + '\'' +
                ", software='" + software + '\'' +
                '}';
    }
}
