package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DatabaseConnecter;
import view.RequestTable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {

    protected Stage stage;

    protected static String userID;

    @FXML
    protected Button btnSeeDetail;

    @FXML
    protected TextField id;

    @FXML
    protected Label startDay , endDay;

    @FXML
    protected DatePicker startDate , endDate;

    @FXML
    protected final ToggleGroup group = new ToggleGroup();

    @FXML
    protected RadioButton c1 , c2 , c3 , c4 , c6 , c7 , c8;

    @FXML protected TableColumn<RequestTable , String> idColumn;
    @FXML protected TableColumn<RequestTable , String> causeTextColumn;
    @FXML protected TableColumn<RequestTable , String> statusColumn;
    @FXML protected TableColumn<RequestTable , String> startDateColumn;
    @FXML protected TableColumn<RequestTable , String> endDateColumn;
    @FXML protected TableColumn<RequestTable , String> destinationColumn;
    @FXML protected TableColumn<RequestTable , String> distanceColumn;
    @FXML protected TableColumn<RequestTable , String> priceGasColumn;
    @FXML protected TableColumn<RequestTable , String> idDriverColumn;
    @FXML protected TableColumn<RequestTable , String> nameDriverColumn;
    @FXML protected TableColumn<RequestTable , String> licensedPlateColumn;
    @FXML protected TableColumn<RequestTable , String> typeCarColumn;
    @FXML protected TableColumn<RequestTable , String> brandCarColumn;
    @FXML protected TableColumn<RequestTable , String> modelCarColumn;
    @FXML protected TableColumn<RequestTable , String> detailColumn;
    @FXML protected TableView<RequestTable> table;

    private boolean isChoice;

    @FXML
    public void initialize(){
        addGroup();
        radioAction();
        search();

        isChoice = false;
    }

    public void setUserID(String u){
        userID = u;
        System.out.println("setuserid");
    }
    protected void signOut(){
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/logIn/logIn.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            logIn.Controller c = loader.getController();
            c.setStage(stage);
            stage.setTitle("Log in");
            stage.setScene(new Scene(root, 290, 290));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @FXML
    public void handleRadioClickAction(ActionEvent event){
        System.out.println("click");
        radioAction();
    }

    public void seeDetail(Controller controller ,String location ,RequestTable table){
        try {
            this.stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/see/Detail2.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            see.Controller c = loader.getController();
            c.setStage(stage);
            c.setController(controller);
            c.setLocation(location);
            c.setData(table);
            c.setController(this);
            stage.setTitle("Detail");
            stage.setScene(new Scene(root, 490, 590));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void radioAction(){
        System.out.println(group.getSelectedToggle().getUserData().toString());
        switch (group.getSelectedToggle().getUserData().toString()){
            case "id":
                chooseIdRadioButton();
                break;
            case "date":
                chooseDateRadioButton();
                break;
            default:
                chooseFilter();
        }
    }

    private void chooseFilter(){
        id.setDisable(true);
        startDay.setDisable(true);
        startDate.setDisable(true);
        endDay.setDisable(true);
        endDate.setDisable(true);
    }

    private void chooseIdRadioButton(){
        id.setDisable(false);
        startDay.setDisable(true);
        startDate.setDisable(true);
        endDay.setDisable(true);
        endDate.setDisable(true);
    }

    private void chooseDateRadioButton(){
        id.setDisable(true);
        startDay.setDisable(false);
        startDate.setDisable(false);
        endDay.setDisable(false);
        endDate.setDisable(false);
    }

    private void addGroup(){
        c1.setUserData("all");
        c2.setUserData("wait");
        c3.setUserData("yes");
        c4.setUserData("id");
        c6.setUserData("return");
        c7.setUserData("reject");
        c8.setUserData("date");
        c1.setToggleGroup(group);
        c2.setToggleGroup(group);
        c3.setToggleGroup(group);
        c4.setToggleGroup(group);
        c6.setToggleGroup(group);
        c7.setToggleGroup(group);
        c8.setToggleGroup(group);
    }

    @FXML
    public void handleBtnSearchClickAction(){ search(); }

    public void search(){
        table.getItems().clear();
        String query = "";
        if (c1.isSelected()){  //all
            query = "select * from requestforcar";
        }
        else if (c2.isSelected()){ //wait
            query = "select * from requestforcar where staus='รออนุมัติ'";
        }
        else if (c3.isSelected()){ //approve
            query = "select * from requestforcar where staus='อนุมัติแล้ว'";
        }
        else if (c4.isSelected()){ //id
            query = "select * from requestforcar where id='"+id.getText()+"'";
        }
        else if (c6.isSelected()){ //returned
            query = "select * from requestforcar where staus='คืนแล้ว'";
        }
        else if (c7.isSelected()){ //reject
            query = "select * from requestforcar where staus='ปฏิเสธคำขอ'";
        }
        else if (c8.isSelected()){ //date
            ArrayList<String> ids = DatabaseConnecter.browseRfcIDByDate(startDate.getValue(),endDate.getValue(),"");
            if (!ids.isEmpty()){
                query = "select * from requestforcar where id='" + ids.get(0) + "'";
                for (int i = 1; i < ids.size(); i++) {
                    query += "or id='" + ids.get(i) + "'";
                }
            }
        }
        displayTable(query);
    }

    protected void displayTable(String query){
        ArrayList<ArrayList> allData = DatabaseConnecter.browseRequestForCar(query);
        Collections.reverse(allData);
        int count = 0;
        for (ArrayList<String> i : allData){
            table.getItems().add(count++,new RequestTable(i.get(0),i.get(1),i.get(2),i.get(3),i.get(4),i.get(5),i.get(6),i.get(7),i.get(8),i.get(9),i.get(10),i.get(11),i.get(12),i.get(13),i.get(14),i.get(15)));
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            causeTextColumn.setCellValueFactory(new PropertyValueFactory<>("causeText"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
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

    public void checkChoice(){
        if(table.getSelectionModel().getSelectedIndex() >= 0) btnSeeDetail.setDisable(false);
        else btnSeeDetail.setDisable(true);
    }

//    public void handleBtnSeeDetailClickAction (ActionEvent event){
//
//
//    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public Stage getStage() { return stage; }

    protected void selectAlert(){
        System.out.println("เลือกด้วยโว้ย");
    }

    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }
}
