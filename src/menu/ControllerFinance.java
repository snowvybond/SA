package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerFinance extends Controller {


    public void handleBtnCreateAction(){
        createRequest();
//        if (isCreate()){
//            showAlertWarning();
//        }
//        else{
//            createRequest();
//        }
    }


//    private void showAlertWarning(){
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertWarning.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            AlertMsg.Controller controller = loader.getController();
//            controller.setStage(stage);
//            controller.setHeaderWarning("คุณมีรายการขอใช้รถแล้ว");
//            stage.setTitle("Warning");
//            stage.setScene(new Scene(root, 380, 100));
//            stage.setResizable(false);
//            stage.show();
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//    }

    private void createRequest(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/request/request.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            request.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setUserID(userID);
            controller.setUp();
            stage.setTitle("รายละเอียดขอใช้รถ");
            stage.setScene(new Scene(root, 425, 740));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    @Override
    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar where user='"+userID+"'";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='รออนุมัติ'";
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='อนุมัติแล้ว'";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where user='"+userID+"'"+"and id='"+id.getText()+"'";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='คืนแล้ว'";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='ปฏิเสธคำขอ'";
        }
        else if (c8.isSelected()){ //date
            ArrayList<String> ids = DatabaseConnecter.browseRfcIDByDate(startDate.getValue(),endDate.getValue(),"where user='"+userID+"'");
            if (!ids.isEmpty()){
                query = "select * from requestforcar where id='" + ids.get(0) + "'";
                for (int i = 1; i < ids.size(); i++) {
                    query += "or id='" + ids.get(i) + "'";
                }
            }

        }
        displayTable(query);
    }

    @FXML
    public void handleBtnSeeDetailClickAction (ActionEvent event){

        if (table.getSelectionModel().isEmpty()){
            selectAlert();
        }
        else {
            RequestTable t = table.getSelectionModel().getSelectedItem();
            seeDetail(this,"/menu/menuFinance.fxml",t);
        }
    }

//    private boolean isCreate(){
//        boolean check;
//        String query = "select id from requestforcar where user='"+userID+"' and (staus='อนุมัติแล้ว' or staus='รออนุมัติ') ";
//        ArrayList<String> idForRequestForCarS = DatabaseConnecter.browseStringInArray(query);
//        int remainRequestForCar = idForRequestForCarS.size();
//        System.out.println(remainRequestForCar);
//        if (remainRequestForCar == 0){
//            check = false;
//        }
//        else {
//            check = true;
//        }
//        return check;
//    }

}
