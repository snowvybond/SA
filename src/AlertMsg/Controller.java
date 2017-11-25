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
    private Label headerConfirm;

    @FXML
    private Label  headerWarning;

    @FXML
    protected Button ok = new Button();

    @FXML
    protected RadioButton used , unused;

    @FXML
    protected final ToggleGroup group = new ToggleGroup();


    public void addStatusCar(){
        used.setUserData("ใช้งานได้");
        unused.setUserData("ไม่สามารถใช้งานได้");
        used.setToggleGroup(group);
        unused.setToggleGroup(group);
    }





    public void setcRequest(request.Controller cRequest) { this.cRequest = cRequest; }
    public void setcConfirmRequest(confirmRequest.Controller cConfirmRequest) { this.cConfirmRequest = cConfirmRequest; }
    public void setHeaderWarning(String headerWarning) { this.headerWarning.setText(headerWarning); }
    public void setHeaderConfirm(String headerConfirm) { this.headerConfirm.setText(headerConfirm); }


    public Button getOk() { return ok; }

    public void setStage(Stage stage) { this.stage = stage; }

    public void handleBtnOKClickAction(){
        ok();
    }
    protected void ok(){
        close();
    }

    public void handleBtnCancelClickAction(){
        close();
    }

    protected void close() {
        if(cConfirmRequest != null) cConfirmRequest.handleBtnCancelClickAction();
        if (cRequest != null) cRequest.handleBtnCancelClickAction();

        stage.close();
    }




}
