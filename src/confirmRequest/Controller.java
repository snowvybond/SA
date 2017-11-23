package confirmRequest;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import menu.ControllerCivil;
import menu.ControllerFinance;


import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private Tab tab = new Tab();
    private boolean isSet;

    public void initialize(){
        tab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                scrollPane.setVvalue(0.0);
            }
        });
//        scrollPane.setVvalue(0.0);
    }

    public void fixScroll(){
        if(!isSet) {
            scrollPane.setVvalue(0.0);
            isSet = true;
        }
        System.out.println(scrollPane.getVvalue());
    }

    public void setStage(Stage stage){ this.stage = stage; }

    public void handleBtnOKClickAction(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setcConfirmRequest(this);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeader("ยืนยันคำอนุมัติการขอใช้รถ");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/menuCivil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ControllerCivil controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 885, 525));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
