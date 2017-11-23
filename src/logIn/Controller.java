package logIn;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerCar;
import menu.ControllerCivil;
import menu.ControllerDriver;
import menu.ControllerFinance;

import java.io.IOException;

public class Controller {

    @FXML
    Stage stage;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnOK;

    @FXML
    public void handleClickClear(){
        username.clear();
        password.clear();
    }

    @FXML
    public void initialize(){

        btnOK.setDisable(true);
        username.textProperty().addListener((ObservableValue <? extends String> observable, String oldValue, String newValue) -> {
            btnOK.setDisable(newValue.trim().isEmpty());
        });
    }

    public void setStage(Stage stage) { this.stage = stage; }
    public void handleBtnOKClickAction(){ check(); }

    private void check(){
        switch (username.getText()){
            case "1" :
                showMenu("/menu/menuFinance.fxml" , new ControllerFinance());
                break;
            case "2" :
                showMenu("/menu/menuCivil.fxml" , new ControllerCivil());
                break;
            case "3" :
                showMenu("/menu/menuDriver.fxml" , new ControllerDriver());
                break;
            case "4" :
                showMenu("/menu/menuCar.fxml" , new ControllerCar());
                break;
            default:
                username.clear();
                password.clear();
                username.setPromptText("Wrong Username");
                password.setPromptText("Wrong Password");
        }
    }

    private void showMenu(String location , menu.Controller controller) {
        System.out.println(username.getText() + "  " + password.getText());
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent root = loader.load();
            Stage stage = new Stage();
            controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}