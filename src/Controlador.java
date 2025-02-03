import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Controlador {

    private Stage stage;

    @FXML
    private MenuItem abrirArchivo;

    @FXML
    private MenuBar barraMenu;

    @FXML
    private VBox biblioteca;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ComboBox<String> boxVelocidad;

    @FXML
    private VBox centerBox;

    @FXML
    private VBox edicion;

    @FXML
    private VBox mediaBox;

    @FXML
    private MenuItem mostrarLat;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Slider sliderVolumen;

    @FXML
    private Button stopButton;

    @FXML
    private Label tituloArchivo;

    @FXML
    private MediaView viewMedia;

    public void setStage(Stage stage){
        this.stage = stage;
        this.boxVelocidad.getItems().addAll("1x","1.25x","1.5x","2x");
        
    }

    @FXML
    void mostrarLaterales(ActionEvent event) {
        if(edicion.isVisible()){
            edicion.setVisible(false);
            biblioteca.setVisible(false);
        } else{
            edicion.setVisible(true);
            biblioteca.setVisible(true);
        }
    }

    @FXML
    void pausarVideo(ActionEvent event) {
        viewMedia.getMediaPlayer().pause();
    }

    @FXML
    void reanudarVideo(ActionEvent event) {
        viewMedia.getMediaPlayer().play();
    }

    @FXML
    void stopVideo(ActionEvent event) {
        viewMedia.getMediaPlayer().stop();
    }

    @FXML
    void cambiarVelocidad(ActionEvent event) {

    }
}

