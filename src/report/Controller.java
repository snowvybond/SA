package report;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import menu.ControllerCivil;
import model.DatabaseConnecter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Controller {

    @FXML protected Label lableReport;
    @FXML protected ChoiceBox choiceBox;

    @FXML protected TableColumn<TableReport , String> typeCarColumn;
    @FXML protected TableColumn<TableReport , String> licensedPlateColumn;
    @FXML protected TableColumn<TableReport , String> brandCarColumn;
    @FXML protected TableColumn<TableReport , String> modelCarColumn;
    @FXML protected TableColumn<TableReport , String> totalDistanceColumn;
    @FXML protected TableColumn<TableReport , String> blackColumn;
    @FXML protected TableColumn<TableReport , String> totalHourColumn;
    @FXML protected TableView<TableReport> table;
    @FXML protected Label missionText;
    @FXML protected Label distanceText;
    @FXML protected Label date;

    private String cache;

    private Stage stage;

    public void setStage(Stage stage) { this.stage = stage; }

    public void handleBtnBackClickAction(){
        showMenu();
    }


    public void initialize(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        System.out.println(date);
        this.date.setText(this.date.getText()+date);
        addChoiceBox();
        displayTable();
    }

    private void addChoiceBox(){
        choiceBox.getItems().add("มกราคม");
        choiceBox.getItems().add("กุมภาพันธ์");
        choiceBox.getItems().add("มีนาคม");
        choiceBox.getItems().add("เมษายน");
        choiceBox.getItems().add("พฤษภาคม");
        choiceBox.getItems().add("มิถุนายน");
        choiceBox.getItems().add("กรกฎาคม");
        choiceBox.getItems().add("สิงหาคม");
        choiceBox.getItems().add("กันยายน");
        choiceBox.getItems().add("ตุลาคม");
        choiceBox.getItems().add("พฤศจิกายน");
        choiceBox.getItems().add("ธันวาคม");
        choiceBox.getItems().add("ประจำปี");

        choiceBox.getSelectionModel().selectFirst();
        cache = choiceBox.getValue().toString();
    }

    @FXML
    private void updateReport(){
        if(!cache.equals(choiceBox.getValue().toString()) && choiceBox.getValue().equals("ประจำปี")){
            cache = choiceBox.getValue().toString();
            System.out.println(cache);
            lableReport.setText("รายงานการใช้รถยนต์"+cache);
            totalDistanceColumn.setVisible(true);
            blackColumn.setVisible(false);
        }
        if(!cache.equals(choiceBox.getValue().toString())){
            cache = choiceBox.getValue().toString();
            System.out.println(cache);
            lableReport.setText("รายงานการใช้รถยนต์เดือน"+cache);
            totalDistanceColumn.setVisible(false);
            blackColumn.setVisible(true);
        }
    }

    private void displayTable(){
        String query = "select * from car";
        ArrayList<ArrayList> allData = DatabaseConnecter.browseCar(query);
        int totalMission = 0;
        int totalDistance = 0;
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new TableReport(i.get(0),i.get(1),i.get(2),i.get(3),i.get(4) , i.get(5)));
            typeCarColumn.setCellValueFactory(new PropertyValueFactory<>("typeCar"));
            licensedPlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensedPlate"));
            brandCarColumn.setCellValueFactory(new PropertyValueFactory<>("brandCar"));
            modelCarColumn.setCellValueFactory(new PropertyValueFactory<>("genCar"));
            totalHourColumn.setCellValueFactory(new PropertyValueFactory<>("totalHour"));
            totalDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("totalDistance"));
            totalMission += Integer.parseInt(i.get(4));
            totalDistance += Integer.parseInt(i.get(5));
        }
        missionText.setText(Integer.toString(totalMission));
        distanceText.setText(Integer.toString(totalDistance));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void showMenu() {
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
}
