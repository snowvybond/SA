package request;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import menu.ControllerFinance;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private Button ok , cancel;
    private Stage stage;

    @FXML
    public void handleBtnClickOKAction(){
        showComfirmRequest();
    }

    public void showComfirmRequest(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setcRequest(this);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeader("ยืนยันคำขอใช้รถ");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/menuFinance.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ControllerFinance controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 885, 525));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }
}
