package com.unisa.software_engineering.project.rubricaapp;

/**
 * @file RubricaApp.java
 * @brief Classe principale per l'avvio dell'applicazione della rubrica.
 */


import com.unisa.software_engineering.project.controller.MenuPrincipaleController;
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import com.unisa.software_engineering.project.model.FileManager;
import com.unisa.software_engineering.project.model.Rubrica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

/**
 * @class RubricaApp
 * @brief Classe principale che estende `Application` per l'avvio e gestione della GUI dell'applicazione.
 */

public class RubricaApp extends Application {

    private Rubrica rubrica;
     /**
     * @brief Metodo principale di avvio dell'applicazione.
     * @param primaryStage Lo stage principale dell'applicazione.
     */
    @Override
    public void start(Stage primaryStage)  {

        try {

            rubrica = FileManager.caricaRubrica();
        } catch (IOException | ClassNotFoundException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Apertura rubrica");
            alert.setContentText("Errore nella lettura della rubrica!");
            alert.showAndWait();

            primaryStage.close();
            System.exit(1);
        }

        MenuPrincipaleView mpView = new MenuPrincipaleView();

        Scene menuPrincipale = new Scene(mpView, 750, 550);

        MenuPrincipaleController mpController = new MenuPrincipaleController(rubrica, primaryStage, mpView, menuPrincipale);

        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(menuPrincipale);
        primaryStage.setOnCloseRequest(event -> chiudiApp(event, primaryStage));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("IconaRubrica.png"));
        primaryStage.show();

    }

    private void chiudiApp(WindowEvent event, Stage primaryStage) {

        Alert conferma = new Alert(Alert.AlertType.CONFIRMATION);
        conferma.setTitle("Conferma chiusura.");
        conferma.setContentText("Sicuro di voler chiudere l'applicazione?");

        Optional<ButtonType> scelta = conferma.showAndWait();

        if(scelta.isPresent() && scelta.get() == ButtonType.OK) {

            try {

                FileManager.salvaRubrica(rubrica);
            } catch (IOException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Salvataggio rubrica.");
                alert.setContentText("Errore nel salvataggio della rubrica");
                alert.showAndWait();
            }
            primaryStage.close();
            System.exit(0);
        }
        else event.consume();
    }

     /**
     * @brief Metodo principale per l'avvio dell'applicazione.
     * @param args Argomenti della linea di comando (non utilizzati).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
