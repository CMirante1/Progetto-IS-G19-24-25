/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unisa.software_engineering.project.MenuContatto;

/**
 * @class ControllerMenuContatto
 * @brief Gestisce la finestra di visualizzazione di un contatto e le operazioni effettuabili al suo interno dall'utente
 *
 * Quando l'utente seleziona un contatto dal menu principale di cui visualizzare i dati viene mostrata la finestra con le informazioni presenti in rubrica.
 *
 * @ingroup MenuContatto
 * @author grouppo_19
 * @date 06/12/24
 */

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;
import com.unisa.software_engineering.project.MenuPrincipale.ControllerMenuPrincipale;
import com.unisa.software_engineering.project.Model.Contatto;
import com.unisa.software_engineering.project.Model.Rubrica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class ControllerMenuContatto implements Initializable{

    @FXML
    private TextField nomeTXF;
    @FXML
    private TextField cognomeTXF;
    @FXML
    private ImageView immagineProfilo;
    @FXML
    private Button modificaBtn;
    @FXML
    private Button salvaBtn;
    @FXML
    private Button escBtn;
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
    private Button immagineBtn;

    private Rubrica rubrica;
    private TextField[] numeriTF;
    private TextField[] emailsTF;
    private Contatto contattoSelezionato;
    private boolean contattoAggiunto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i = 0; i < numeriTF.length; i++) numeriTF[i] = new TextField();
        for(int i = 0; i < emailsTF.length; i++) emailsTF[i] = new TextField();
    }

    /**
 *
 * @brief metodo per settare la rubrica all'interno del controller del menu contatto
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto viene passato al controller il riferimento della rubrica da cui proviene il contatto
 * @param rubrica Rappresenta la struttura dati da cui proviene il contatto
 */

    public void setRubrica(Rubrica rubrica) {

        this.rubrica = rubrica;
    }

 /**
 * @brief metodo per settare il contatto da visualizzare all'interno del controller del menu contatto
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto viene passato al controller il riferimento del contatto da cui vengono prelevati gli attributi e visualizzati nei textField della finestra di visualizzazione
 * @param contatto
 *
 */

    public void setContatto(Contatto contatto) {
        this.contattoSelezionato = contatto;

        if (contatto == null) {
            // Modalità Aggiungi - I campi sono già attivi
            abilitaCampi();
            nomeTXF.setText("");
            cognomeTXF.setText("");
            for (TextField numTF : numeriTF) numTF.setText("");
            for (TextField emailTF : emailsTF) emailTF.setText("");
            immagineProfilo.setImage(new Image("res/immagine-profilo-default.jpg"));
        } else {
            // Modalità Visualizza - Disabilita i campi
            disabilitaCampi();

            // Popola i campi con i dati del contatto esistente
            nomeTXF.setText(contatto.getNomi());
            cognomeTXF.setText(contatto.getCognomi());
            for (int i = 0; i < numeriTF.length; i++) {
                if (i < contatto.getNumeriDiTelefono().length) {
                    numeriTF[i].setText(contatto.getNumeriDiTelefono()[i]);
                } else {
                    numeriTF[i].setText("");
                }
            }
            for (int i = 0; i < emailsTF.length; i++) {
                if (i < contatto.getEmails().length) {
                    emailsTF[i].setText(contatto.getEmails()[i]);
                } else {
                    emailsTF[i].setText("");
                }
            }

//            try {
//
//                if (contatto.getImmagineProfilo() != null) {
//                    immagineProfilo.setImage(new Image(contatto.getImmagineProfilo().toURI().toString()));
//                } else {
//                    immagineProfilo.setImage(new Image("file:path_to_default_image.jpg"));
//                }
//            } catch (IOException e) {
//
//                System.err.println("Errore getImmagineProfilo");
//            }
        }
    }

 /**
 *
 * @brief Metodo per disabilitare i campi di testo
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto i campi textField in cui appaiono i dati vengono disabilitati per le modifiche.
 *
 */

    private void disabilitaCampi() {
        nomeTXF.setDisable(true);
        cognomeTXF.setDisable(true);
        for (TextField numTF : numeriTF) numTF.setDisable(true);
        for (TextField emailTF : emailsTF) emailTF.setDisable(true);
        immagineProfilo.setDisable(true); // Se l'immagine non è modificabile
        salvaBtn.setDisable(true); // disabilita il pulsante Salva
        modificaBtn.setDisable(false);  // Abilita il tasto Modifica
    }

 /**
 * @brief Metodo per abilitare i campi di testo per la modifica
 *
 * Quando viene cliccato il tasto "Modifica" all'interno della finestra di visualizzazione di un contatto vengono attivati i campi textField per le modifiche.
 */

    private void abilitaCampi() {
        nomeTXF.setDisable(false);
        cognomeTXF.setDisable(false);
        for (TextField numTF : numeriTF) numTF.setDisable(false);
        for (TextField emailTF : emailsTF) emailTF.setDisable(false);
        immagineProfilo.setDisable(false);  // Abilita anche l'immagine (se modificabile)
        salvaBtn.setDisable(false); // abilita il pulsante Salva
        modificaBtn.setDisable(true);  // Disabilita il tasto Modifica (non può essere premuto durante la modifica)
    }

       /// Gestore del tasto Modifica
    @FXML
    private void modificaContatto(ActionEvent event) {
        // Abilita i campi di testo per la modifica
        abilitaCampi();
    }

    /// Gestore del tasto Salva
    private void salvaContatto(ActionEvent event) throws InfoContattoException {
        // Recupera i dati modificati
        String nome = nomeTXF.getText();
        String cognome = cognomeTXF.getText();
        String[] numeriDiTelefono = new String[numeriTF.length];
        for(int i = 0; i < numeriTF.length; i++) numeriDiTelefono[i] = numeriTF[i].getText();
        String[] emails = new String[emailsTF.length];
        for(int i = 0; i < emailsTF.length; i++) emails[i] = emailsTF[i].getText();
        // Esegui la validazione dei dati (come in precedenza)

        // Salva il contatto (se non esiste già, creane uno nuovo, altrimenti aggiorna quello esistente)
        if (contattoSelezionato == null) {

            contattoSelezionato = new Contatto(nome, cognome, numeriDiTelefono, emails, null);
            contattoAggiunto = true;
        }
        else contattoAggiunto = false;

//        } else {
//            contattoSelezionato.getNomi.s(nome);
//            contattoSelezionato.setCognomi(cognome);
//            contattoSelezionato.setNumeri(Arrays.asList(numeri));
//            contattoSelezionato.setEmails(Arrays.asList(emails));
//        }
        // Esegui il salvataggio dei dati
        // Salva nel database, file o nella struttura dati che stai utilizzando

        // Dopo aver salvato, disabilita i campi e riabilita il pulsante Modifica
        disabilitaCampi();

    }

   /// Gestore del tasto Indietro
    private void tornaIndietro(ActionEvent event) throws IOException {
        // Carica la schermata principale
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPrincipale.fxml"));
        Parent root = loader.load();

        // Ottieni il controller della schermata principale (se necessario)
        ControllerMenuPrincipale controllerMenuPrincipale = loader.getController();


        if(contattoAggiunto == true)
            controllerMenuPrincipale.setContatto(contattoSelezionato);
        else
            controllerMenuPrincipale.setContatto(null);
        // Crea la nuova scena
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aggiungiImmagine(ActionEvent event) {
    }
}
