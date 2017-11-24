package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ControllerFinance extends Controller {

    private boolean isCreate = false;

    public void handleBtnCreateAction(){

        if(isCreate) { ShowAlertWarning(); }
        else createRequest();
        isCreate = !isCreate;
    }


    private void ShowAlertWarning(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        Label text = new Label();
        text.setFont(Font.font(20));
        text.setText("คุณมีรายการขอใช้รถแล้ว!!");
        alert.getDialogPane().setContent(text);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        }
    }

    private void createRequest(){
        try {
            getStage().close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/request/request.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            request.Controller controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Car system");
            stage.setScene(new Scene(root, 425, 660));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }

    @FXML
    public void handleBtnSearchClickAction(){ search(); }

    @Override
    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar where user='"+userID+"'";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='wait'";
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='approve'";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where user='"+userID+"'"+"and id='"+id.getText()+"'";
        }
        else if (c5.isSelected()){ //working
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='working'";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='returned'";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='reject'";
        }
        else if (c8.isSelected()){ //date
            query = "select * from requestforcar where user='"+userID+"'"+"and staus='wait'";
        }
        displayTable(query);
    }

}
