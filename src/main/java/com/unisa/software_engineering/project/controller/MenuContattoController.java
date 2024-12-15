package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import com.unisa.software_engineering.project.model.Contatto;
import com.unisa.software_engineering.project.view.MenuContattoView;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @file MenuPrincipaleController.java
 * @class MenuContattoController
 * @brief Controller per gestire il menu dei contatti.
 *
 * Questa classe gestisce la logica e i metodi per visualizzare, modificare e salvare i dettagli di un contatto
*/
import java.net.URI;
import java.net.URISyntaxException;


public class MenuContattoController {

    private final MenuPrincipaleController mpController;
    private final MenuContattoView mcView;
    private final Scene menuPrincipale;
    private  Contatto contattoRicevuto;
    private boolean contattoAggiunto;

    /**
     * @brief Costruttore per MenuContattoController.
     *
     * @param mpController il controller del menu principale.
     * @param mcView la view del menu contatto.
     * @param menuPrincipale la scena del menu principale.
     */
    public MenuContattoController(MenuPrincipaleController mpController, MenuContattoView mcView, Scene menuPrincipale) {

        this.mpController = mpController;
        this.mcView = mcView;
        this.menuPrincipale = menuPrincipale;

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        //bordo text field nome e cognome rosso
        mcView.getNomeTF().textProperty().addListener((observable, oldValue, newValue) -> checkNomeCognome());
        mcView.getCognomeTF().textProperty().addListener((observable, oldValue, newValue) -> checkNomeCognome());

        //definizione logica pulsante cambio schermata
        mcView.getEscBtn().setOnAction(event -> tornaIndietro(event));

        //definizione logica pulsante modifica contatto
        mcView.getModificaBtn().setOnAction(event -> abilitaModifica());

        //definizione logica pulsante salvataggio contatto
        mcView.getSalvaBtn().setOnAction(event -> salvaContatto());

        //definizione logica pulsante aggiunta immagine profilo personalizzata
        mcView.getAggiungiImmagineBtn().setOnAction(event -> aggiungiImmagine(event));

        //definizione logica pulsante rimozione immagine profilo personalizzata
        mcView.getRimuoviImmagineBtn().setOnAction(event -> rimuoviImmagine());

        //definizione logica apertura client di posta
        for(TextField emailTF : mcView.getEmailsTF())
            emailTF.setOnMouseClicked(event -> apriClientEmail(event, emailTF));
    }
    /**
     * @brief collegamento con app di email
     *
     * Reindirizza l'utente al proprio client di posta elettronica quando seleziona un indirizzo email di un contatto.
     * Se il campo email non contiene alcun indirizzo o se il click avviene con un tasto diverso da quello sinistro del mouse allora il click viene ignorato
     *
     * @param event il click dell'utente
     * @param emailTF l'indirizzo email
     */
    private void apriClientEmail(MouseEvent event, TextField emailTF) {

        if(emailTF.isEditable() || emailTF.getText().trim().isEmpty()) return;

        if(!(event.getButton() == MouseButton.PRIMARY)) return;

        if (Desktop.isDesktopSupported()) {
            try {

                URI mailto = new URI("mailto:" + emailTF.getText());
                Desktop.getDesktop().mail(mailto);
            } catch (URISyntaxException | IOException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Impossibile aprire il client email!");
                alert.showAndWait();
            }
        }
    }
    /**
     * @brief Permette di ritornare al menu principale dal menu contatto.
     *
     * Quando l'utente clicka il tasto "indietro", in alto a sinistra della schermata del menu contatto, verrà reindirizzato alla schermata del menu principale
     *
     * @param event la scena del menu principale.
     */
    private void tornaIndietro(ActionEvent event) {

        if(contattoAggiunto) mpController.passaNuovoContatto(contattoRicevuto);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuPrincipale);
    }

    /**
     * Imposta il contatto corrente da visualizzare o modificare.
     *
     * @param contatto riceve in input il contatto da visualizzare.
     */
    public void setContatto(Contatto contatto) {

        this.contattoRicevuto = contatto;
        this.contattoAggiunto = false;

        if(contattoRicevuto == null) {

            pulisciCampi();
            abilitaCampi();
            aggiornaBottoni(false, false, true, true);

            mcView.getNomeTF().setStyle("-fx-border-color: red;");
            mcView.getCognomeTF().setStyle("-fx-border-color: red;");
        }
        else {

            riempiCampi();
            disabilitaCampi();
            aggiornaBottoni(true, true, false, false);
        }

    }
    /**
     * @brief Salva in rubrica i dati inseriti
     *
     * Il metodo salva un nuovo contatto in rubrica oppure salva le modifiche di un contatto già esistente attraverso il valroe di contattoRicevuto.
     * In particolare se contattoRicevuto==null allora l'utente sta cercando di salvare un nuovo contatto, se invece contattoRicevuto!=null allora sta cercando di modificare un contatto già esistente.
     *
     */
    private void salvaContatto() {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        String nome = mcView.getNomeTF().getText();
        String cognome = mcView.getCognomeTF().getText();
        String[] numeri = new String[Contatto.MAX_NUMERI];
        String[] emails = new String[Contatto.MAX_EMAILS];
        for(int i = 0; i < numeri.length; i++) numeri[i] = mcView.getNumeriTF()[i].getText();
        for(int i = 0; i < emails.length; i++) emails[i] = mcView.getEmailsTF()[i].getText();
        BufferedImage immagineprofilo = immagineABufferedImage(mcView.getImmagineProfilo().getImage());

        try {

            if(contattoRicevuto == null) {

                contattoRicevuto = new Contatto(nome, cognome, numeri, emails, immagineprofilo);

                disabilitaCampi();
                aggiornaBottoni(true, true, false, false);

                contattoAggiunto = true;
            }
            else {

                contattoRicevuto.modificaContatto(nome, cognome, numeri, emails, immagineprofilo);

                disabilitaCampi();
                aggiornaBottoni(true, true, false, false);
            }
        } catch(IOException e) {

            alert.setContentText("Errore durante l'inserimento dell'immagine profilo!");
            alert.showAndWait();
        } catch(InfoContattoException e) {

            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void aggiungiImmagine(ActionEvent event) {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("File supportati", "*.png", "*.jpg", "*.jpeg");

        fileChooser.getExtensionFilters().add(filtro);

        File immagineScelta = fileChooser.showOpenDialog(stage);

        if(immagineScelta == null) return;

       mcView.getImmagineProfilo().setImage(new Image(immagineScelta.toURI().toString()));
    }

    private void rimuoviImmagine() {

        mcView.getImmagineProfilo().setImage(new Image("immagineProfiloDefault.png"));
    }

    private void abilitaModifica() {

        abilitaCampi();
        aggiornaBottoni(false, false, true, true);
    }

    private void pulisciCampi() {

        mcView.getNomeTF().setText("");
        mcView.getCognomeTF().setText("");
        for(TextField numeroTF : mcView.getNumeriTF()) numeroTF.setText("");
        for(TextField emailTF : mcView.getEmailsTF()) emailTF.setText("");
        mcView.getImmagineProfilo().setImage(new Image("immagineProfiloDefault.png"));
    }

    private void riempiCampi() {

        mcView.getNomeTF().setText(contattoRicevuto.getNome());
        mcView.getCognomeTF().setText(contattoRicevuto.getCognome());

        for(int i = 0; i < mcView.getNumeriTF().length; i++)
            mcView.getNumeriTF()[i].setText(contattoRicevuto.getNumeri()[i]);
        for(int i = 0; i < mcView.getEmailsTF().length; i++) {
            mcView.getEmailsTF()[i].setText(contattoRicevuto.getEmails()[i]);
            mcView.getEmailsTF()[i].setStyle("-fx-text-inner-color: #0000FF;");
        }
        try {

            if(contattoRicevuto.getImmagineProfilo() == null)
                mcView.getImmagineProfilo().setImage(new Image("immagineProfiloDefault.png"));
            else
                mcView.getImmagineProfilo().setImage(contattoRicevuto.getImmagineProfilo());
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore durante il caricamento dell'immagine profilo!");
            alert.showAndWait();
        }

    }
    /**
     * @brief attiva i campi del menu contatto
     * Abilita i textfield contenenti i dati del contatto quando l'utente vuole modificarne il contenuto o inserire un nuovo contatto
     *
     */
    private void abilitaCampi() {

        mcView.getNomeTF().setEditable(true);
        mcView.getCognomeTF().setEditable(true);
        for(TextField numeroTF : mcView.getNumeriTF()) numeroTF.setEditable(true);
        for(TextField emailTF : mcView.getEmailsTF()) emailTF.setEditable(true);
        mcView.getAggiungiImmagineBtn().setDisable(false);
        mcView.getAggiungiImmagineBtn().setVisible(true);
    }
    /**
     * @brief disattiva i campi del menu contatto
     * Disabilita i textfield contenenti i dati del contatto quando l'utente termina la modifica o l'inserimento di un nuovo contatto
     *
     */
    private void disabilitaCampi() {

        mcView.getNomeTF().setEditable(false);
        mcView.getCognomeTF().setEditable(false);
        for(TextField numeroTF : mcView.getNumeriTF()) numeroTF.setEditable(false);
        for(TextField emailTF : mcView.getEmailsTF()) emailTF.setEditable(false);
        mcView.getAggiungiImmagineBtn().setDisable(true);
        mcView.getAggiungiImmagineBtn().setVisible(false);
    }

    private BufferedImage immagineABufferedImage(Image immagineProfilo) {

        return  SwingFXUtils.fromFXImage(immagineProfilo, null);
    }

    private void aggiornaBottoni(boolean modificaAttivo, boolean modificaVisibile, boolean salvaAttivo, boolean salvaVisibile) {

        mcView.getModificaBtn().setDisable(!modificaAttivo);
        mcView.getModificaBtn().setVisible(modificaVisibile);

        mcView.getSalvaBtn().setDisable(!salvaAttivo);
        mcView.getSalvaBtn().setVisible(salvaVisibile);
    }

    private void checkNomeCognome() {
        String nome = mcView.getNomeTF().getText().trim();
        String cognome = mcView.getCognomeTF().getText().trim();

        if (nome.isEmpty() && cognome.isEmpty()) {
            mcView.getNomeTF().setStyle("-fx-border-color: red;");
            mcView.getCognomeTF().setStyle("-fx-border-color: red;");
        } else {
            mcView.getNomeTF().setStyle("");
            mcView.getCognomeTF().setStyle("");
        }
    }
}
