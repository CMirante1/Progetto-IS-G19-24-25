package com.unisa.software_engineering.project.controller;
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

import com.unisa.software_engineering.project.model.ContattoV2;
import com.unisa.software_engineering.project.model.Rubrica;
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

public class ControllerMenuPrincipale{

    @FXML
    private TableView<ContattoV2> tabellaContatti;
    @FXML
    private TableColumn<ContattoV2, String> cognomeCln;
    @FXML
    private TableColumn<ContattoV2, String> nomeCln;
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

    private static Rubrica rubrica;
    private  ObservableList<ContattoV2> listaContatti;  // Lista fisica dei contatti
    private  ObservableList<ContattoV2> listaContattiFiltrati;  // Lista filtrata dei contatti

    public ControllerMenuPrincipale(Rubrica rubrica) {

        this.rubrica = rubrica;
        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        System.out.println("Sono in initialize");

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());

        listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);
        // Collega la ObservableList alla TableView

        // Imposta le colonne della TableView
        //cognomeCln = new TableColumn<>();

        if(cognomeCln == null) System.out.println("cognCln null");
        else {
            System.out.println("colonna cognome ok");
            cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));
        }
        // nomeCln = new TableColumn<>();
        if(nomeCln == null) System.out.println("nome null");
        else {
            System.out.println("colonna nome ok");
            nomeCln.setCellValueFactory(new PropertyValueFactory<>("nome"));
        }

        if(tabellaContatti == null) System.out.println("tabella null");
        else System.out.println("tabella ok");
    }
//
//    private void inizializzaFXML() {
//
//        FXMLLoader loader=new FXMLLoader();
//        loader.setController(this);
//        try {
//            loader.setLocation(new File("MenuPrincipale.fxml").toURL());
//            loader.load();
//        } catch (IOException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//    }
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//
//        System.out.println("Sono in initialize");
//
//        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());
//
//        listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);
//        // Collega la ObservableList alla TableView
//
//        // Imposta le colonne della TableView
//        //cognomeCln = new TableColumn<>();
//
//        if(cognomeCln == null) System.out.println("cognCln null");
//        else {
//            System.out.println("colonna cognome ok");
//        cognomeCln.setCellValueFactory(new PropertyValueFactory<>("cognome"));
//        }
//        // nomeCln = new TableColumn<>();
//        if(nomeCln == null) System.out.println("nome null");
//        else {
//            System.out.println("colonna nome ok");
//            nomeCln.setCellValueFactory(new PropertyValueFactory<>("nome"));
//        }
//
////        if(tabellaContatti == null) System.out.println("tabella null");
////        else System.out.println("tabella ok");
//
//       //tabellaContatti.setItems(listaContattiFiltrati);
//
//        // Abilita la selezione multipla nella TableView
//     //   tabellaContatti.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        //  tabellaContatti.setOnMouseClicked(event -> visualizzaContatto(event));
////        // Aggiungi listener per la barra di ricerca
////        barraRicerca.textProperty().addListener((observable, oldValue, newValue) -> {
////            filtraContatti(newValue);
////        });
//
//        // Menu contestuale per selezione multipla
//        MenuItem esportaItem = new MenuItem("Esporta contatti");
//        MenuItem eliminaItem = new MenuItem("Elimina contatti");
////
////        // Aggiungi le azioni del menu contestuale
////        esportaItem.setOnAction(e -> esportaContatti());
////        eliminaItem.setOnAction(e -> eliminaContatti());
//
//        // Crea un menu contestuale
//        ContextMenu contextMenu = new ContextMenu(esportaItem, eliminaItem);
//        //tabellaContatti.setContextMenu(contextMenu);
//    }
////
////    /**
////     * @brief Metodo che passa al controller la Rubrica
////     * Quando si lancia il software al controller verrà passata la rubrica salvata in memoria
////     * @param rubrica Riferimento alla rubrica salvata in memoria
////     */
////    public void setRubrica(Rubrica rubrica) {
////
////        this.rubrica = rubrica;
////
////        listaContatti.addAll(rubrica.getContatti());
////       // tabellaContatti.setItems(listaContatti);
////    }

    public void setContatto(ContattoV2 contatto) {

        if(contatto != null) {

            listaContatti.add(contatto);
        }
    }

   /* /// Metodo di ricerca che filtra la lista dei contatti
    private void filtraContatti(String query);
*/
    /// Metodo per aprire la schermata di aggiunta di un nuovo contatto
    @FXML
    private void aggiungiContatto(ActionEvent event) throws IOException {

        ContattoV2 nuovoContatto = null;

        cambiaSchermata(event, nuovoContatto);
    }

    /// Metodo per gestire il doppio clic su un contatto per visualizzarlo
    private void visualizzaContatto(MouseEvent event) {
        if (event.getClickCount() == 2) System.out.println("doppio click");//{
 /*           Contatto contattoSelezionato = tableViewContatti.getSelectionModel().getSelectedItem();
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
        }*/
    }

    private void cambiaSchermata(ActionEvent event, ContattoV2 contatto) {

        try {
            // Carica il file FXML
            FXMLLoader fxmlLoader = new FXMLLoader(ControllerMenuContatto.class.getResource("MenuContatto.fxml"));

            Parent root = fxmlLoader.load();

            ControllerMenuContatto controllerMenuContatto = fxmlLoader.getController();

            controllerMenuContatto.setContatto(contatto);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Crea e setta la scena
            Scene scena = new Scene(root);

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
