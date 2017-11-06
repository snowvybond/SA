package logIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    Stage stage;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    public void handleClickClear(){
        username.clear();
        password.clear();
    }

    public void handleClickDateBox(){

    }

    public void handleClickOK(ActionEvent event){
        Button button = (Button) event.getSource();
        this.stage = (Stage) button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/requestToUseCar/request.fxml"));

        try {
            this.stage.setScene(new Scene(loader.load(), 325, 740));
            this.stage.setResizable(false);
            this.stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
