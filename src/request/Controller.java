package request;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerFinance;
import model.DatabaseConnecter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    @FXML
    private Button ok , cancel;
    private Stage stage;

    private String userID;
    private ArrayList<String> data = new ArrayList<>();

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private ChoiceBox cause;

    @FXML
    private TextField causeText;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private ChoiceBox destination;

    @FXML
    private TextArea detail;


    @FXML
    public void handleBtnClickOKAction(){
        showComfirmRequest();
    }

    public void showComfirmRequest(){
        if(checkData()) {
            confirmRequest();
        }else{
            showAlert();
        }
    }

    @FXML
    public void checkCause(){
//        System.out.println(cause.getSelectionModel().getSelectedItem());
        if(!cause.getSelectionModel().isEmpty() && cause.getSelectionModel().getSelectedItem().equals("อื่นๆ")) causeText.setDisable(false);
        else causeText.setDisable(true);
    }

    private boolean checkData(){
        System.out.println(startDate.getValue().getDayOfYear());
        System.out.println(endDate.getValue().getDayOfYear());
        if(id.getText().isEmpty()) return false;
        if(name.getText().isEmpty()) return false;
        if(cause.getValue() == null) return false;
        if(cause.getValue().equals("อื่นๆ") && causeText.getText().isEmpty()) return false;
        if(startDate.getValue() == null) return false;
        if(endDate.getValue() == null) return false;
        if(startDate.getValue().isBefore(LocalDate.now())) return false;
        if(endDate.getValue().isBefore(LocalDate.now())) return false;
        if(startDate.getValue().isAfter(endDate.getValue())) return false;
        if(endDate.getValue().getDayOfYear()>361) return false;
        if(destination.getValue() == null) return false;


        return true;
    }

    private void showAlert(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertWarning.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setHeaderWarning("กรอกข้อมูลผิดพลาด");
            stage.setTitle("Warning");
            stage.setScene(new Scene(root, 380, 100));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

//    private void showConfirm(){
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
//            Parent root = loader.load();
//            Stage stage = new Stage();
//            AlertMsg.Controller controller = loader.getController();
//            controller.setStage(stage);
//            controller.setcRequest(this);
//            this.createData();
//            controller.setData(data,1);
//            stage.setTitle("Confirmation");
//            stage.setScene(new Scene(root, 380, 130));
//            stage.setResizable(false);
//            controller.setHeaderConfirm("ยืนยันคำขอใช้รถ");
//            stage.show();
//
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//    }

    public void handleBtnCancelClickAction(){
        goToMenu();
    }

    private void goToMenu() {
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/menu/menuFinance.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            ControllerFinance controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("ตารางรายการขอใช้รถ");
            stage.setScene(new Scene(root, 875, 515));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUp (){
        String userName = DatabaseConnecter.browserString("select name from user where username='"+userID+"'");
        userName += " "+ DatabaseConnecter.browserString("select surname from user where username='"+userID+"'");
        String lastId = DatabaseConnecter.browserString("select max(id) from requestforcar");
        int idData = 1001;
        if (lastId != null) {
            idData = Integer.parseInt(lastId) + 1;
        }
        ArrayList<String> causeOfUse = DatabaseConnecter.browseStringInArray("select cause from causeofuse");
        ArrayList<String> provience = DatabaseConnecter.browseStringInArray("select provience from destination");
        id.setText(Integer.toString(idData));
        name.setText(userName);
        for (String i :causeOfUse){
            cause.getItems().add(i);
        }
        for (String i: provience){
            destination.getItems().add(i);
        }

    }
    private void createData(){
        data.add(startDate.getValue().toString());
        data.add(endDate.getValue().toString());
        data.add(detail.getText());
        data.add(destination.getValue().toString());
        if (cause.getValue().equals("อื่นๆ")){
            data.add(causeText.getText());
        }else{
            data.add(cause.getValue().toString());
        }
        data.add(userID);
        data.add("รออนุมัติ");
    }

    private void confirmRequest(){
        this.createData();
        DatabaseConnecter.insertStringByArray(data ,"INSERT INTO requestforcar(startdate, enddate, detail, provience, causeofuse, user, staus) VALUES (?, ?, ?, ?, ?, ?, ?)");
        this.createNewRequest();
    }

    private void createNewRequest(){
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/request/request.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            request.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setUserID(userID);
            controller.setUp();
            stage.setTitle("รายละเอียดขอใช้รถ");
            stage.setScene(new Scene(root, 425, 740));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
