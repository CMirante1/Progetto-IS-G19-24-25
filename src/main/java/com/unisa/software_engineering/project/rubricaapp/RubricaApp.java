package com.unisa.software_engineering.project.rubricaapp;

/**
 *
 * @author andre
 */
import com.unisa.software_engineering.project.controller.MenuPrincipaleController;
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import com.unisa.software_engineering.project.model.FileManager;
import com.unisa.software_engineering.project.model.Rubrica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class RubricaApp extends Application {

    private Rubrica rubrica;  // La lista fisica dei contatti

    @Override
    public void start(Stage primaryStage)  {

        try {

            rubrica = FileManager.caricaRubrica();
        } catch (IOException | ClassNotFoundException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore nella lettura della rubrica!");
            alert.showAndWait();
        }

        MenuPrincipaleView mpView = new MenuPrincipaleView();

        Scene menuPrincipale = new Scene(mpView, 750, 600);

        MenuPrincipaleController mpController = new MenuPrincipaleController(rubrica, primaryStage, mpView, menuPrincipale);

        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(menuPrincipale);
        primaryStage.setOnCloseRequest(event -> chiudiApp(event, primaryStage));
        primaryStage.show();

    }

    private void chiudiApp(WindowEvent event, Stage primaryStage) {

        Alert conferma = new Alert(Alert.AlertType.CONFIRMATION);

        conferma.setContentText("Sicuro di voler chiudere l'applicazione?");

        Optional<ButtonType> scelta = conferma.showAndWait();

        if (scelta.isPresent() && scelta.get() == ButtonType.OK) {

            try {

                FileManager.salvaRubrica(rubrica);
            } catch (IOException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Errore nel salvataggio della rubrica");
                alert.showAndWait();
            }
            primaryStage.close();
            System.exit(0);
        } else {

            event.consume();
        }
    }

    // Metodo che avvia l'applicazione
    public static void main(String[] args) {
        launch(args);
    }
}
