package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;

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

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeaderConfirm("ยืนยันคำปฎิเสธการขอใช้รถรหัส XXXXX");
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
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
            stage.setScene(new Scene(root, 940, 600));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnSeeDetailClickAction(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/see/Detail.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            see.Controller c = loader.getController();
            c.setStage(stage);
            c.setController(this);
            c.setLocation("/menu/menuCivil.fxml");
            stage.setTitle("Detail");
            stage.setScene(new Scene(root, 490, 590));
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
