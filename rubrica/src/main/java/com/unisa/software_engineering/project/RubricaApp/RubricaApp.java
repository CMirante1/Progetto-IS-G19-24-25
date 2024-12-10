package com.unisa.software_engineering.project.RubricaApp;

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

        // Impostiamo la finestra principale
        FileManager.setStage(primaryStage);
        
        // Carichiamo la rubrica (presumibilmente da un file o da un database)
        rubrica = FileManager.caricaRubrica();

        // Carichiamo il file FXML per la scena
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/unisa/software_engineering/project/MenuPrincipale/MenuPrincipale.fxml"));

        // Creiamo la scena utilizzando il file FXML
        Scene scene = new Scene(fxmlLoader.load());

        // Otteniamo il controller del file FXML
        ControllerMenuPrincipale controller = fxmlLoader.getController();

        // Impostiamo il titolo della finestra
        primaryStage.setTitle("Rubrica");

        // Impostiamo la scena e mostriamo la finestra
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metodo che avvia l'applicazione
    public static void main(String[] args) {
        // Lanciamo l'applicazione JavaFX
        launch(args);
    }
}