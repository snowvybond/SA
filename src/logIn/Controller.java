package logIn;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import menu.ControllerCar;
import menu.ControllerCivil;
import menu.ControllerDriver;
import menu.ControllerFinance;
import model.DatabaseConnecter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

public class Controller {

    protected String userID;
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
        System.out.println("Date now: "+LocalDateTime.now().toLocalDate());
        System.out.println("Time now: "+LocalDateTime.now().toLocalTime());
    }

    public void setStage(Stage stage) { this.stage = stage; }
    public void handleBtnOKClickAction(){ check(); }

    private void check(){
        String usernameQuery = "select username from user where username='"+username.getText()+"' AND password='"+password.getText()+"'";
        String user = DatabaseConnecter.browserString(usernameQuery);
        String type = "0";
        if (username.getText().equals(user)) {
            type = user.substring(0, 1);
        }
//        switch (username.getText()){
        switch (type){
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
                alert();
        }
    }

    private void showMenu(String location , menu.Controller controller) {
        System.out.println(username.getText() + "  " + password.getText());
        try {

            System.out.println(LocalDateTime.now().toLocalDate());

            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent root = loader.load();
            Stage stage = new Stage();
            controller = loader.getController();
            controller.setStage(stage);
            controller.setUserID(username.getText());
            controller.initialize();
            stage.setTitle("ตารางรายการขอใช้รถ");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void alert(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertWarning.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Warning");
            stage.setScene(new Scene(root, 380, 100));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
