package com.unisa.software_engineering.project.rubricaapp;

/**
 *
 * @author andre
 */
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import com.unisa.software_engineering.project.model.FileManager;
import com.unisa.software_engineering.project.model.Rubrica;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RubricaApp extends Application {

    private Rubrica rubrica;  // La lista fisica dei contatti

    @Override
    public void start(Stage primaryStage)  {

        FileManager.setStage(primaryStage);
        rubrica = FileManager.caricaRubrica();

        MenuPrincipaleView mpView = new MenuPrincipaleView();

        Scene menuPrincpale = new Scene(mpView, 750, 600);

//        MenuContattoView mcView = new MenuContattoView();
//
//        Scene mc = new Scene(mcView, 750, 600);

        primaryStage.setTitle("Rubrica");
        primaryStage.setScene(menuPrincpale);
        primaryStage.show();

    }

    // Metodo che avvia l'applicazione
    public static void main(String[] args) {
        launch(args);
    }
}
