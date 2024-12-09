package com.unisa.software_engineering.project.RubricaApp;

/**
 *
 * @author andre
 */
import com.unisa.software_engineering.project.MenuPrincipale.ControllerMenuPrincipale;
import com.unisa.software_engineering.project.Model.FileManager;
import com.unisa.software_engineering.project.Model.Rubrica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class RubricaApp extends Application {

    private Rubrica rubrica;  // La lista fisica dei contatti

    @Override
    public void start(Stage primaryStage) throws Exception {

        FileManager.setStage(primaryStage);
        rubrica = FileManager.caricaRubrica();

        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuPrincipale.class.getResource("MenuPrincipale.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        ControllerMenuPrincipale controller = fxmlLoader.getController();

        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metodo che avvia l'applicazione
    public static void main(String[] args) {
        launch(args);
    }
}
