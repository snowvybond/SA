package menu;

import AlertMsg.ReturnCarController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ControllerCar extends Controller{

    @FXML
    private Button btnReturnCar;

    public void handleBtnReturnAction(){
        showAlertReturn();
    }

    @Override
    public void checkChoice(){
        super.checkChoice();
        if(!table.getSelectionModel().isEmpty()&&table.getSelectionModel().getSelectedItem().getStatus().equals("อนุมัติแล้ว")) {
            btnReturnCar.setDisable(false);
        }else btnReturnCar.setDisable(true);
    }

    private void showAlertReturn(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/returnCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ReturnCarController controller = loader.getController();
            controller.setStage(stage);
            controller.setController(this);
            controller.setTable(table.getSelectionModel().getSelectedItem());
            controller.setHeaderReturnCar(table.getSelectionModel().getSelectedItem().getLicensedPlate());
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 550, 150));
            stage.setResizable(false);
            controller.setStage(stage);
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
            query = "select * from requestforcar where not staus='ปฏิเสธคำขอ' and not staus='รออนุมัติ'";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where staus='รออนุมัติ'";
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where staus='อนุมัติแล้ว'";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where id='"+id.getText()+"'";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where staus='คืนแล้ว'";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where staus='ปฏิเสธคำขอ'";
        }
        else if (c8.isSelected()){ //date
            ArrayList<String> ids = DatabaseConnecter.browseRfcIDByDate(startDate.getValue(),endDate.getValue(),"");
            if (!ids.isEmpty()){
                query = "select * from requestforcar where id='" + ids.get(0) + "'";
                for (int i = 1; i < ids.size(); i++) {
                    query += "or id='" + ids.get(i) + "'";
                }
            }
        }
        displayTable(query);
    }

    public void handleBtnChangeStutusAction(){
        showAlertChangeStatusCar();
    }

    private void showAlertChangeStatusCar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/statusCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("ค้นหารถยนต์");
            stage.setScene(new Scene(root, 370, 230));
            stage.setResizable(false);
            controller.setStage(stage);
            controller.getOk().setDisable(true);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }

    public void handleBtnSeeDetailClickAction (ActionEvent event){
        if (table.getSelectionModel().isEmpty()){
            selectAlert();
        }
        else{
            RequestTable t = table.getSelectionModel().getSelectedItem();
            seeDetail(this,"/menu/menuCar.fxml",t);
        }
    }
}
