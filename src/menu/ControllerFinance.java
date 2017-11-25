package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.RequestTable;

import java.io.IOException;
import java.util.Optional;

public class ControllerFinance extends Controller {

    private boolean isCreate = false;

    public void handleBtnCreateAction(){

        if(true) { ShowAlertWarning(); }
        else createRequest();
//        isCreate = !isCreate;
    }


    private void ShowAlertWarning(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertWarning.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setHeaderWarning("คุณมีรายการขอใช้รถแล้ว");
            stage.setTitle("Warning");
            stage.setScene(new Scene(root, 380, 100));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void createRequest(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/request/request.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            request.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("รายละเอียดขอใช้รถ");
            stage.setScene(new Scene(root, 425, 660));
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
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='wait'";
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='approve'";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where user='"+userID+"'"+"and id='"+id.getText()+"'";
        }
        else if (c5.isSelected()){ //working
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='working'";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='returned'";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='reject'";
        }
        else if (c8.isSelected()){ //date
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='wait'";
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

}
