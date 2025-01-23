import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Controlador {

    @FXML
    private MenuBar barraMenu;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

}

