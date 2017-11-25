package AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.ControllerCar;
import model.DatabaseConnecter;

import java.util.ArrayList;

public class Controller {
    private Stage stage;

    private request.Controller cRequest;
    private confirmRequest.Controller cConfirmRequest;

    private int check;
    private ArrayList<String> data;



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
    protected menu.Controller controller;


    public void addStatusCar(){
        used.setUserData("ใช้งานได้");
        unused.setUserData("ไม่สามารถใช้งานได้");
        used.setToggleGroup(group);
        unused.setToggleGroup(group);
    }

    public void setController(menu.Controller controller) {
        this.controller = controller;
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
        if (check == 1){
            DatabaseConnecter.insertStringByArray(data ,"INSERT INTO requestforcar(startdate, enddate, detail, provience, causeofuse, user, staus) VALUES (?, ?, ?, ?, ?, ?, ?)");
        }
        else if(check == 2){
            String id = data.get(0);
            DatabaseConnecter.updateString("update requestforcar set staus='ปฏิเสธคำขอ' where id='"+id+"'");
            controller.search();
        }
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

    public void setData(ArrayList<String> data,int check){
        this.data = data;
        this.check = check;
    }




}
