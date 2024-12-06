/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.unisa.software_engineering.project.MenuPrincipale;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * @class MenuPrincipaleController
 * @brief Gestisce il menu principale
 * @todo Definire initialize e come strutturare model-controller-view
 * @author andre
 * @ingroup Controllers
 * @date 05/12/24
 */
public class ControllerMenuPrincipale implements Initializable {

    @FXML
    private MenuItem esportaBtn;
    @FXML
    private MenuItem eliminaBtn;
    @FXML
    private TableColumn<?, ?> nomeCln;
    @FXML
    private TableColumn<?, ?> cognomeCln;
    @FXML
    private TextField barraRicerca;
    @FXML
    private Button aggiungiBtn;
    @FXML
    private Button importaBtn;

    /**
     * @brief Inizializza il controller
     * @ingroup Construzione
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void esportaContatto(ActionEvent event) {
    }

    @FXML
    private void eliminaContatto(ActionEvent event) {
    }

    @FXML
    private void aggiungiContatto(ActionEvent event) {
    }

    @FXML
    private void importaContatto(ActionEvent event) {
    }
    
}
