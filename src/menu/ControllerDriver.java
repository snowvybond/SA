package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ControllerDriver  extends Controller{
    @Override
    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar where id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where staus='รออนุมัติ' and id in(select requestforcarid from workassign where driverid='"+userID+"')" ;
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where staus='อนุมัติแล้ว' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where id='"+id.getText()+"' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where staus='คืนแล้ว' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where staus='ปฏิเสธคำขอ' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c8.isSelected()){ //date
            ArrayList<String> ids = DatabaseConnecter.browseRfcIDByDate(startDate.getValue(),endDate.getValue(),"where id in(select requestforcarid from workassign where driverid='"+userID+"')");
            if (!ids.isEmpty()){
                query = "select * from requestforcar where id='" + ids.get(0) + "'";
                for (int i = 1; i < ids.size(); i++) {
                    query += "or id='" + ids.get(i) + "'";
                }
            }

        }
        displayTable(query);
    }

    @FXML
    public void handleBtnSeeDetailClickAction (ActionEvent event){

        if (table.getSelectionModel().isEmpty()){
            selectAlert();
        }
        else {
            RequestTable t = table.getSelectionModel().getSelectedItem();
            seeDetail(this,"/menu/menuDriver.fxml",t);
        }
    }

}
