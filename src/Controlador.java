import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseDragEvent;
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
    private CheckMenuItem mostrarLat;

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
        /*
        this.boxVelocidad.getItems().addAll("1x","1.25x","1.5x","2x");
        this.mostrarLat.setSelected(true);
        */
    }

    @FXML
    void mostrarLaterales(ActionEvent event) {
        if(mostrarLat.isSelected()){
            edicion.setVisible(true);
            biblioteca.setVisible(true);
        } else{
            edicion.setVisible(false);
            biblioteca.setVisible(false);
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
        String velocidad = this.boxVelocidad.getSelectionModel().getSelectedItem();
        viewMedia.getMediaPlayer().setRate(Integer.valueOf(velocidad.replace("x", "")));
    }

    @FXML
    void cambiarVolumen(MouseDragEvent event) {
        Double volumen = sliderVolumen.getValue();
        viewMedia.getMediaPlayer().setVolume(volumen);
    }
}

