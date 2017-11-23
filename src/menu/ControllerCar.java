package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCar extends Controller{


    public void handleBtnReturnAction(){
        showAlertReturn();
    }


    private void showAlertReturn(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/returnCar/returnCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            returnCar.Controller controller = loader.getController();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/returnCar/statusCar.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            returnCar.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 375, 235));
            stage.setResizable(false);
            controller.setStage(stage);
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
