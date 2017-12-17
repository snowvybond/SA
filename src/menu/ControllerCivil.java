package menu;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ControllerCivil extends Controller{

    @FXML private Button btnApprove;
    @FXML private Button btnReject;

    @FXML protected TableColumn<RequestTable , String> nameColumn;

    @FXML
    public void initialize(){
        super.initialize();
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
            RequestTable t = table.getSelectionModel().getSelectedItem();
            controller.setTable(t);
            controller.setUp();
            controller.setController(this);
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
        reject();
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            AlertMsg.Controller controller = loader.getController();
//            controller.setStage(stage);
//            ArrayList<String> data = new ArrayList<>();
//            data.add(table.getSelectionModel().getSelectedItem().getId());
//            controller.setData(data,2);
//            controller.setController(this);
//            stage.setTitle("Confirmation");
//            stage.setScene(new Scene(root, 380, 130));
//            stage.setResizable(false);
//            controller.setHeaderConfirm("ยืนยันคำปฎิเสธการขอใช้รถรหัส "+table.getSelectionModel().getSelectedItem().getId());
//            stage.show();
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
    }

    @Override
    public void checkChoice(){
        super.checkChoice();


        if(!table.getSelectionModel().isEmpty()&&table.getSelectionModel().getSelectedItem().getStatus().equals("รออนุมัติ")) {
            if (table.getSelectionModel().getSelectedItems().size() > 1){
                btnApprove.setDisable(true);
            }else{
                btnApprove.setDisable(false);
            }
            btnReject.setDisable(false);
        }else {
            btnApprove.setDisable(true);
            btnReject.setDisable(true);
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
            stage.setScene(new Scene(root, 740, 630));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    protected void displayTable(String query) {
        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
//        Collections.reverse(allData);
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new RequestTable(i.get(0),i.get(1),i.get(2),i.get(3),i.get(4),i.get(5),i.get(6),i.get(7),i.get(8),i.get(9),i.get(10),i.get(11),i.get(12),i.get(13),i.get(14),i.get(15)));
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            causeTextColumn.setCellValueFactory(new PropertyValueFactory<>("causeText"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
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
    public void handleBtnSeeDetailClickAction (ActionEvent event){

        if (table.getSelectionModel().isEmpty()){
            selectAlert();
        }
        else {
            RequestTable t = table.getSelectionModel().getSelectedItem();
            seeDetail(this,"/menu/menuCivil.fxml",t);
        }
    }


    public void seeDetail(Controller controller ,String location ,RequestTable table){
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/see/Detail.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            see.Controller c = loader.getController();
            c.setStage(stage);
            c.setController(controller);
            c.setLocation(location);
            c.setData(table);
            c.setController(this);
            c.setName(table.getName());
            stage.setTitle("Detail");
            stage.setScene(new Scene(root, 490, 590));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void reject(){
        ObservableList<RequestTable> selected = table.getSelectionModel().getSelectedItems();
        for(RequestTable t:selected){
            String id = t.getId();
            DatabaseConnecter.updateString("update requestforcar set staus='ปฏิเสธคำขอ' where id='"+id+"'");
        }
        search();


    }


}
