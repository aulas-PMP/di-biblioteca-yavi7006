import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Controlador {

    @FXML
    private MenuBar barraMenu;

    @FXML
    private VBox biblioteca;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox centro;

    @FXML
    private VBox edicion;

    @FXML
    private Label tituloArchivo;

    @FXML
    private MediaView viewMedia;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

}

