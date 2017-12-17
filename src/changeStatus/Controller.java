package changeStatus;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import menu.ControllerCar;
import menu.ControllerCivil;
import model.DatabaseConnecter;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML protected Label lableReport;
    @FXML protected ChoiceBox choiceBox;

    @FXML protected TableColumn<TableStatus, String> typeCarColumn;
    @FXML protected TableColumn<TableStatus, String> licensedPlateColumn;
    @FXML protected TableColumn<TableStatus, String> brandCarColumn;
    @FXML protected TableColumn<TableStatus, String> modelCarColumn;
    @FXML protected TableColumn<TableStatus, String> statusColumn;
    @FXML protected TableView<TableStatus> table;
//    @FXML protected Label missionText;
//    @FXML protected Label distanceText;
    @FXML protected Label date;

    private String cache;

    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

    public void handleBtnBackClickAction(){
        showMenu();
    }


    public void initialize(){
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        System.out.println(date);
        displayTable();
    }

    @FXML
    private void updateReport(){

    }

    private void displayTable(){
        table.getItems().clear();
        String query = "select type, liscenseplate, brand, model, status from car where liscenseplate not in (select liscenseplate from workassign where requestforcarid in (select id from requestforcar where staus='กำลังปฏิบัติงาน' or staus='อนุมัติแล้ว' or staus='รออนุมัติ'))";
        ArrayList<ArrayList> allData = DatabaseConnecter.browseCarForStaus(query);
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new TableStatus(i.get(0),i.get(1),i.get(2),i.get(3),i.get(4)));
            typeCarColumn.setCellValueFactory(new PropertyValueFactory<>("typeCar"));
            licensedPlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensedPlate"));
            brandCarColumn.setCellValueFactory(new PropertyValueFactory<>("brandCar"));
            modelCarColumn.setCellValueFactory(new PropertyValueFactory<>("genCar"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        }
//        missionText.setText(Integer.toString(totalMission));
//        distanceText.setText(Integer.toString(totalDistance));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void showMenu() {
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/menuCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ControllerCar controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("ตารางรายการขอใช้รถ");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    protected void handleBtnUseAction(){
        useSet();
        displayTable();

    }
    @FXML
    protected void handleBtnUnUseAction(){
        unUseSet();
        displayTable();

    }

    private void useSet(){
        ObservableList<TableStatus> selected= table.getSelectionModel().getSelectedItems();
        for (TableStatus t:selected){
            String query = "update car set status='ใช้งานได้' where liscenseplate='"+t.getLicensedPlate()+"'";
            DatabaseConnecter.updateString(query);
        }

    }
    private void unUseSet(){
        ObservableList<TableStatus> selected= table.getSelectionModel().getSelectedItems();
        for (TableStatus t:selected){
            String query = "update car set status='ไม่สามารถใช้งานได้' where liscenseplate='"+t.getLicensedPlate()+"'";
            DatabaseConnecter.updateString(query);
        }
    }

}
