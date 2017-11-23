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

    public RequestTable(String id, String name, String causeText, String startDate, String endDate, String destination, String distance, String priceGas, String idDriver, String nameDriver, String liscensePlate, String typeCar, String brandCar, String genCar, String detail) {
        this.id = id;
        this.name = name;
        this.causeText = causeText;
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


//    public void search(){
//
//        String query = "";
//        if (c1.isSelected()){  //all
//            query = "select * from requestforcar where user='"+userID+"'";
//        }
//        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
//        int count = 0;
//        for (ArrayList<String> i : allData){
//            for (String j:i){
//                System.out.println(j);
//            }
//
//            table.getItems().add(count++,new RequestTable(i.get(0) , i.get(1) , i.get(2) , i.get(3) , i.get(4) , i.get(5) , i.get(6) , i.get(7) , i.get(8) , i.get(9) , i.get(10) , i.get(11) , i.get(12) , "gen" , "detail"));
//
//            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//            causeTextColumn.setCellValueFactory(new PropertyValueFactory<>("causeText"));
//            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
//
////            @FXML protected TableColumn<RequestTable , String> idColumn;
////            @FXML protected TableColumn<RequestTable , String> nameColumn;
////            @FXML protected TableColumn<RequestTable , String> causeTextColumn;
////            @FXML protected TableColumn<RequestTable , String> startDateColumn;
////            @FXML protected TableColumn<RequestTable , String> endDateColumn;
////            @FXML protected TableColumn<RequestTable , String> destinationColumn;
////            @FXML protected TableColumn<RequestTable , String> distanceColumn;
////            @FXML protected TableColumn<RequestTable , String> priceGasColumn;
////            @FXML protected TableColumn<RequestTable , String> idDriverColumn;
////            @FXML protected TableColumn<RequestTable , String> nameDriverColumn;
////            @FXML protected TableColumn<RequestTable , String> licensedPlateColumn;
////            @FXML protected TableColumn<RequestTable , String> typeCarColumn;
////            @FXML protected TableColumn<RequestTable , String> brandCarColumn;
////            @FXML protected TableColumn<RequestTable , String> modelCarColumn;
////            @FXML protected TableColumn<RequestTable , String> detailColumn;
////            @FXML private TableView<RequestTable> table;
//        }
//    }
}
