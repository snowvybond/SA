package report;

public class TableReport {

    private String typeCar;
    private String licensedPlate;
    private String brandCar;
    private String genCar;
    private String totalHour;
    private String totalDistance;

    public TableReport(String typeCar, String licensedPlate, String brandCar, String genCar, String totalHour, String totalDistance) {
        this.typeCar = typeCar;
        this.licensedPlate = licensedPlate;
        this.brandCar = brandCar;
        this.genCar = genCar;
        this.totalHour = totalHour;
        this.totalDistance = totalDistance;
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
    public String getTotalHour() {
        return totalHour;
    }
    public String getTotalDistance() {
        return totalDistance;
    }
}
