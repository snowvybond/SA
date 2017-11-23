package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    private Stage stage;

    @FXML
    private TextField id;

    @FXML
    private Label startDay , endDay;

    @FXML
    private DatePicker startDate , endDate;

    @FXML
    private final ToggleGroup group = new ToggleGroup();

    @FXML
    private RadioButton c1 , c2 , c3 , c4 , c5 , c6 , c7 , c8 , c9;

    @FXML
    public void initialize(){
        addGroup();
        radioAction();
    }

    protected void signOut(){
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/logIn/logIn.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            logIn.Controller c = loader.getController();
            c.setStage(stage);
            stage.setTitle("Log in");
            stage.setScene(new Scene(root, 290, 290));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleRadioClickAction(){
        radioAction();
    }

    private void radioAction(){
//        System.out.println(group.getSelectedToggle().getUserData().toString());
        switch (group.getSelectedToggle().getUserData().toString()){
            case "id":
                chooseIdRadioButton();
                break;
            case "date":
                chooseDateRadioButton();
                break;
            default:
                chooseFilter();
        }
    }

    private void chooseFilter(){
        id.setDisable(true);
        startDay.setDisable(true);
        startDate.setDisable(true);
        endDay.setDisable(true);
        endDate.setDisable(true);
    }

    private void chooseIdRadioButton(){
        id.setDisable(false);
        startDay.setDisable(true);
        startDate.setDisable(true);
        endDay.setDisable(true);
        endDate.setDisable(true);
    }

    private void chooseDateRadioButton(){
        id.setDisable(true);
        startDay.setDisable(false);
        startDate.setDisable(false);
        endDay.setDisable(false);
        endDate.setDisable(false);
    }

    private void addGroup(){
        c1.setUserData("all");
        c2.setUserData("wait");
        c3.setUserData("yes");
        c4.setUserData("id");
        c5.setUserData("working");
        c6.setUserData("return");
        c7.setUserData("reject");
        c8.setUserData("date");
        c1.setToggleGroup(group);
        c2.setToggleGroup(group);
        c3.setToggleGroup(group);
        c4.setToggleGroup(group);
        c5.setToggleGroup(group);
        c6.setToggleGroup(group);
        c7.setToggleGroup(group);
        c8.setToggleGroup(group);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage() { return stage; }
}
