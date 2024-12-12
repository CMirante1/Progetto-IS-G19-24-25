package com.unisa.software_engineering.project.controller;

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

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import com.unisa.software_engineering.project.model.ContattoV2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class ControllerMenuContatto implements Initializable{

    @FXML
    private TextField nomeTF;
    @FXML
    private TextField cognomeTF;
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

    private TextField[] numeriTF;
    private TextField[] emailsTF;
    private ContattoV2 contattoSelezionato;
    private ContattoV2 nuovoContatto;
    private boolean contattoAggiunto;


    /**
 *
 * @brief metodo per settare la rubrica all'interno del controller del menu contatto
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto viene passato al controller il riferimento della rubrica da cui proviene il contatto
 * @param rubrica Rappresenta la struttura dati da cui proviene il contatto
 */

//    public void setRubrica(Rubrica rubrica) {
//
//        this.rubrica = rubrica;
//    }

 /**
 * @brief metodo per settare il contatto da visualizzare all'interno del controller del menu contatto
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto viene passato al controller il riferimento del contatto da cui vengono prelevati gli attributi e visualizzati nei textField della finestra di visualizzazione
 * @param contatto
 *
 */

    public void setContatto(ContattoV2 contatto) {

            this.contattoSelezionato = contatto;

            if (contatto == null) {
                // Modalità Aggiungi - Svuto i campi e li abilito
                System.out.println("sto aggiungendo");
                // pulisciCampi();
                // abilitaCampi();
            } else {
                // Modalità Visualizza - Riempio i campi e li disabilito
                riempiCampi();
                disabilitaCampi();

            }



    }

    private void pulisciCampi() {

        nomeTF.setText("");
        cognomeTF.setText("");
        for (TextField numTF : numeriTF) numTF.setText("");
        for (TextField emailTF : emailsTF) emailTF.setText("");
       // immagineProfilo.setImage(new Image("res/immagine-profilo-default.jpg"));
    }

    private void riempiCampi() {

        nomeTF.setText(contattoSelezionato.getNome());
        cognomeTF.setText(contattoSelezionato.getCognome());
        for (int i = 0; i < numeriTF.length; i++) numeriTF[i].setText(contattoSelezionato.getNumeri()[i]);
        for (int i = 0; i < emailsTF.length; i++)  emailsTF[i].setText(contattoSelezionato.getEmails()[i]);
        //immagineProfilo.setImage(contatto.get);
    }

 /**
 *
 * @brief Metodo per disabilitare i campi di testo
 *
 * Quando viene aperta la finestra di visualizzazione di un contatto i campi textField in cui appaiono i dati vengono disabilitati per le modifiche.
 *
 */

    private void disabilitaCampi() {

        nomeTF.setDisable(true);
        cognomeTF.setDisable(true);
        for (TextField numTF : numeriTF) numTF.setDisable(true);
        for (TextField emailTF : emailsTF) emailTF.setDisable(true);
        salvaBtn.setVisible(false);
        salvaBtn.setDisable(true);
    }

 /**
 * @brief Metodo per abilitare i campi di testo per la modifica
 *
 * Quando viene cliccato il tasto "Modifica" all'interno della finestra di visualizzazione di un contatto vengono attivati i campi textField per le modifiche.
 */

    private void abilitaCampi() {

        nomeTF.setDisable(false);
        cognomeTF.setDisable(false);
        for (TextField numTF : numeriTF) numTF.setDisable(false);
        for (TextField emailTF : emailsTF) emailTF.setDisable(false);
        immagineProfilo.setDisable(false);  // Abilita anche l'immagine (se modificabile)
        salvaBtn.setDisable(false); // abilita il pulsante Salva
        modificaBtn.setVisible(false);
        modificaBtn.setDisable(true);
    }

       /// Gestore del tasto Modifica
    @FXML
    private void modificaContatto(ActionEvent event) {
        // Abilita i campi di testo per la modifica
        abilitaCampi();
    }

  /*  /// Gestore del tasto Salva
    @FXML
    private void salvaContatto(ActionEvent event) {
        // Recupera i dati modificati
        String nome = nomeTF.getText();
        System.out.println("Nome: " + nome);
        System.out.println("TF: " + nomeTF.getText());
        String cognome = cognomeTF.getText();
        String[] numeriDiTelefono = new String[numeriTF.length];
        for(int i = 0; i < numeriTF.length; i++) numeriDiTelefono[i] = numeriTF[i].getText();
        String[] emails = new String[emailsTF.length];
        for(int i = 0; i < emailsTF.length; i++) emails[i] = emailsTF[i].getText();
        // Esegui la validazione dei dati (come in precedenza)

        // Salva il contatto (se non esiste già, creane uno nuovo, altrimenti aggiorna quello esistente)
        if (contattoSelezionato == null) {
            try {

                this.nuovoContatto = new ContattoV2(nome, cognome, numeriDiTelefono, emails);
                contattoAggiunto = true;
            } catch (InfoContattoException ex) {

                System.out.println("Errore nell'aggiunta del contatto.\n" + ex.getMessage());
            }
        }


        disabilitaCampi();
        modificaBtn.setVisible(true);
        modificaBtn.setDisable(false);

    }
*/
   /// Gestore del tasto Indietro
    @FXML
    private void tornaIndietro(ActionEvent event) throws IOException {
        // Carica la schermata principale
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuPrincipale.class.getResource("MenuPrincipale.fxml"));

        // Ottieni il controller della schermata principale (se necessario)

        Parent root = fxmlLoader.load();
        ControllerMenuPrincipale controllerMenuPrincipale = fxmlLoader.getController();

        if(contattoAggiunto == true) {

            controllerMenuPrincipale.setContatto(nuovoContatto);
        }

        // Crea la nuova scena
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void aggiungiImmagine(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            numeriTF = new TextField[ContattoV2.getnDati()];
            emailsTF = new TextField[ContattoV2.getnDati()];


            for(int i = 0; i < numeriTF.length; i++) numeriTF[i] = new TextField();
            for(int i = 0; i < emailsTF.length; i++) emailsTF[i] = new TextField();

    }
}
