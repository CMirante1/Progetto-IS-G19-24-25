package com.unisa.software_engineering.project.view;

import com.unisa.software_engineering.project.model.ContattoV2;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

        this.getChildren().addAll(escBtn, immagineProfilo);
    }

    private void inizializzaCampi() {

        nomeTF = new TextField();
        nomeTF.setFocusTraversable(true);
        cognomeTF = new TextField();
        numeriTF = new TextField[ContattoV2.getnDati()];
        emailsTF = new TextField[ContattoV2.getnDati()];
        for(int i = 0; i < numeriTF.length; i++) numeriTF[i] = new TextField();
        for(int i = 0; i < emailsTF.length; i++) emailsTF[i] = new TextField();
    }

    private void inizializzaEtichette() {

        nomeLB = new Label("Nome:");
        cognomeLB = new Label("Cognome:");
        numeriLB = new Label[numeriTF.length];
        emailsLB = new Label[emailsTF.length];

        for(int i = 0; i < numeriLB.length; i++) numeriLB[i] = new Label("Numero " + i + ":");
        for(int i = 0; i < emailsLB.length; i++) emailsLB[i] = new Label("Email " + i + ":");
    }

    private void inizializzaImmagini() {

        immagineProfilo = new ImageView(new Image("/res/immagine-profilo-default.jpg"));
        escImmagine = new ImageView(new Image("/res/immagine-profilo-default.jpg"));
    }

    private void inizializzaPulsanti() {

        escBtn = new Button();
        escBtn.setGraphic(escImmagine);

        escBtn.setPrefHeight(40);
        escBtn.setPrefWidth(170);
        escBtn.setLayoutX(170);
        escBtn.setLayoutY(110);
    }


}

