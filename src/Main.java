import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("biblioMulti.fxml"));
        Parent root = loader.load();
        Controlador controlador = loader.getController();
        controlador.setStage(primaryStage);
        primaryStage.setTitle("Biblioteca");
        primaryStage.setScene(new Scene(root,600,600));
        controlador.anadirComboBox();
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}