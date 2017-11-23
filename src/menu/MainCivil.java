package menu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCivil extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menuCivil.fxml"));
        Parent root = loader.load();
        ControllerCivil c = loader.getController();
        c.setStage(primaryStage);
        primaryStage.setTitle("Car system");
        primaryStage.setScene(new Scene(root, 875, 515));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}