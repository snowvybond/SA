package AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import menu.ControllerCar;
import model.DatabaseConnecter;
import view.RequestTable;

public class ReturnCarController extends Controller {

    private RequestTable table;
    private ControllerCar controllerCar;
    public void setTable (RequestTable table){
        this.table = table;
    }
    public void setControllerCar(ControllerCar controllerCar) {
        this.controllerCar = controllerCar;
    }

    @Override
    protected void ok() {
        String requestForCarId = table.getId();
        String query = "update requestforcar set staus='returned' where id='"+requestForCarId+"'";
        DatabaseConnecter.updateString(query);
        String liscensePlate = table.getLicensedPlate();
        String status ="";
        if (used.isSelected()){
            status = "ใช้งานได้";
        }
        else if (unused.isSelected()){
            status = "ไม่สามารถใช้งานได้";
        }
        String query2 = "update car set status='"+status+"' where liscenseplate='"+liscensePlate+"'";
        DatabaseConnecter.updateString(query2);
        controllerCar.search();
        close();
    }

}
