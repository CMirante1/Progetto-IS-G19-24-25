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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuContattoController {

    private MenuPrincipaleController mpController;
    private MenuContattoView mcView;
    private Scene menuPrincipale;
    private Contatto contattoRicevuto;
    private boolean contattoAggiunto;

    public MenuContattoController(MenuPrincipaleController mpController, MenuContattoView mcView, Scene menuPrincipale) {

        this.mpController = mpController;
        this.mcView = mcView;
        this.menuPrincipale = menuPrincipale;

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        mcView.getEscBtn().setOnAction(event -> tornaIndietro(event));

        mcView.getModificaBtn().setOnAction(event -> abilitaModifica());

        mcView.getSalvaBtn().setOnAction(event -> salvaContatto());

        mcView.getAggiungiImmagineBtn().setOnAction(event -> aggiungiImmagine(event));
    }

    private void tornaIndietro(ActionEvent event) {

        if(contattoAggiunto == true)
            mpController.passaNuovoContatto(contattoRicevuto);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuPrincipale);
    }

    public void setContatto(Contatto contatto) {

        this.contattoRicevuto = contatto;
        this.contattoAggiunto = false;

        if(contattoRicevuto == null) {

            pulisciCampi();
            abilitaCampi();
            mcView.getModificaBtn().setDisable(true);
            mcView.getModificaBtn().setVisible(false);
            mcView.getSalvaBtn().setDisable(false);
            mcView.getSalvaBtn().setVisible(true);
        }
        else {

            riempiCampi();
            disabilitaCampi();
            mcView.getModificaBtn().setDisable(false);
            mcView.getModificaBtn().setVisible(true);
            mcView.getSalvaBtn().setDisable(true);
            mcView.getSalvaBtn().setVisible(false);
        }

    }

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
                mcView.getModificaBtn().setDisable(false);
                mcView.getModificaBtn().setVisible(true);
                mcView.getSalvaBtn().setDisable(true);
                mcView.getSalvaBtn().setVisible(false);

                contattoAggiunto = true;
            }
            else {

                contattoRicevuto.modificaContatto(nome, cognome, numeri, emails, immagineprofilo);

                disabilitaCampi();
                mcView.getModificaBtn().setDisable(false);
                mcView.getModificaBtn().setVisible(true);
                mcView.getSalvaBtn().setDisable(true);
                mcView.getSalvaBtn().setVisible(false);

                contattoAggiunto = false;
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

        FileChooser.ExtensionFilter filtroPNG = new FileChooser.ExtensionFilter("PNG Files", "*.png");
        FileChooser.ExtensionFilter filtroJPEG = new FileChooser.ExtensionFilter("JPEG Files", "*.jpg", "*.jpeg");

        fileChooser.getExtensionFilters().addAll(filtroPNG, filtroJPEG);

        File immagineScelta = fileChooser.showOpenDialog(stage);

        if(immagineScelta == null) return;

        mcView.getImmagineProfilo().setImage(new Image(immagineScelta.getAbsolutePath()));
    }

    private void abilitaModifica() {

        abilitaCampi();
        mcView.getModificaBtn().setDisable(true);
        mcView.getModificaBtn().setVisible(false);
        mcView.getSalvaBtn().setDisable(false);
        mcView.getSalvaBtn().setVisible(true);
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
        for(int i = 0; i < mcView.getEmailsTF().length; i++)
            mcView.getEmailsTF()[i].setText(contattoRicevuto.getEmails()[i]);
        try {

            mcView.getImmagineProfilo().setImage(contattoRicevuto.getImmagineProfilo());
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore durante il caricamento dell'immagine profilo!");
            alert.showAndWait();
        }
    }

    private void abilitaCampi() {

        mcView.getNomeTF().setEditable(true);
        mcView.getCognomeTF().setEditable(true);
        for(TextField numeroTF : mcView.getNumeriTF()) numeroTF.setEditable(true);
        for(TextField emailTF : mcView.getEmailsTF()) emailTF.setEditable(true);
        mcView.getAggiungiImmagineBtn().setDisable(false);
        mcView.getAggiungiImmagineBtn().setVisible(true);
    }

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
}
