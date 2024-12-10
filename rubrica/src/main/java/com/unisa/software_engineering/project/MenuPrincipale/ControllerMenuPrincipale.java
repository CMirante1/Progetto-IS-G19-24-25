package com.unisa.software_engineering.project.MenuPrincipale;
/**
 * @class ControllerPrincipale
 * @brief Gestisce la finestra di visualizzazione del menu principale
 *
 * Quando l'utente lancerà il software sarà la prima finestra a visualizzare
 *
 * @ingroup MenuPrincipale
 * @author grouppo_19
 * @date 06/12/24
 */

import com.unisa.software_engineering.project.MenuContatto.ControllerMenuContatto;
import com.unisa.software_engineering.project.Model.Contatto;
import com.unisa.software_engineering.project.Model.FileManager;
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

    private Rubrica rubrica;
    private ObservableList<Contatto> listaContatti;  // Lista fisica dei contatti
    private ObservableList<Contatto> listaContattiFiltrati;  // Lista filtrata dei contatti

    /**
     * @brief Metodo che passa al controller la Rubrica
     * Quando si lancia il software al controller verrà passata la rubrica salvata in memoria
     * @param rubrica Riferimento alla rubrica salvata in memoria
     */
    public void setRubrica(Rubrica rubrica) {
        // Trasforma la lista fisica della rubrica in una ObservableList
        this.rubrica = rubrica;
        inizializzaComponenti();
    }

    public void setContatto(Contatto contatto) {

        if(contatto != null)
            rubrica.getContatti().add(contatto);
    }


   /// Metodo per inizializzare tutti i componenti della finestra menu Principale
    private void inizializzaComponenti() {

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());
        listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);

        // Collega la ObservableList alla TableView
        tableViewContatti.setItems(listaContattiFiltrati);

        // Imposta le colonne della TableView
        nomeCln.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));

        // Abilita la selezione multipla nella TableView
        tableViewContatti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//        // Aggiungi listener per la barra di ricerca
//        barraRicerca.textProperty().addListener((observable, oldValue, newValue) -> {
//            filtraContatti(newValue);
//        });

        // Menu contestuale per selezione multipla
        MenuItem esportaItem = new MenuItem("Esporta contatti");
        MenuItem eliminaItem = new MenuItem("Elimina contatti");
//
//        // Aggiungi le azioni del menu contestuale
//        esportaItem.setOnAction(e -> esportaContatti());
//        eliminaItem.setOnAction(e -> eliminaContatti());

        // Crea un menu contestuale
        ContextMenu contextMenu = new ContextMenu(esportaItem, eliminaItem);
        tableViewContatti.setContextMenu(contextMenu);
    }

   /* /// Metodo di ricerca che filtra la lista dei contatti
    private void filtraContatti(String query);
*/
    /// Metodo per aprire la schermata di aggiunta di un nuovo contatto
    @FXML
    private void aggiungiContatto(ActionEvent event) throws IOException {

        Contatto nuovoContatto = null;
       // rubrica.aggiungiContatto(menucontatto.);
        cambiaSchermata(event, nuovoContatto);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuContatto.fxml"));
//        Parent root = loader.load();
//
//        // Passa la rubrica al controller della schermata contatto
//        ControllerMenuContatto controllerMenuContatto = loader.getController();
//
//        controllerMenuContatto.setRubrica(rubrica);
//        controllerMenuContatto.setContatto(contatto);         // Modalità "Aggiungi" (senza contatto selezionato)
//
//        // Carica la nuova scena
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
        }
/*
    /// Metodo per gestire il doppio clic su un contatto per visualizzarlo
    private void visualizzaContatto(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            Contatto contattoSelezionato = tableViewContatti.getSelectionModel().getSelectedItem();
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuContatto.fxml"));
//            Parent root = loader.load();
//
//            // Passa la rubrica e il contatto selezionato al controller della schermata contatto
//            ControllerMenuContatto controllerMenuContatto = loader.getController();
//
//            controllerMenuContatto.setRubrica(rubrica);  // Passa la lista dei contatti
//            controllerMenuContatto.setContatto(contattoSelezionato);  // Modalità "Visualizza" con i dati pre-compilati
//
//            // Carica la scena
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();

            int index = listaContatti.indexOf(contatto);

            if (index != -1) {

                listaContatti.add(contatto);
                rubrica.aggiungiContatto(contatto);
            }
        }
    }
*/
    private void cambiaSchermata(ActionEvent event, Contatto contatto) {

            try {
                // Carica il file FXML
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MenuContatto.fxml"));

                if (fxmlLoader == null) {
                    System.out.println("Errore: FXMLLoader non è stato creato correttamente.");
                } else {
                    System.out.println("FXMLLoader creato correttamente.");
                }

                // Carica la vista dalla risorsa FXML
                Parent root = fxmlLoader.load();
                if (root == null) {
                    System.out.println("Errore: root è null, il file FXML potrebbe non essere stato caricato correttamente.");
                } else {
                    System.out.println("root caricato correttamente.");
                }

                // Ottieni il controller del file FXML
                ControllerMenuContatto controllerMenuContatto = fxmlLoader.getController();
                if (controllerMenuContatto == null) {
                    System.out.println("Errore: il controller non è stato caricato correttamente.");
                } else {
                    System.out.println("Controller caricato correttamente.");
                }

                // Passa il contatto al controller (se necessario)
                controllerMenuContatto.setContatto(contatto);

                // Crea la scena con il root caricato
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                if (stage == null) {
                    System.out.println("Errore: Stage è null.");
                } else {
                    System.out.println("Stage trovato correttamente.");
                }

                // Crea e setta la scena
                Scene scena = new Scene(root);
                if (scena == null) {
                    System.out.println("Errore: la scena è null.");
                } else {
                    System.out.println("Scena creata correttamente.");
                }

                // Setta la scena al palco e mostralo
                stage.setScene(scena);
                stage.show();
            } catch (IOException e) {
                System.out.println("Errore durante il caricamento del file FXML: " + e.getMessage());
                System.out.println("Causa: " + e.getCause());
            }
        }


    @FXML
    private void esportaContatti(ActionEvent event) {


    }

    @FXML
    private void eliminaContatti(ActionEvent event) {
    }

    @FXML
    private void importaContatti(ActionEvent event) {


//        List<Contatto> contattiSelezionati = tableViewContatti.getSelectionModel().getSelectedItems();
//        FileManager.importaContatti(contattiSelezionati);
//        listaContatti.clear();
//        listaContatti.addAll(rubrica.getContatti());
    }
  }
