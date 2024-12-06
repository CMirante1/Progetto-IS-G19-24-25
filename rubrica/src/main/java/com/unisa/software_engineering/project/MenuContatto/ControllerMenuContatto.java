/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.MenuContatto;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @class ControllerMenuContatto
 * @brief Effettua controlli sulla GUI MenuContatto
 * 
 * Questa classe si occupa di gestire la GUI del menu che mostra il contatto durante la visualizzazione
 * e modifica dei contatti. Controlla anche se il contatto è valido prima di passarlo alle strutture dati.
 * 
 * @ingroup Controllers
 * @todo Definire initialize e come strutturare model-controller-view
 * @author paolo
 * @date 05/12/24
 */
public class ControllerMenuContatto implements Initializable {
    
    @FXML
    private TextField nomeTXF;
    @FXML
    private TextField cognomeTXF;
    @FXML
    private TextField[] numeriTF;
    @FXML
    private TextField[] emailsTF;
    @FXML
    private Label nomeLBL;
    @FXML
    private Label cognomeLBL;
    @FXML
    private Label cell1LBL;
    @FXML
    private Label cell2LBL;
    @FXML
    private Label cell3LBL;
    @FXML
    private Label email1LBL;
    @FXML
    private Label email2LBL;
    @FXML
    private Label email3LBL;
    @FXML
    private ImageView immagineProfilo;
    @FXML
    private Button modificaBtn;
    @FXML
    private Button salvaBtn;
    @FXML
    private Button escBtn;
    @FXML
    private Button immagineBtn;

    /**
     * @brief Verifica il nome inserito dall'utente
     * @param nome Il nome del contatto
     * @param cognome Il cognome del contatto
     * @return Se il contatto è valido
     * @ingroup Verifiche
     */
    private boolean verificaNome(String nome, String cognome){
    
    }
    
    /**
     * @brief Verifica i numeri di telefono inseriti dall'utente
     * @param numeri L'array di numeri di telefono
     * @return Se tutti i numeri sono validi
     * @ingroup Verifiche
     */
    private  boolean verificaNumeri(String[] numeri){
    
    }
    
    /**
     * @brief Verifica le email inserite dall'utente
     * @param email L'array di email
     * @return Se tutte le email sono valide
     * @ingroup Verifiche
     */
    private  boolean verificaEmail(String[] email){
    
    }
    
    /**
     * @brief Inizializza il controller
     * @ingroup Construzione
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void modificaContatto(ActionEvent event) {
    }

    @FXML
    private void verificaContatto(ActionEvent event) {
    }

    @FXML
    private void cambiaScena(ActionEvent event) {
    }

    @FXML
    private void aggiungiImmagine(ActionEvent event) {
    }
}
