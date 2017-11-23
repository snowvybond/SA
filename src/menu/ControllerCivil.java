package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import view.RequestTable;

import java.io.IOException;

public class ControllerCivil extends Controller{

    @FXML protected TableColumn<RequestTable , String> idColumn;
    @FXML protected TableColumn<RequestTable , String> nameColumn;
    @FXML protected TableColumn<RequestTable , String> causeTextColumn;
    @FXML protected TableColumn<RequestTable , String> startDateColumn;
    @FXML protected TableColumn<RequestTable , String> endDateColumn;
    @FXML protected TableColumn<RequestTable , String> destinationColumn;
    @FXML protected TableColumn<RequestTable , String> distanceColumn;
    @FXML protected TableColumn<RequestTable , String> priceGasColumn;
    @FXML protected TableColumn<RequestTable , String> idDriverColumn;
    @FXML protected TableColumn<RequestTable , String> nameDriverColumn;
    @FXML protected TableColumn<RequestTable , String> licensedPlateColumn;
    @FXML protected TableColumn<RequestTable , String> typeCarColumn;
    @FXML protected TableColumn<RequestTable , String> brandCarColumn;
    @FXML protected TableColumn<RequestTable , String> modelCarColumn;
    @FXML protected TableColumn<RequestTable , String> detailColumn;
    @FXML protected TableView<RequestTable> table;

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
