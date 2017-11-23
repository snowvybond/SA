package AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;

    private request.Controller cRequest;
    private confirmRequest.Controller cConfirmRequest;

    @FXML
    private Label status;

    @FXML
    private Label header;

    @FXML
    private Button ok = new Button();

    @FXML
    private TextField input;

    @FXML
    private RadioButton used , unused;

    @FXML
    private final ToggleGroup group = new ToggleGroup();

    public void addStatusCar(){
        used.setUserData("ใช้งานได้");
        unused.setUserData("ไม่สามารถใช้งานได้");
        used.setToggleGroup(group);
        unused.setToggleGroup(group);
    }

    public void setcRequest(request.Controller cRequest) { this.cRequest = cRequest; }
    public void setcConfirmRequest(confirmRequest.Controller cConfirmRequest) { this.cConfirmRequest = cConfirmRequest; }

    public void setHeader(String header) { this.header.setText(header); }
    public Button getOk() { return ok; }

    public void setStage(Stage stage) { this.stage = stage; }

    public void handleBtnOKClickAction(){
        ok();
    }

    private void ok() {
        
        close();
    }

    public void handleBtnCancelClickAction(){
        close();
    }

    private void close() {
        if(cConfirmRequest != null) cConfirmRequest.handleBtnCancelClickAction();
        if (cRequest != null) cRequest.handleBtnCancelClickAction();

        stage.close();
    }

    public void handleBtnFoundClickAction(){
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
