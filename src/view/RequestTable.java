package view;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static java.lang.Math.round;
import static java.lang.String.format;

public class RequestTable {

    private String id;
    private String name;
    private String causeText;
    private String startDate;
    private String endDate;
    private String destination;
    private String distance;
    private String priceGas;
    private String idDriver;
    private String nameDriver;
    private String licensedPlate;
    private String typeCar;
    private String brandCar;
    private String genCar;
    private String detail;
    private String status;

    public RequestTable(String id, String name, String causeText, String staus, String startDate, String endDate, String destination, String distance, String priceGas, String idDriver, String nameDriver, String liscensePlate, String typeCar, String brandCar, String genCar, String detail) {
        this.id = id;
        this.name = name;
        this.causeText = causeText;
        this.status = staus;
        this.startDate = formatDate(startDate);
        this.endDate = formatDate(endDate);
        this.destination = destination;
        this.distance = formatComma(distance);
        this.priceGas = format2Decimal(formatComma(priceGas));
        this.idDriver = idDriver;
        this.nameDriver = nameDriver;
        this.licensedPlate = liscensePlate;
        this.typeCar = typeCar;
        this.brandCar = brandCar;
        this.genCar = genCar;
        this.detail = detail;
    }

    String formatComma(String value){
        return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(value));
    }

    String format2Decimal(String value){
        return value+".00";
    }

    String formatDate(String value){
        String[] date = value.split("-");
//        System.out.println(Arrays.toString(date));
        return date[2]+"-"+date[1]+"-"+date[0];
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCauseText() {
        return causeText;
    }

    public String getStatus() {
        return status;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getDistance() {
        return distance;
    }

    public String getPriceGas() {
        return priceGas;
    }

    public String getIdDriver() {
        return idDriver;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getLicensedPlate() {
        return licensedPlate;
    }

    public String getTypeCar() {
        return typeCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public String getGenCar() {
        return genCar;
    }

    public String getDetail() {
        return detail;
    }

}
