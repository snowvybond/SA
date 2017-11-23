package see;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerCivil;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField causeText;

    @FXML
    private TextField startDate;

    @FXML
    private TextField endDate;

    @FXML
    private TextField destination;

    @FXML
    private TextField distance;

    @FXML
    private TextField priceGas;

    @FXML
    private TextField idDriver;

    @FXML
    private TextField nameDriver;

    @FXML
    private TextField liscensePlate;

    @FXML
    private TextField typeCar;

    @FXML
    private TextField brandCar;

    @FXML
    private TextField genCar;

    @FXML
    private TextArea detail;

    @FXML
    private ScrollPane scrollPane;
    private Stage stage;
    private menu.Controller controller;
    private String location;
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
    }

    public void setStage(Stage stage){ this.stage = stage; }
    public void setController(menu.Controller controller) { this.controller = controller; }
    public void setLocation(String location) { this.location = location; }

    public void handleBtnOKClickAction(){
        stage.close();
    }

    public void handleBtnCancelClickAction(){
        goToMenu();
    }

    private void goToMenu() {
        stage.close();
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
            Parent root = loader.load();
            Stage stage = new Stage();
            controller = loader.getController();
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
