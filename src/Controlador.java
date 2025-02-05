import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controlador {

    private Stage stage;

    @FXML
    private MenuItem abrirArchivo;

    @FXML
    private Menu acercaDe;

    @FXML
    private MenuItem cerrar;

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
    private Button muteButton;

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
        FileChooser fc = new FileChooser();
        fc.setTitle("Abrir archivo");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Video","*.mp4","*.mov","*.avi","*.webm"),new ExtensionFilter("Audio", "*.mp3","*.wav"));
        File arch = fc.showOpenDialog(stage);
        if(arch!=null){
            if(arch.getName().contains(".mp4") || arch.getName().contains(".mov") || arch.getName().contains(".avi") || arch.getName().contains(".webm")){
                setVideo(arch);
            } else{
                setAudio(arch);
            }
        }
    }

    @FXML
    void cerrarArchivo(ActionEvent event) {
        mp = new MediaPlayer(null);
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
        String velocidad = this.boxVelocidad.getSelectionModel().getSelectedItem();
        if(mp!=null){
            mp.setRate(Integer.valueOf(velocidad.replace("x", "")));
        }
    }

    @FXML
    void cambiarTamano(ActionEvent event) {
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

    @FXML
    void mutear(ActionEvent event) {
        if(mp!=null){
            if(mp.isMute()){
                mp.setMute(false);
            } else{
                mp.setMute(true);
            }
        }
    }

    public void setVideo(File video){
        mp = new MediaPlayer(new Media(video.getPath()));
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
        tituloArchivo.setText(video.getName());
        progressBar.setProgress(0);
    }

    public void setAudio(File audio){
        mp = new MediaPlayer(new Media(audio.getPath()));
        viewMedia = new MediaView(mp);

        tituloArchivo.setText(audio.getName());
        progressBar.setProgress(0);
    }
    

    public void libreria(File directorio){
        File[] videos = directorio.listFiles();
        if(videos!=null){
            for(File video : videos){
                if(video.isDirectory()){
                    libreria(video);
                } else{
                    Button bt = new Button();
                    Media med = new Media(video.toURI().getPath());
                    double duracion = med.getDuration().toSeconds();
                    int horas = (int) duracion/3600;
                    duracion -= horas*3600;
                    int minutos = (int) duracion/60;
                    duracion -= minutos*60;
                    int segundos = (int) duracion;
                    if(horas>0){
                        bt.setText(video.getName()+" "+horas+":"+minutos+":"+segundos);
                    } else{
                        bt.setText(video.getName()+" "+minutos+":"+segundos);
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

    public void anadirComboBox(){
        if(boxVelocidad.getItems().isEmpty()){
            boxVelocidad.getItems().addAll("0.25x","0.5x","0.75x","1x","1.25x","1.5x","1.75x","2x");
        }
        if(boxTamano.getItems().isEmpty()){
            boxTamano.getItems().addAll("50%","75%","100%");
        }
        boxTamano.setEditable(false);
        boxVelocidad.setEditable(false);
    }
}