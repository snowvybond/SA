package report;

import java.text.NumberFormat;
import java.util.Locale;

public class TableReport {

    private String typeCar;
    private String licensedPlate;
    private String brandCar;
    private String genCar;
    private String totalMission;
    private String totalDistance;

    public TableReport(String typeCar, String licensedPlate, String brandCar, String genCar, String totalHour, String totalDistance) {
        this.typeCar = typeCar;
        this.licensedPlate = licensedPlate;
        this.brandCar = brandCar;
        this.genCar = genCar;
        this.totalMission = addComma(totalHour);
        this.totalDistance = addComma(totalDistance);
    }

    String addComma(String value){
        return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(value));
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
        return totalMission;
    }
    public String getTotalDistance() {
        return totalDistance;
    }
}

