package changeStatus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML protected TableColumn<TableReport, String> typeCarColumn;
    @FXML protected TableColumn<TableReport, String> licensedPlateColumn;
    @FXML protected TableColumn<TableReport, String> brandCarColumn;
    @FXML protected TableColumn<TableReport, String> modelCarColumn;
    @FXML protected TableColumn<TableReport, String> statusColumn;
    @FXML protected TableView<TableReport> table;
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
        System.out.println(date);
        displayTable();
    }

    @FXML
    private void updateReport(){
        System.out.println("yes");
    }

    private void displayTable(){
        String query = "select * from car";
        ArrayList<ArrayList> allData = DatabaseConnecter.browseCar(query);
        int totalMission = 0;
        int totalDistance = 0;
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new TableReport(i.get(0),i.get(1),i.get(2),i.get(3),i.get(4)));
            typeCarColumn.setCellValueFactory(new PropertyValueFactory<>("typeCar"));
            licensedPlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensedPlate"));
            brandCarColumn.setCellValueFactory(new PropertyValueFactory<>("brandCar"));
            modelCarColumn.setCellValueFactory(new PropertyValueFactory<>("genCar"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            totalMission += Integer.parseInt(i.get(4));
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
}
