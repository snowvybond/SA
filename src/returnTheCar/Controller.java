package returnTheCar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private CheckBox idBox , dateBox;

    @FXML
    private TextField id;

    @FXML
    private Label startDay , endDay;

    @FXML
    private DatePicker startDate , endDate;

    @FXML
    public void initialize(){
        handleClickIdBox();
    }

    @FXML
    public void handleClickIdBox(){
        idBox.setSelected(true);
        dateBox.setSelected(false);
        id.setDisable(false);
        startDay.setDisable(true);
        startDate.setDisable(true);
        endDay.setDisable(true);
        endDate.setDisable(true);
    }

    public void handleClickDateBox(){
        dateBox.setSelected(true);
        idBox.setSelected(false);
        id.setDisable(true);
        startDay.setDisable(false);
        startDate.setDisable(false);
        endDay.setDisable(false);
        endDate.setDisable(false);
    }

}
