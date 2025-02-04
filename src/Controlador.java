import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileSystem;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private ComboBox<String> boxTamano;

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

    @FXML
    private ListView<Button> lista;

    private MediaPlayer mp;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void abrirArchivo(ActionEvent event) {
        
    }

    @FXML
    void mostrarLaterales(ActionEvent event) {
        if(mostrarLat.isSelected()){
            edicion.setVisible(true);
            biblioteca.setVisible(true);
            edicion.setPrefWidth(140);
            biblioteca.setPrefWidth(140);
        } else{
            edicion.setVisible(false);
            biblioteca.setVisible(false);
            edicion.setPrefWidth(0);
            biblioteca.setPrefWidth(0);
        }
    }

    @FXML
    void pausarVideo(ActionEvent event) {
        if(mp!=null){
            mp.pause();
        }
    }

    @FXML
    void reanudarVideo(ActionEvent event) {
        if(mp!=null){
            mp.play();
        }
    }

    @FXML
    void stopVideo(ActionEvent event) {
        if(mp!=null){
            mp.stop();
        }
    }

    @FXML
    void cambiarVelocidad(ActionEvent event) {
        if(boxVelocidad.getItems().isEmpty()){
            boxVelocidad.getItems().addAll("0.25x","0.5x","0.75x","1x","1.25x","1.5x","1.75x","2x");
        }
        String velocidad = this.boxVelocidad.getSelectionModel().getSelectedItem();
        if(mp!=null){
            mp.setRate(Integer.valueOf(velocidad.replace("x", "")));
        }
    }

    @FXML
    void cambiarTamano(ActionEvent event) {
        if(boxTamano.getItems().isEmpty()){
            boxTamano.getItems().addAll("50%","75%","100%");
        }
        String tamano = this.boxTamano.getSelectionModel().getSelectedItem();
        double refTam = Double.parseDouble(tamano.replace("%", ""))/100;
        viewMedia.setFitWidth(viewMedia.getFitWidth()*refTam);
        viewMedia.setFitHeight(viewMedia.getFitHeight()*refTam);
        viewMedia.setPreserveRatio(true);
        viewMedia.setSmooth(true);
    }

    @FXML
    void cambiarVolumen(MouseDragEvent event) {
        Double volumen = sliderVolumen.getValue();
        if(mp!=null){
            mp.setVolume(volumen);
        }
    }

    public void setVideo(File video){
        mp = new MediaPlayer(new Media(video.getAbsolutePath()));
        viewMedia = new MediaView(mp);
        if(mp.getMedia().getWidth()<=mediaBox.getMaxWidth()){
            viewMedia.setFitWidth(mp.getMedia().getWidth());
        } else{
            viewMedia.setFitWidth(mediaBox.getWidth());
        }
        if(mp.getMedia().getHeight()<=mediaBox.getMaxHeight()){
            viewMedia.setFitHeight(mp.getMedia().getHeight());
        } else{
            viewMedia.setFitHeight(mediaBox.getHeight());
        }
        viewMedia.setPreserveRatio(true);
        viewMedia.setSmooth(true);
        progressBar.progressProperty().bind(null);
        
    }

    public void libreria(File directorio){
        File[] videos = directorio.listFiles();

        for(File video : videos){
            if(video.isDirectory()){
                libreria(video);
            } else{
                Button bt = new Button();
                Media med = new Media(video.getName());
                double duracion = med.getDuration().toSeconds();
                int horas = (int) duracion/3600;
                duracion -= horas*3600;
                int minutos = (int) duracion/60;
                duracion -= minutos*60;
                int segundos = (int) duracion;
                if(horas>0){
                    bt.setText(video.getName()+" "+horas+":"+minutos+":"+segundos);
                } else{
                    bt.setText(video.getName()+" "+horas+":"+minutos+":"+segundos);
                }
                bt.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        setVideo(new File(bt.getText().substring(0, bt.getText().indexOf(" "))));
                    }
                    
                });
                lista.getItems().add(bt);
            }
        }
    }
}