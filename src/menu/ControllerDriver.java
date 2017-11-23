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

    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> idColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> nameColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> causeTextColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> startDateColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> endDateColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> destinationColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> distanceColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> priceGasColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> idDriverColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> nameDriverColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> licensedPlateColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> typeCarColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> brandCarColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> modelCarColumn;
    @FXML protected javafx.scene.control.TableColumn<RequestTable , String> detailColumn;
    @FXML protected TableView<RequestTable> table;

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }

    public void handleBtnSeeDetailClickAction(){ seeDetail(this,"/menu/menuDriver.fxml"); }

    @FXML
    public void handleBtnSearchClickAction(){
        System.out.println("eiiiiiiiiiiiiiiiiiiiiiiiii");search(); }

    public void search(){

        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar where user='"+userID+"'";
        }
        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
        int count = 0;
        for (ArrayList<String> i : allData){
            for (String j:i){
                System.out.println(j);
            }

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
