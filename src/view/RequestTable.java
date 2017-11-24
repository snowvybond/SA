package view;

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
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.distance = distance;
        this.priceGas = priceGas;
        this.idDriver = idDriver;
        this.nameDriver = nameDriver;
        this.licensedPlate = liscensePlate;
        this.typeCar = typeCar;
        this.brandCar = brandCar;
        this.genCar = genCar;
        this.detail = detail;
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
