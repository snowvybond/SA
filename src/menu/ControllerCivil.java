package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerCivil extends Controller{

    @FXML protected TableColumn<RequestTable , String> idColumn;
    @FXML protected TableColumn<RequestTable , String> nameColumn;
    @FXML protected TableColumn<RequestTable , String> causeTextColumn;
    @FXML protected TableColumn<RequestTable , String> startDateColumn;
    @FXML protected TableColumn<RequestTable , String> endDateColumn;
    @FXML protected TableColumn<RequestTable , String> destinationColumn;
    @FXML protected TableColumn<RequestTable , String> distanceColumn;
    @FXML protected TableColumn<RequestTable , String> priceGasColumn;
    @FXML protected TableColumn<RequestTable , String> idDriverColumn;
    @FXML protected TableColumn<RequestTable , String> nameDriverColumn;
    @FXML protected TableColumn<RequestTable , String> licensedPlateColumn;
    @FXML protected TableColumn<RequestTable , String> typeCarColumn;
    @FXML protected TableColumn<RequestTable , String> brandCarColumn;
    @FXML protected TableColumn<RequestTable , String> modelCarColumn;
    @FXML protected TableColumn<RequestTable , String> detailColumn;
    @FXML protected TableView<RequestTable> table;

    public void initialize(){
        search();
    }
    public void handkeBtnConfirmClickAction(){
        showConfirmRequest();
    }

    private void showConfirmRequest(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/confirmRequest/confirmRequest.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            confirmRequest.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Confirm Request");
            stage.setScene(new Scene(root, 490, 590));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnRejectAction(){
        ShowAlertReject();
    }

    private void ShowAlertReject(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeaderConfirm("ยืนยันคำปฎิเสธการขอใช้รถรหัส XXXXX");
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnReportClickAction(){ showReport(); }

    private void showReport(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/report/report.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            report.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Report");
            stage.setScene(new Scene(root, 940, 600));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnSeeDetailClickAction(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/see/Detail.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            see.Controller c = loader.getController();
            c.setStage(stage);
            c.setController(this);
            c.setLocation("/menu/menuCivil.fxml");
            stage.setTitle("Detail");
            stage.setScene(new Scene(root, 490, 590));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleBtnSearchClickAction(){ search(); }

    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar";
//            query = "select * from requestforcar where user='"+userID+"'";
        }
        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
        int count = 0;
        for (ArrayList<String> i : allData){
            for (String j:i){
                System.out.println(j);
            }

            table.getItems().add(count++,new RequestTable(i.get(0) , i.get(1) , i.get(2) , i.get(3) , i.get(4) , i.get(5) , i.get(6) , i.get(7) , i.get(8) , i.get(9) , i.get(10) , i.get(11) , i.get(12) , "gen" , "detail"));

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            causeTextColumn.setCellValueFactory(new PropertyValueFactory<>("causeText"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
            distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
            priceGasColumn.setCellValueFactory(new PropertyValueFactory<>("priceGas"));
            idDriverColumn.setCellValueFactory(new PropertyValueFactory<>("idDriver"));
            nameDriverColumn.setCellValueFactory(new PropertyValueFactory<>("nameDriver"));
            licensedPlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensedPlate"));
            typeCarColumn.setCellValueFactory(new PropertyValueFactory<>("typeCar"));
            brandCarColumn.setCellValueFactory(new PropertyValueFactory<>("brandCar"));
            modelCarColumn.setCellValueFactory(new PropertyValueFactory<>("genCar"));
            detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        }
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
}
