package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class ControllerCivil extends Controller{

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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        Label text = new Label();
        text.setFont(Font.font(20));
        text.setText("ยืนยันคำปฎิเสธการขอใช้รถ (ไม่สามารถยกเลิกได้!!)");
        alert.getDialogPane().setContent(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
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
            stage.setScene(new Scene(root, 600, 420));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
}
