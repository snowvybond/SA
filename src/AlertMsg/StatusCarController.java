package AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.DatabaseConnecter;

public class StatusCarController extends Controller{


    @FXML
    private Label status;
    @FXML
    private TextField input;

    private  String liscenseResult;

    public void handleBtnFoundClickAction(){
        searchCar();
    }

    private void searchCar(){
        String liscenseInput = input.getText();
        liscenseResult = DatabaseConnecter.browserString("select liscenseplate from car where liscenseplate='"+liscenseInput+"' and liscenseplate not in (select liscenseplate from workassign where requestforcarid in (select id from requestforcar where staus='working' or staus='approve' or staus='wait'))");
        if (liscenseInput.equals(liscenseResult)){
            found();
        }else {
            notFound();
        }
    }
    private void notFound(){
        status.setText("ไม่พบ");
        status.setTextFill(Color.RED);
        ok.setDisable(true);
    }

    private void found() {
        status.setText("พบ");
        status.setTextFill(Color.GREEN);
        ok.setDisable(false);
    }

    @Override
    protected void ok(){
        String status ="";
        if (used.isSelected()){
            status = "ใช้งานได้";
        }
        else if (unused.isSelected()){
            status = "ไม่สามารถใช้งานได้";
        }
        String query = "update car set status='"+status+"' where liscenseplate='"+liscenseResult+"'";
        DatabaseConnecter.updateString(query);
        close();


    }

}
