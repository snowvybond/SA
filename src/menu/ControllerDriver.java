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
        System.out.println(query);
        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new RequestTable(i.get(0) , i.get(1) , i.get(2) , i.get(3) , i.get(4) , i.get(5) , i.get(6) , i.get(7) , i.get(8) , i.get(9) , i.get(10) , i.get(11) , i.get(12) , "gen" , "detail"));
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            causeTextColumn.setCellValueFactory(new PropertyValueFactory<>("causeText"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
            distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
            priceGasColumn.setCellValueFactory(new PropertyValueFactory<>("priceGas"));
            idDriverColumn.setCellValueFactory(new PropertyValueFactory<>("idDriver"));
            nameDriverColumn.setCellValueFactory(new PropertyValueFactory<>("nameDriver"));
            licensedPlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensedPlate"));
            typeCarColumn.setCellValueFactory(new PropertyValueFactory<>("typeCar"));
            brandCarColumn.setCellValueFactory(new PropertyValueFactory<>("brandCar"));
            modelCarColumn.setCellValueFactory(new PropertyValueFactory<>("genCar"));
            detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));
        }
    }

}
