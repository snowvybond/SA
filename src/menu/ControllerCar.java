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
            controller.setControllerCar(this);
            controller.setTable(table.getSelectionModel().getSelectedItem());
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 550, 150));
            stage.setResizable(false);
            controller.setStage(stage);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
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
            stage.setTitle("ตารางรายการขอใช้รถ");
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
