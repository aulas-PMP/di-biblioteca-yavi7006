import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
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
    private VBox edicion;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button stopButton;

    @FXML
    private Label tituloArchivo;

    @FXML
    private MediaView viewMedia;

    private Stage stage;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void reanudarVideo(){
        viewMedia.getMediaPlayer().play();
    }

    public void pausarVideo(){
        viewMedia.getMediaPlayer().pause();
    }

    public void stopVideo(){
        viewMedia.getMediaPlayer().stop();
    }
}

