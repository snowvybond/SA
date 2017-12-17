package changeStatus;

public class TableReport {

    private String typeCar;
    private String licensedPlate;
    private String brandCar;
    private String genCar;
    private String status;

    public TableReport(String typeCar, String licensedPlate, String brandCar, String genCar, String status) {
        this.typeCar = typeCar;
        this.licensedPlate = licensedPlate;
        this.brandCar = brandCar;
        this.genCar = genCar;
        this.status = status;
    }

    public String getTypeCar() {
        return typeCar;
    }
    public String getLicensedPlate() {
        return licensedPlate;
    }
    public String getBrandCar() {
        return brandCar;
    }
    public String getGenCar() {
        return genCar;
    }
    public String getStatus() {
        return status;
    }
}

