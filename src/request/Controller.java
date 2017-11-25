package request;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.ControllerFinance;
import java.io.IOException;

public class Controller {

    @FXML
    private Button ok , cancel;
    private Stage stage;

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
            showConfirm();
        }else{
            showAlert();
        }
    }

    private boolean checkData(){
        if(id.getText().isEmpty()) return false;
        if(name.getText().isEmpty()) return false;
        if(cause.getValue() == null) return false;
        if(cause.getValue().equals("อื่นๆ") && causeText.getText().isEmpty()) return false;
        if(startDate.getValue().toString().isEmpty()) return false;
        if(endDate.getValue().toString().isEmpty()) return false;

        String[] start = startDate.getValue().toString().split("-");
        String[] end = endDate.getValue().toString().split("-");
        int checkDate =(Integer.parseInt(end[0])+Integer.parseInt(end[1])*10+Integer.parseInt(end[2])) - (Integer.parseInt(start[0])+Integer.parseInt(start[1])*10+Integer.parseInt(start[2]));

        if(checkDate<0) return false;
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
            controller.setHeaderWarning("กรอกข้อมูลไม่ครบ");
            stage.setTitle("Warning");
            stage.setScene(new Scene(root, 380, 100));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void showConfirm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AlertMsg/AlertConfirm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            AlertMsg.Controller controller = loader.getController();
            controller.setStage(stage);
            controller.setcRequest(this);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root, 380, 130));
            stage.setResizable(false);
            controller.setHeaderConfirm("ยืนยันคำขอใช้รถ");
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

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
            stage.setScene(new Scene(root, 885, 525));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setStage(Stage stage){ this.stage = stage; }
}
