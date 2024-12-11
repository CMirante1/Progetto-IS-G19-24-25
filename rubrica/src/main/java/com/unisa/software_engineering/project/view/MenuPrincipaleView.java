package com.unisa.software_engineering.project.view;

import com.unisa.software_engineering.project.model.ContattoV2;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipaleView extends AnchorPane {

    private TableView<ContattoV2> tabellaContatti;
    private TableColumn<ContattoV2, String> cognomeCln;
    private TableColumn<ContattoV2, String> nomeCln;
    private TextField barraRicerca;
    private Button aggiungiBtn, importaBtn;
    private ContextMenu menuContestuale;
    private MenuItem eliminaBtn, esportaBtn;

    public MenuPrincipaleView() {

        super();

        inizializzaTabella();
        inizializzaPulsanti();
        inizializzaBarraRicerca();
        inizializzaMenuContestuale();

        this.getChildren().addAll(barraRicerca, aggiungiBtn, importaBtn, tabellaContatti);
    }

    private void inizializzaTabella() {

        tabellaContatti = new TableView<>();
        tabellaContatti.setEditable(true);
        tabellaContatti.setLayoutX(0);
        tabellaContatti.setLayoutY(200);
        tabellaContatti.setPrefHeight(400);
        tabellaContatti.setPrefWidth(750);
        tabellaContatti.setFocusTraversable(true);

        cognomeCln = new TableColumn<>("Cognome");
        cognomeCln.setMinWidth(375);
        cognomeCln.setPrefWidth(75);
        cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        nomeCln = new TableColumn<>("Nome");
        nomeCln.setMinWidth(375);
        nomeCln.setPrefWidth(75);
        nomeCln.setCellValueFactory(new PropertyValueFactory<>("Nome"));

        tabellaContatti.getColumns().addAll(cognomeCln, nomeCln);
    }

    private void inizializzaPulsanti() {

        aggiungiBtn = new Button("Aggiungi contatto");
        aggiungiBtn.setLayoutX(170);
        aggiungiBtn.setLayoutY(110);
        aggiungiBtn.setPrefHeight(40);
        aggiungiBtn.setPrefWidth(170);
        aggiungiBtn.setFont(new javafx.scene.text.Font(18));
        aggiungiBtn.setFocusTraversable(false);

        importaBtn = new Button("Importa contatti");
        importaBtn.setLayoutX(425);
        importaBtn.setLayoutY(110);
        importaBtn.setPrefHeight(40);
        importaBtn.setPrefWidth(155);
        importaBtn.setFont(new javafx.scene.text.Font(18));
        importaBtn.setFocusTraversable(false);
    }

    private void inizializzaBarraRicerca() {

        barraRicerca = new TextField();
        barraRicerca.setLayoutX(145);
        barraRicerca.setLayoutY(25);
        barraRicerca.setPrefHeight(40);
        barraRicerca.setPrefWidth(460);
        barraRicerca.setPromptText("Cerca contatti");
        barraRicerca.setAlignment(Pos.CENTER);
        barraRicerca.setFocusTraversable(false);
    }

    private void inizializzaMenuContestuale() {

        eliminaBtn = new MenuItem("Elimina contatto");
        esportaBtn = new MenuItem("Esporta contatto");

        menuContestuale = new ContextMenu(eliminaBtn, esportaBtn);

        tabellaContatti.setContextMenu(menuContestuale);
    }

    public TableView<ContattoV2> getTabellaContatti() {

        return tabellaContatti;
    }

    public TableColumn<ContattoV2, String> getCognomeCln() {

        return cognomeCln;
    }

    public TableColumn<ContattoV2, String> getNomeCln() {

        return nomeCln;
    }

    public TextField getBarraRicerca() {

        return barraRicerca;
    }

    public Button getAggiungiBtn() {

        return aggiungiBtn;
    }

    public Button getImportaBtn() {

        return importaBtn;
    }

    public ContextMenu getMenuContestuale() {

        return menuContestuale;
    }

    public MenuItem getEliminaBtn() {

        return eliminaBtn;
    }

    public MenuItem getEsportaBtn() {

        return esportaBtn;
    }
}