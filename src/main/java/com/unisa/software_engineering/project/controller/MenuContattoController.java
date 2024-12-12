package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.model.ContattoV3;
import com.unisa.software_engineering.project.view.MenuContattoView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MenuContattoController {

    private MenuContattoView mcView;
    private Scene menuPrincipale;
    private ContattoV3 contattoRicevuto;

    public MenuContattoController(MenuContattoView mcView, Scene menuPrincipale) {

        this.mcView = mcView;
        this.menuPrincipale = menuPrincipale;

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        mcView.getEscBtn().setOnAction(event -> tornaIndietro(event));

        mcView.getModificaBtn().setOnAction(event -> abilitaModifica());

        mcView.getSalvaBtn().setOnAction(event -> salvaContatto());

        mcView.getAggiungiImmagineBtn().setOnAction(event -> aggiungiImmagine());
    }

    private void aggiungiImmagine() {
    }

    private void salvaContatto() {
    }

    private void abilitaModifica() {

        abilitaCampi();
        mcView.getSalvaBtn().setDisable(false);
        mcView.getSalvaBtn().setVisible(true);
    }

    private void tornaIndietro(ActionEvent event) {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuPrincipale);
    }

    public void setContatto(ContattoV3 contatto) {

        this.contattoRicevuto = contatto;

        if(contattoRicevuto == null) {

            pulisciCampi();
            abilitaCampi();
            mcView.getModificaBtn().setDisable(true);
            mcView.getModificaBtn().setVisible(false);
        }
        else {

            riempiCampi();
            disabilitaCampi();
            mcView.getSalvaBtn().setDisable(true);
            mcView.getSalvaBtn().setVisible(false);
        }

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
        mcView.getImmagineProfilo().setImage(new Image("immagineProfiloDefault.png"));
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
}
