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
import model.DatabaseConnecter;
import view.RequestTable;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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

    private menu.Controller controller;

    private ArrayList<String> data;
    private ArrayList<String[]> liscenseList,driverList;

    public void initialize(){
        liscenseList = new ArrayList<String[]>();
        driverList = new ArrayList<String[]>();
        data = new ArrayList<String>();
        tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                scrollPane.setVvalue(0.0);
            }
        });
//        scrollPane.setVvalue(0.0);
    }

    public void setController(menu.Controller controller) {
        this.controller = controller;
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
            controller.setController(this.controller);
            controller.setcConfirmRequest(this);
            createData();
            controller.setData(data,3);
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

    public void checkChoice(){
        fixScroll();
        if(!liscensePlate.getSelectionModel().isEmpty()) {
            int index = liscensePlate.getSelectionModel().getSelectedIndex();
            typeCar.setText(liscenseList.get(index)[3]);
            brandCar.setText(liscenseList.get(index)[1]);
            genCar.setText(liscenseList.get(index)[2]);
        }if(!idDriver.getSelectionModel().isEmpty()) {
            int index = idDriver.getSelectionModel().getSelectedIndex();
            nameDriver.setText(driverList.get(index)[1]);
        }
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
        setUpCar();
        setUpDriver();

    }

    private void setUpCar(){
        ArrayList<String> unUseID = DatabaseConnecter.browseRfcIDByDate(LocalDate.parse(table.getStartDate()),LocalDate.parse(table.getEndDate()),"where staus='อนุมัติแล้ว'");
        if (unUseID.isEmpty()){
            liscenseList = DatabaseConnecter.browseCarInArray("select * from car where status='ใช้งานได้'");
        }else{
            String query = "select * from car where status='ใช้งานได้' and liscenseplate not in (";
            String query2 = "select liscenseplate from workassign where requestforcarid='"+unUseID.get(0)+"'";
            for (int i = 1;i<unUseID.size();i++){
                query2 += "or requestforcarid='"+unUseID.get(i)+"'";
            }
            query += query2+")";
//            System.out.println(query);
            liscenseList = DatabaseConnecter.browseCarInArray(query);
        }
        for (String[] i: liscenseList){
            liscensePlate.getItems().add(i[0]);
            System.out.println(Arrays.toString(i));
        }
    }

    private void setUpDriver(){
        ArrayList<String> unUseID = DatabaseConnecter.browseRfcIDByDate(LocalDate.parse(table.getStartDate()),LocalDate.parse(table.getEndDate()),"where staus='อนุมัติแล้ว'");
        if (unUseID.isEmpty()){
            driverList = DatabaseConnecter.browseDriverInArray("select * from driver");
        }else{
            String query = "select id from driver where id not in (";
            String query2 = "select driverid from workassign where requestforcarid='"+unUseID.get(0)+"'";
            for (int i = 1;i<unUseID.size();i++){
                query2 += "or requestforcarid='"+unUseID.get(i)+"'";
            }
            query += query2+")";
//            System.out.println(query);
            driverList = DatabaseConnecter.browseDriverInArray(query);
        }
        for (String[] i: driverList){
            idDriver.getItems().add(i[0]);
            System.out.println(Arrays.toString(i));
        }
    }
    private void createData() {
        Collections.addAll(data,table.getId(),liscensePlate.getSelectionModel().getSelectedItem().toString(),idDriver.getSelectionModel().getSelectedItem().toString());
    }
}
