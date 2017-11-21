package menu;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import javax.swing.table.TableColumn;
import java.util.Optional;

public class ControllerDriver  extends Controller{
    @FXML
    public void handleBtnSignOutClickAction(){
        signOut();
    }

}
