package code.eutrustservicesapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.IOException;
/**
 * Classe che inizializza gli elementi grafici e lancia il programma, contiene il
 * metodo main
 */
public class EUTrustServicesApplication extends Application {
    /**
     * Inizializza elementi grafici
     * @param stage schermata dell'applicazione
     * @throws IOException possibili eccezioni lanciate da fxmlLoader.load()
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EUTrustServicesApplication.class.getResource("MainScene.fxml")); //caricamento elementi schermata principale
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("sample/stile.css").toExternalForm());
        stage.setTitle("EU Trust Services Dashboard");
        stage.getIcons().add(new Image(getClass().getResource("Picture/icon.png").toExternalForm()));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    /**
     * Metodo Main
     * @param args  argomenti
     */
    public static void main(String[] args) {
        launch();
    }
}