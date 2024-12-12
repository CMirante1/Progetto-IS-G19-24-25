package com.unisa.software_engineering.project.view;

import com.unisa.software_engineering.project.model.ContattoV2;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;

public class MenuContattoView extends AnchorPane {

    private TextField nomeTF, cognomeTF;
    private TextField[] numeriTF, emailsTF;
    private Label nomeLB, cognomeLB;
    private Label[] numeriLB, emailsLB;
    private Button escBtn, modificaBtn, salvaBtn, aggiungiImmagineBtn;
    private ImageView immagineProfilo, escImmagine;

    public MenuContattoView() {

        super();

        inizializzaCampi();
        inizializzaEtichette();
        inizializzaImmagini();
        inizializzaPulsanti();

        this.getChildren().addAll(escBtn, modificaBtn, salvaBtn, aggiungiImmagineBtn, nomeLB, cognomeLB);
        this.getChildren().addAll(numeriLB[0], numeriLB[1], numeriLB[2], emailsLB[0], emailsLB[1], emailsLB[2],immagineProfilo);
        this.getChildren().addAll(numeriTF[0], numeriTF[1], numeriTF[2], emailsTF[0], emailsTF[1], emailsTF[2]);
    }

    private void inizializzaCampi() {

        nomeTF = new TextField();
        nomeTF.setFocusTraversable(true);
        nomeTF.setPrefWidth(190);
        nomeTF.setPrefHeight(30);
        nomeTF.setLayoutX(35);
        nomeTF.setLayoutY(385);

        cognomeTF = new TextField();
        cognomeTF.setPrefWidth(190);
        cognomeTF.setPrefHeight(30);
        cognomeTF.setLayoutX(285);
        cognomeTF.setLayoutY(385);

        numeriTF = new TextField[ContattoV2.getnDati()];
        emailsTF = new TextField[ContattoV2.getnDati()];
        for(int i = 0; i < numeriTF.length; i++) {

            numeriTF[i] = new TextField();
            numeriTF[i].setPrefWidth(190);
            numeriTF[i].setPrefHeight(30);
            numeriTF[i].setLayoutY(385);
        }
        numeriTF[0].setLayoutY(35);
        numeriTF[1].setLayoutY(285);
        numeriTF[2].setLayoutY(525);

        for(int i = 0; i < emailsTF.length; i++) {

            emailsTF[i] = new TextField();
            emailsTF[i].setPrefWidth(190);
            emailsTF[i].setPrefHeight(30);
            emailsTF[i].setLayoutY(545);
        }
        emailsTF[0].setLayoutX(35);
        emailsTF[1].setLayoutX(285);
        emailsTF[2].setLayoutX(385);
    }

    private void inizializzaEtichette() {

        nomeLB = new Label("Nome:");
        nomeLB.setPrefWidth(45);
        nomeLB.setPrefHeight(20);
        nomeLB.setLayoutX(35);
        nomeLB.setLayoutY(345);

        cognomeLB = new Label("Cognome:");
        cognomeLB.setPrefWidth(70);
        cognomeLB.setPrefHeight(20);
        cognomeLB.setLayoutX(285);
        cognomeLB.setLayoutY(345);

        numeriLB = new Label[numeriTF.length];
        emailsLB = new Label[emailsTF.length];

        for(int i = 0; i < numeriLB.length; i++) {

            numeriLB[i] = new Label("Numero " + i + ":");
            numeriLB[i].setPrefWidth(70);
            numeriLB[i].setPrefHeight(20);
            numeriLB[i].setLayoutY(425);
        }
        numeriLB[0].setLayoutX(35);
        numeriLB[1].setLayoutX(285);
        numeriLB[2].setLayoutX(535);

        for(int i = 0; i < emailsLB.length; i++) {

            emailsLB[i] = new Label("Email " + i + ":");
            emailsLB[i].setPrefWidth(70);
            emailsLB[i].setPrefHeight(20);
            emailsLB[i].setLayoutY(505);
        }
        emailsLB[0].setLayoutX(35);
        emailsLB[1].setLayoutX(285);
        emailsLB[2].setLayoutX(535);
    }

    private void inizializzaImmagini() {

        immagineProfilo = new ImageView();
        immagineProfilo.setFitWidth(255);
        immagineProfilo.setFitHeight(255);
        immagineProfilo.setLayoutX(220);
        immagineProfilo.setLayoutY(10);

        escImmagine = new ImageView(new Image("frecciaIndietro.png"));
        escImmagine.setFitWidth(85);
        escImmagine.setFitHeight(30);
        escImmagine.setPreserveRatio(true);
    }

    private void inizializzaPulsanti() {

        escBtn = new Button();
        escBtn.setGraphic(escImmagine);
        escBtn.setPrefHeight(40);
        escBtn.setPrefWidth(95);
        escBtn.setLayoutX(10);
        escBtn.setLayoutY(10);
        escBtn.setGraphic(escImmagine);
        escBtn.setFocusTraversable(false);

        modificaBtn = new Button("Modifica");
        modificaBtn.setPrefHeight(40);
        modificaBtn.setPrefWidth(95);
        modificaBtn.setLayoutX(500);
        modificaBtn.setLayoutY(10);
        modificaBtn.setFocusTraversable(false);

        salvaBtn = new Button("Salva");
        salvaBtn.setPrefHeight(40);
        salvaBtn.setPrefWidth(95);
        salvaBtn.setLayoutX(635);
        salvaBtn.setLayoutY(10);
        salvaBtn.setFocusTraversable(false);

        aggiungiImmagineBtn = new Button("Aggiungi immagine profilo");
        aggiungiImmagineBtn.setPrefHeight(40);
        aggiungiImmagineBtn.setPrefWidth(240);
        aggiungiImmagineBtn.setLayoutX(220);
        aggiungiImmagineBtn.setLayoutY(275);
        aggiungiImmagineBtn.setFocusTraversable(false);

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

    public ImageView getImmagineProfilo() {

        return immagineProfilo;
    }
}
