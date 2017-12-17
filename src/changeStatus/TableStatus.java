package changeStatus;

public class TableStatus {

    private String typeCar;
    private String licensedPlate;
    private String brandCar;
    private String genCar;
    private String status;

    public TableStatus(String typeCar, String licensedPlate, String brandCar, String genCar, String status) {
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

