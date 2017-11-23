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
    public void handleBtnClickOKAction(ActionEvent event){
        showComfirmRequest();
    }

    public void showComfirmRequest(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        javafx.scene.control.Label text = new javafx.scene.control.Label();
        text.setFont(javafx.scene.text.Font.font(20));
        text.setText("ยืนยันคำขอใช้รถ (ไม่สามารถยกเลิกได้)");
        alert.getDialogPane().setContent(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            goToMenu();
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
