package menu;

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
    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
    @Override
    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar where id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where staus='wait' and id in(select requestforcarid from workassign where driverid='"+userID+"')" ;
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where staus='approve' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where id='"+id.getText()+"' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c5.isSelected()){ //working
            query = "select * from requestforcar where staus='working' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where staus='returned' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where staus='reject' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        else if (c8.isSelected()){ //date
            query = "select * from requestforcar where staus='wait' and id in(select requestforcarid from workassign where driverid='"+userID+"')";
        }
        displayTable(query);
    }

}
