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
import javafx.scene.Parent;

public class RubricaApp extends Application {

    private Rubrica rubrica;  // La lista fisica dei contatti

    @Override
    public void start(Stage stage) throws Exception {

        FileManager.set
        rubrica = FileManager.caricaRubrica();

        // 2. Crea il loader per il menu principale
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipale.fxml"));
        Parent root = loader.load();

        // 3. Ottieni il controller del menu principale
        ControllerMenuPrincipale controller = loader.getController();

        // 4. Passa la rubrica (come ObservableList) al controller
        controller.setRubrica(rubrica);

        // 5. Crea la scena e mostra la finestra principale
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Rubrica");
        stage.show();
    }

    // Metodo che avvia l'applicazione
    public static void main(String[] args) {
        launch(args);
    }
}
