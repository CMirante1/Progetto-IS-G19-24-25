package com.unisa.software_engineering.project.RubricaApp;


import com.unisa.software_engineering.project.Model.FileManager;
import com.unisa.software_engineering.project.Model.Rubrica;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author andre
 */
public class RubricaApp extends Application {
    
    @Override
    public void start(Stage stage) {
       
       
        stage.setTitle("Rubrica");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
