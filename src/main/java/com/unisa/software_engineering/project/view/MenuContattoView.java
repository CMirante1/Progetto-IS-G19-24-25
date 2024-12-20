package com.unisa.software_engineering.project.view;

import com.unisa.software_engineering.project.model.Contatto;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;



/**
 * @file MenuContattoView.java
 * @class MenuContattoView
 * @brief Definisce l'interfaccia grafica per la gestione dei dettagli di un contatto
 */
public class MenuContattoView extends AnchorPane {

    private TextField nomeTF, cognomeTF;
    private TextField[] numeriTF, emailsTF;
    private Label nomeLB, cognomeLB;
    private Label[] numeriLB, emailsLB;
    private Button escBtn, modificaBtn, salvaBtn, aggiungiImmagineBtn, rimuoviImmagineBtn;
    private ImageView immagineProfilo, escImmagine;
    private Image immagineProfiloDefault;

    public MenuContattoView() {

        super();

        inizializzaCampi();
        inizializzaEtichette();
        inizializzaImmagini();
        inizializzaPulsanti();

        this.getChildren().addAll(escBtn, modificaBtn, salvaBtn, aggiungiImmagineBtn, rimuoviImmagineBtn, cognomeLB, nomeLB, cognomeTF, nomeTF);
        this.getChildren().addAll(numeriLB[0], numeriLB[1], numeriLB[2], emailsLB[0], emailsLB[1], emailsLB[2],immagineProfilo);
        this.getChildren().addAll(numeriTF[0], numeriTF[1], numeriTF[2], emailsTF[0], emailsTF[1], emailsTF[2]);
    }

    private void inizializzaCampi() {

        //inizializzazione text field cognome
        cognomeTF = new TextField();
        cognomeTF.setPrefWidth(190);
        cognomeTF.setPrefHeight(30);
        cognomeTF.setLayoutX(35);
        cognomeTF.setLayoutY(330);

        //inizializzazione text field nome
        nomeTF = new TextField();
        nomeTF.setPrefWidth(190);
        nomeTF.setPrefHeight(30);
        nomeTF.setLayoutX(285);
        nomeTF.setLayoutY(330);

        //inizializzazione text field numeri ed emails
        numeriTF = new TextField[Contatto.MAX_NUMERI];
        emailsTF = new TextField[Contatto.MAX_EMAILS];

        for(int i = 0; i < numeriTF.length; i++) {

            numeriTF[i] = new TextField();
            numeriTF[i].setPrefWidth(190);
            numeriTF[i].setPrefHeight(30);
            numeriTF[i].setLayoutX(35 + 250 * i);
            numeriTF[i].setLayoutY(410);
        }

        for(int i = 0; i < emailsTF.length; i++) {

            emailsTF[i] = new TextField();
            emailsTF[i].setPrefWidth(190);
            emailsTF[i].setPrefHeight(30);
            emailsTF[i].setLayoutX(35 + 250 * i);
            emailsTF[i].setLayoutY(490);
        }
    }

    private void inizializzaEtichette() {

        //inizializzazione etichetta cognome
        cognomeLB = new Label("Cognome:");
        cognomeLB.setPrefWidth(70);
        cognomeLB.setPrefHeight(20);
        cognomeLB.setLayoutX(35);
        cognomeLB.setLayoutY(300);

        //inizializzazione etichetta nome
        nomeLB = new Label("Nome:");
        nomeLB.setPrefWidth(45);
        nomeLB.setPrefHeight(20);
        nomeLB.setLayoutX(285);
        nomeLB.setLayoutY(300);

        //inizializzazione etichette numeri ed emails
        numeriLB = new Label[numeriTF.length];
        emailsLB = new Label[emailsTF.length];

        for(int i = 0; i < numeriLB.length; i++) {

            numeriLB[i] = new Label("Numero " + (i + 1) + ":");
            numeriLB[i].setPrefWidth(70);
            numeriLB[i].setPrefHeight(20);
            numeriLB[i].setLayoutX(35 + 250 * i);
            numeriLB[i].setLayoutY(380);
        }

        for(int i = 0; i < emailsLB.length; i++) {

            emailsLB[i] = new Label("Email " + (i + 1) + ":");
            emailsLB[i].setPrefWidth(70);
            emailsLB[i].setPrefHeight(20);
            emailsLB[i].setLayoutX(35 + 250 * i);
            emailsLB[i].setLayoutY(460);
        }
    }

    private void inizializzaImmagini() {

        //inizializzazione immagine profilo default
        immagineProfiloDefault = new Image("immagineProfiloDefault.png");

        //inizializzazione image view per immagine profilo
        immagineProfilo = new ImageView(immagineProfiloDefault);
        immagineProfilo.setFitWidth(255);
        immagineProfilo.setFitHeight(255);
        immagineProfilo.setLayoutX(180);
        immagineProfilo.setLayoutY(10);

        //inizializzazione immagine pulsante per tornare al menu principale
        escImmagine = new ImageView(new Image("frecciaIndietro.png"));
        escImmagine.setFitWidth(85);
        escImmagine.setFitHeight(30);
        escImmagine.setPreserveRatio(true);
    }

    private void inizializzaPulsanti() {

        //inizializzazione pulsante cambio schermata
        escBtn = new Button();
        escBtn.setGraphic(escImmagine);
        escBtn.setPrefHeight(40);
        escBtn.setPrefWidth(95);
        escBtn.setLayoutX(10);
        escBtn.setLayoutY(10);
        escBtn.setGraphic(escImmagine);
        escBtn.setFocusTraversable(false);

        //inizializzazione pulsante per la modifica del contatto
        modificaBtn = new Button("Modifica");
        modificaBtn.setPrefHeight(40);
        modificaBtn.setPrefWidth(95);
        modificaBtn.setLayoutX(500);
        modificaBtn.setLayoutY(10);
        modificaBtn.setFocusTraversable(false);

        //inizializzazione pulsante salvataggio contatto
        salvaBtn = new Button("Salva");
        salvaBtn.setPrefHeight(40);
        salvaBtn.setPrefWidth(95);
        salvaBtn.setLayoutX(635);
        salvaBtn.setLayoutY(10);
        salvaBtn.setFocusTraversable(false);

        //inizializzazione pulsante aggiunta immagine profilo personalizzata
        aggiungiImmagineBtn = new Button("Aggiungi immagine profilo");
        aggiungiImmagineBtn.setPrefWidth(240);
        aggiungiImmagineBtn.setPrefHeight(40);
        aggiungiImmagineBtn.setLayoutX(480);
        aggiungiImmagineBtn.setLayoutY(115);
        aggiungiImmagineBtn.setFocusTraversable(false);

        //inizializzazione pulsante rimozione immagine profilo personalizzata
        rimuoviImmagineBtn = new Button("Rimuovi immagine profilo");
        rimuoviImmagineBtn.setPrefHeight(40);
        rimuoviImmagineBtn.setPrefWidth(240);
        rimuoviImmagineBtn.setLayoutX(480);
        rimuoviImmagineBtn.setLayoutY(185);
        rimuoviImmagineBtn.setFocusTraversable(false);

    }

    public TextField getNomeTF() {

        return nomeTF;
    }

    public TextField getCognomeTF() {

        return cognomeTF;
    }

    public TextField[] getNumeriTF() {

        return numeriTF;
    }

    public TextField[] getEmailsTF() {

        return emailsTF;
    }

    public Button getEscBtn() {

        return escBtn;
    }

    public Button getModificaBtn() {

        return modificaBtn;
    }

    public Button getSalvaBtn() {

        return salvaBtn;
    }

    public Button getAggiungiImmagineBtn() {

        return aggiungiImmagineBtn;
    }

    public Button getRimuoviImmagineBtn() {

        return rimuoviImmagineBtn;
    }

    public ImageView getImmagineProfilo() {

        return immagineProfilo;
    }

    public Image getImmagineProfiloDefault() {

        return immagineProfiloDefault;
    }
}

