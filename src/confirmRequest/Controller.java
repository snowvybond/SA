package confirmRequest;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerCivil;
import view.RequestTable;


import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField causeText;

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
    private ChoiceBox idDriver;

    @FXML
    private TextField nameDriver;

    @FXML
    private ChoiceBox liscensePlate;

    @FXML
    private TextField typeCar;

    @FXML
    private TextField brandCar;

    @FXML
    private TextField genCar;

    @FXML
    private TextArea detail;

    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private Tab tab = new Tab();
    private boolean isSet;

    private RequestTable table;

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

    public void handleBtnOKClickAction(){
        if(checkData()){
            showConfirm();
        }else{
            showAlert();
        }
    }

    private void showAlert(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertWarning.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setHeaderWarning("กรอกข้อมูลไม่ครบ");
            stage.setTitle("Alert Warning");
            stage.setScene(new Scene(root, 380, 100));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private boolean checkData(){

        if(idDriver.getValue() == null) return false;
        if(nameDriver.getText().isEmpty()) return false;
        if(liscensePlate.getValue() == null) return false;
        if(typeCar.getText().isEmpty()) return false;
        if(brandCar.getText().isEmpty()) return false;
        if(genCar.getText().isEmpty()) return false;

        return true;
    }

    private void showConfirm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setcConfirmRequest(this);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeaderConfirm("ยืนยันคำอนุมัติการขอใช้รถ");
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnCancelClickAction(){
        goToMenu();
    }

    private void goToMenu() {
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/menuCivil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ControllerCivil controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("ตารางรายการขอใช้รถ");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setTable(RequestTable table) {
        this.table = table;
    }

    public void setUp(){
        id.setText(table.getId());
        name.setText(table.getName());
        causeText.setText(table.getCauseText());
        startDate.setText(table.getStartDate());
        endDate.setText(table.getEndDate());
        destination.setText(table.getDestination());
        distance.setText(table.getDistance());
        priceGas.setText(table.getPriceGas());
        detail.setText(table.getDetail());
    }
}
