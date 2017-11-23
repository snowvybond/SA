package returnCar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ControllerCar;

import java.io.IOException;

public class Controller {
    private Stage stage;

    @FXML
    private Label status;

    @FXML
    private Button ok = new Button();

    @FXML
    private TextField input;

    @FXML
    private RadioButton used , unused;

    @FXML
    private final ToggleGroup group = new ToggleGroup();

    public void initialize(){
        ok.setDisable(true);
    }

    public void addStatusCar(){
        used.setUserData("ใช้งานได้");
        unused.setUserData("ไม่สามารถใช้งานได้");
        used.setToggleGroup(group);
        unused.setToggleGroup(group);
    }

    public void setStage(Stage stage) { this.stage = stage; }

    public void handleBtnOKClickAction(){
        update();
    }

    private void update() {
        //do somthing
        close();
    }

    public void handleBtnCancelClickAction(){
        close();
    }

    private void close() {
        stage.close();
    }

    public void handleBtnFoudClickAction(){
        found();
    }

    private void found() {
        status.setText("พบ");

        if(status.getText().equals("พบ")) status.setTextFill(Color.GREEN);
    }

    public void checkInput(){
        if(input.getText().isEmpty()) ok.setDisable(true);
        else ok.setDisable(false);
    }


}
