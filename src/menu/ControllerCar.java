package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;
import java.io.IOException;
import java.util.Optional;

public class ControllerCar extends Controller{

    public void handleBtnReturnAction(){
        ShowAlertReturn();
    }

    private void ShowAlertReturn(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        Label text = new Label();
        text.setFont(Font.font(20));
        text.setText("ยืนยันการคืนรถ (ไม่สามารถยกเลิกได้!!)");
        alert.getDialogPane().setContent(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        }
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
}
