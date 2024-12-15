package com.unisa.software_engineering.project.view;

import com.unisa.software_engineering.project.model.Contatto;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

public class MenuPrincipaleView extends AnchorPane {

    private TableView<Contatto> tabellaContatti;
    private TableColumn<Contatto, String> cognomeCln;
    private TableColumn<Contatto, String> nomeCln;
    private TextField barraRicerca;
    private Button aggiungiBtn, importaBtn;
    private ContextMenu menuContestuale;
    private MenuItem esportaBtn, eliminaBtn;
    private Button temaBtn;
/**
 * @file MenuPrincipaleView.java
 * @class MenuPrincipaleView
 * @brief Gestisce l'interfaccia grafica principale dell'applicazione per la visualizzazione e gestione dei contatti.
 */
    public MenuPrincipaleView() {

        super();

        inizializzaTabella();
        inizializzaPulsanti();
        inizializzaBarraRicerca();
        inizializzaMenuContestuale();

        this.getChildren().addAll(temaBtn, barraRicerca, aggiungiBtn, importaBtn, tabellaContatti);
    }

    private void inizializzaTabella() {

        //inizializzazione tabella contatti
        tabellaContatti = new TableView<>();
        tabellaContatti.setPrefWidth(750);
        tabellaContatti.setPrefHeight(350);
        tabellaContatti.setLayoutX(0);
        tabellaContatti.setLayoutY(200);
        tabellaContatti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabellaContatti.setFocusTraversable(true);

        //inizializzazione colonna cognomi
        cognomeCln = new TableColumn<>("Cognome");
        cognomeCln.setMinWidth(375);
        cognomeCln.setPrefWidth(75);
        cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        //inizializzazione colonna nomi
        nomeCln = new TableColumn<>("Nome");
        nomeCln.setMinWidth(375);
        nomeCln.setPrefWidth(75);
        nomeCln.setCellValueFactory(new PropertyValueFactory<>("Nome"));

        tabellaContatti.getColumns().addAll(cognomeCln, nomeCln);
    }

    private void inizializzaPulsanti() {

        //inizializzazione pulsante nuovo contatto
        aggiungiBtn = new Button("Aggiungi contatto");
        aggiungiBtn.setPrefWidth(170);
        aggiungiBtn.setPrefHeight(40);
        aggiungiBtn.setLayoutX(170);
        aggiungiBtn.setLayoutY(110);
        aggiungiBtn.setFont(new javafx.scene.text.Font(18));
        aggiungiBtn.setFocusTraversable(false);

        //inizializzazione pulsante importazione contatti
        importaBtn = new Button("Importa contatti");
        importaBtn.setPrefWidth(155);
        importaBtn.setPrefHeight(40);
        importaBtn.setLayoutX(425);
        importaBtn.setLayoutY(110);
        importaBtn.setFont(new javafx.scene.text.Font(18));
        importaBtn.setFocusTraversable(false);

        //inizializzaziobe pulsante cambio tema
        temaBtn = new Button();
        temaBtn.setPrefWidth(50);
        temaBtn.setPrefHeight(50);
        temaBtn.setLayoutX(25);
        temaBtn.setLayoutY(25);
    }

    private void inizializzaBarraRicerca() {

        //inizializzazione text field barra di ricerca
        barraRicerca = new TextField();
        barraRicerca.setPrefWidth(460);
        barraRicerca.setPrefHeight(40);
        barraRicerca.setLayoutX(145);
        barraRicerca.setLayoutY(25);
        barraRicerca.setPromptText("Cerca contatti");
        barraRicerca.setAlignment(Pos.CENTER);
        barraRicerca.setFocusTraversable(false);
    }

    private void inizializzaMenuContestuale() {

        //inizializzazione menu contestuale (esportazione ed eliminazione contatti)
        esportaBtn = new MenuItem("Esporta contatto");
        eliminaBtn = new MenuItem("Elimina contatto");
        eliminaBtn.getStyleClass().add("Elimina");

        menuContestuale = new ContextMenu(esportaBtn, eliminaBtn);

        tabellaContatti.setRowFactory(tv -> {

            TableRow<Contatto> riga = new TableRow<>();
            riga.setOnMouseClicked(event -> {

                if (event.getButton() != MouseButton.PRIMARY) return;

                if (!riga.isSelected()) tabellaContatti.getSelectionModel().select(riga.getItem());
            });
            return riga;
        });

        tabellaContatti.setContextMenu(menuContestuale);
    }

    public TableView<Contatto> getTabellaContatti() {

        return tabellaContatti;
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

    public MenuItem getEliminaBtn() {

        return eliminaBtn;
    }

    public MenuItem getEsportaBtn() {

        return esportaBtn;
    }

    public Button getTemaBtn(){

        return temaBtn;
    }
}
