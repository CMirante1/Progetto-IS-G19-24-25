package com.unisa.software_engineering.project.MenuPrincipale;

import com.unisa.software_engineering.project.MenuContatto.ControllerMenuContatto;
import com.unisa.software_engineering.project.Model.Contatto;
import com.unisa.software_engineering.project.Model.Rubrica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Node;

import java.io.IOException;
import java.util.List;

public class ControllerMenuPrincipale {

    private TableView<Contatto> tableViewContatti;
    @FXML
    private TableColumn<Contatto, String> nomeCln;
    @FXML
    private TableColumn<Contatto, String> cognomeCln;
    @FXML
    private TextField barraRicerca;  // Barra di ricerca
    @FXML
    private MenuItem esportaBtn;
    @FXML
    private MenuItem eliminaBtn;
    @FXML
    private Button aggiungiBtn;
    @FXML
    private Button importaBtn;

    private ObservableList<Contatto> contattiList;  // Lista fisica dei contatti
    private ObservableList<Contatto> filteredContattiList;  // Lista filtrata dei contatti


    // Metodo che viene chiamato per passare la rubrica al controller
    public void setRubrica(Rubrica rubrica) {
        // Trasforma la lista fisica della rubrica in una ObservableList
        contattiList = FXCollections.observableArrayList(rubrica.getContatti());
        filteredContattiList = FXCollections.observableArrayList(contattiList);

        // Collega la ObservableList alla TableView
        tableViewContatti.setItems(filteredContattiList);

        // Imposta le colonne della TableView
        nomeCln.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        // Abilita la selezione multipla nella TableView
        tableViewContatti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Aggiungi listener per la barra di ricerca
        barraRicerca.textProperty().addListener((observable, oldValue, newValue) -> {
            filtraContatti(newValue);
        });

        // Menu contestuale per selezione multipla
        MenuItem esportaItem = new MenuItem("Esporta contatti");
        MenuItem eliminaItem = new MenuItem("Elimina contatti");

        // Aggiungi le azioni del menu contestuale
        esportaItem.setOnAction(e -> esportaContatti());
        eliminaItem.setOnAction(e -> eliminaContatti());

        // Crea un menu contestuale
        ContextMenu contextMenu = new ContextMenu(esportaItem, eliminaItem);
        tableViewContatti.setContextMenu(contextMenu);
    }

    // Metodo di ricerca che filtra la lista dei contatti
    private void filtraContatti(String query) {
        filteredContattiList.clear();
        if (query.isEmpty()) {
            filteredContattiList.addAll(contattiList);
        } else {
            for (Contatto contatto : contattiList) {
                // Confronta il nome e cognome del contatto con la query (ignorando maiuscole/minuscole)
                if (contatto.getNome().toLowerCase().contains(query.toLowerCase()) ||
                    contatto.getCognome().toLowerCase().contains(query.toLowerCase())) {
                    filteredContattiList.add(contatto);
                }
            }
        }
    }

    // Metodo per esportare i contatti selezionati
    private void esportaContatti() {
        List<Contatto> contattiSelezionati = tableViewContatti.getSelectionModel().getSelectedItems();
        // Logica di esportazione (ad esempio, scrivere su un file CSV, JSON, ecc.)
        System.out.println("Esporta contatti: " + contattiSelezionati);
        // Esportazione dei contatti
    }

    // Metodo per eliminare i contatti selezionati
    private void eliminaContatti() {
        List<Contatto> contattiSelezionati = tableViewContatti.getSelectionModel().getSelectedItems();
        contattiList.removeAll(contattiSelezionati);
        filteredContattiList.removeAll(contattiSelezionati);
        // Logica di eliminazione (ad esempio, eliminare anche dal file)
        System.out.println("Elimina contatti: " + contattiSelezionati);
    }

    // Metodo per aprire la schermata di aggiunta di un nuovo contatto
    @FXML
    private void aggiungiContatto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuContatto.fxml"));
        Parent root = loader.load();

        // Passa la rubrica al controller della schermata contatto
        ControllerMenuContatto controller = loader.getController();
        controller.setContatto(null);         // Modalità "Aggiungi" (senza contatto selezionato)

        // Carica la nuova scena
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Metodo per gestire il doppio clic su un contatto per visualizzarlo
    private void onTableViewItemDoubleClick(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Contatto contattoSelezionato = tableViewContatti.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuContatto.fxml"));
            Parent root = loader.load();

            // Passa la rubrica e il contatto selezionato al controller della schermata contatto
            ControllerMenuContatto controller = loader.getController();
            controller.setRubrica(contattiList);  // Passa la lista dei contatti
            controller.setContatto(contattoSelezionato);  // Modalità "Visualizza" con i dati pre-compilati

            // Carica la scena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void esportaContatto(ActionEvent event) {
    }

    @FXML
    private void eliminaContatto(ActionEvent event) {
    }

    @FXML
    private void importaContatto(ActionEvent event) {
    }
}
