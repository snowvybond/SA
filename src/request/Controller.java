package request;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.awt.*;
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
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }
}
