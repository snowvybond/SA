package logIn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Parent root = loader.load();
        Controller c = loader.getController();
        c.setStage(primaryStage);
        primaryStage.setTitle("Log in");
        primaryStage.setScene(new Scene(root, 290, 290));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

//    @Override
//    public void stop(){
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation Dialog");
//        alert.setHeaderText("Look, a Confirmation Dialog");
//        alert.setContentText("Are you ok with this?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            // ... user chose OK
//            System.exit(1);
//        } else {
//            // ... user chose CANCEL or closed the dialog
//        }
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
