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

public class ControllerCar extends Controller{

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


    public void handleBtnReturnAction(){
        showAlertReturn();
    }


    private void showAlertReturn(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/returnCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
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
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 375, 235));
            stage.setResizable(false);
            controller.setStage(stage);
            controller.getOk().setDisable(true);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void handleBtnSeeDetailClickAction(){
        seeDetail(this,"/menu/menuCar.fxml");
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
}
