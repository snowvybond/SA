package see;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerCivil;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;

public class Controller {


    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField causeText;

    @FXML
    private TextField status;

    @FXML
    private TextField startDate;

    @FXML
    private TextField endDate;

    @FXML
    private TextField destination;

    @FXML
    private TextField distance;

    @FXML
    private TextField priceGas;

    @FXML
    private TextField idDriver;

    @FXML
    private TextField nameDriver;

    @FXML
    private TextField liscensedPlate;

    @FXML
    private TextField typeCar;

    @FXML
    private TextField brandCar;

    @FXML
    private TextField genCar;

    public void setId(String id) {
        this.id.setText(id);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setCauseText(String causeText) {
        this.causeText.setText(causeText);
    }

    public void setStatus(String status) { this.status.setText(status); }

    public void setStartDate(String startDate) {
        this.startDate.setText(startDate);
    }

    public void setEndDate(String endDate) {
        this.endDate.setText(endDate);
    }

    public void setDestination(String destination) {
        this.destination.setText(destination);
    }

    public void setDistance(String distance) {
        this.distance.setText(distance);
    }

    public void setPriceGas(String priceGas) {
        this.priceGas.setText(priceGas);
    }

    public void setIdDriver(String idDriver) {
        this.idDriver.setText(idDriver);
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver.setText(nameDriver);
    }



    public void setTypeCar(String typeCar) {
        this.typeCar.setText(typeCar);
    }

    public void setBrandCar(String brandCar) {
        this.brandCar.setText(brandCar);
    }

    public void setGenCar(String genCar) {
        this.genCar.setText(genCar);
    }

    public void setDetail(String detail) {
        this.detail.setText(detail);
    }

    @FXML
    private TextArea detail;

    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private menu.Controller controller;
    private String location;
    private Tab tab = new Tab();
    private boolean isSet;

    public void initialize(){
        tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                scrollPane.setVvalue(0.0);
            }
        });
//        scrollPane.setVvalue(0.0);
    }

    public void fixScroll(){
        if(!isSet) {
            scrollPane.setVvalue(0.0);
            isSet = true;
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }
    public void setController(menu.Controller controller) { this.controller = controller; }
    public void setLocation(String location) { this.location = location; }

    public void handleBtnOKClickAction(){
        stage.close();
    }

    public void handleBtnCancelClickAction(){
        goToMenu();
    }

    private void goToMenu() {
        stage.close();
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent root = loader.load();
            Stage stage = new Stage();
            controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("ตารางรายการขอใช้รถ");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void setData (RequestTable table){
        id.setText(table.getId());
        causeText.setText(table.getCauseText());
        status.setText(table.getStatus());
        startDate.setText(table.getStartDate());
        endDate.setText(table.getEndDate());
        destination.setText(table.getDestination());
        distance.setText(table.getDistance());
        priceGas.setText(table.getPriceGas());
        idDriver.setText(table.getIdDriver());
        nameDriver.setText(table.getNameDriver());
        liscensedPlate.setText(table.getLicensedPlate());
        typeCar.setText(table.getTypeCar());
        brandCar.setText(table.getBrandCar());
        genCar.setText(table.getGenCar());
    }
}
