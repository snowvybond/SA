package AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import menu.ControllerCar;
import model.DatabaseConnecter;
import view.RequestTable;

public class ReturnCarController extends Controller {

    @FXML private Label headerReturnCar;
    private RequestTable table;
    private ControllerCar controllerCar;
    public void setTable (RequestTable table){
        this.table = table;
    }
    public void setHeaderReturnCar(String liscence) {
        headerReturnCar.setText(headerReturnCar.getText()+liscence);
    }
    public void setControllerCar(ControllerCar controllerCar) {
        this.controllerCar = controllerCar;
    }

    @Override
    protected void ok() {
        String requestForCarId = table.getId();
        String liscensePlate = table.getLicensedPlate();
        String query = "update requestforcar set staus='คืนแล้ว' where id='"+requestForCarId+"'";
        DatabaseConnecter.updateString(query);
        int totalMission = Integer.parseInt(DatabaseConnecter.browserString("select totalmission from car where liscenseplate='"+liscensePlate+"'"));
        totalMission++;
        String query2 ="update car set totalmission='"+totalMission+"' where liscenseplate='"+table.getLicensedPlate()+"'";
        DatabaseConnecter.updateString(query2);
        int totalDistance = Integer.parseInt(DatabaseConnecter.browserString("select totaldistance from car where liscenseplate='"+liscensePlate+"'"));
        totalDistance+=Integer.parseInt(table.getDistance());
        String query3 ="update car set totaldistance='"+totalDistance+"' where liscenseplate='"+table.getLicensedPlate()+"'";
        DatabaseConnecter.updateString(query3);
        String status ="";
        if (used.isSelected()){
            status = "ใช้งานได้";
        }
        else if (unused.isSelected()){
            status = "ไม่สามารถใช้งานได้";
        }
        String query4 = "update car set status='"+status+"' where liscenseplate='"+liscensePlate+"'";
        DatabaseConnecter.updateString(query4);
        controllerCar.search();
        close();
    }

}
