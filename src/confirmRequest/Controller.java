package confirmRequest;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.util.Optional;

public class Controller {

    @FXML
    private ScrollPane scrollPane;
    private Stage stage;

    public void initialize(){
        scrollPane.setVvalue(0);
    }

    public void ss(){

        System.out.println(scrollPane.getVvalue());
    }

    public void setStage(Stage stage){ this.stage = stage; }

    public void handleBtnOKClickAction(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        Label text = new Label();
        text.setFont(Font.font(20));
        text.setText("ยืนยันคำอนุมัติการขอใช้รถ (ไม่สามารถยกเลิกได้!!)");
        alert.getDialogPane().setContent(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        }
    }
}
