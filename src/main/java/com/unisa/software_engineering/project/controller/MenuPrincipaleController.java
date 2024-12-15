package com.unisa.software_engineering.project.controller;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import com.unisa.software_engineering.project.model.*;
import com.unisa.software_engineering.project.view.MenuContattoView;
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import java.util.prefs.*;

/**
 * @file MenuPrincipaleController.java
 * @class MenuPrincipaleController
 * @brief Gestisce le azioni dell'interfaccia grafica e l'interazione con il la Rubrica.
 */
public class MenuPrincipaleController {

    private final MenuPrincipaleView mpView;
    private final Scene mpScene;
    private final Rubrica rubrica;
    private ObservableList<Contatto> listaContatti;
    private ImageView immagineTemaScuro;
    private ImageView immagineTemaChiaro;

    private final Stage stage;
    private final MenuContattoView mcView;
    private final MenuContattoController mcController;
    private final Scene menuContatto;
    /**
     * @brief Costruttore della del controller del menu principale.
     * @param rubrica La rubrica su cui operare.
     * @param stage La finestra principale dell'applicazione.
     * @param mpView La view del menu principale.
     * @param menuPrincipale La scena principale per tornare indietro.
     */
    public MenuPrincipaleController(Rubrica rubrica, Stage stage,  MenuPrincipaleView mpView, Scene mpScene) {

        this.rubrica = rubrica;
        this.mpView = mpView;
        this.stage = stage;
        this.mpScene = mpScene;

        mcView = new MenuContattoView();

        menuContatto = new Scene(mcView, 750, 550);
        //cambiaTema();

        mcController = new MenuContattoController(this, mcView, mpScene);

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());

        //riempimento tabella contatti e definizione logica visualizzazione contatto
        mpView.getTabellaContatti().setItems(listaContatti);
        mpView.getTabellaContatti().setOnMouseClicked(event -> visualizzaContatto(event));

        //definizione logica barra di ricerca
        mpView.getBarraRicerca().textProperty().addListener((observable, vecchiaStringa, nuovaStringa) -> {

            ObservableList<Contatto> listaContattiFiltrati = FXCollections.observableArrayList();

            for(Contatto contatto : listaContatti) {

                if(contatto.getNome().toLowerCase().contains(nuovaStringa.toLowerCase()) ||
                    contatto.getCognome().toLowerCase().contains(nuovaStringa.toLowerCase()))

                    listaContattiFiltrati.add(contatto);
            }

            if(mpView.getBarraRicerca().getText().isEmpty())
                mpView.getTabellaContatti().setItems(listaContatti);
            else mpView.getTabellaContatti().setItems(listaContattiFiltrati);
        });

        //definizione logica pulsante nuovo contatto
        mpView.getAggiungiBtn().setOnAction(event -> aggiungiContatto());

        //definizione logica pulsante eliminazione contatti
        mpView.getEliminaBtn().setOnAction(event -> eliminaContatto());

        //definizione logica pulsante importazione contatti
        mpView.getImportaBtn().setOnAction(event -> importaContatto());

        //definizione logica pulsante esportazione contatti
        mpView.getEsportaBtn().setOnAction(event -> esportaContatto());

        mpView.getTemaBtn().setOnAction(event -> cambiaTema(mpView.getTemaBtn()));
    }

    private void aggiungiContatto() {

        Contatto contatto = null;
        mcController.setContatto(contatto);

        stage.setScene(menuContatto);
    }

    private void eliminaContatto() {

        List<Contatto> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.isEmpty()) return;

        Alert conferma = new Alert(Alert.AlertType.CONFIRMATION);
        conferma.setTitle("Conferma eliminazione contatti.");
        conferma.setContentText("Sicuro di voler eliminare i contatti selezionati?");

        Optional<ButtonType> scelta = conferma.showAndWait();
        if(!scelta.isPresent() || scelta.get() != ButtonType.OK) return;

        for(Contatto contatto : contattiSelezionati) {

            rubrica.rimuoviContatto(contatto);
        }

        mpView.getTabellaContatti().getItems().removeAll(mpView.getTabellaContatti().getSelectionModel().getSelectedItems());
    }
    /**
     * @brief Permette di visualizzare un contatto visualizzato
     * Quando l'utente seleziona un contatto il menuPrincipale passa al menuContatto il contatto da visualizzare e chiama la nuova finestra
     *
     */
    private void visualizzaContatto(MouseEvent event) {

        if(event.getClickCount() != 2) return;

        if (event.getTarget() instanceof TableColumnHeader) return;

        Contatto contatto = mpView.getTabellaContatti().getSelectionModel().getSelectedItem();

        if(contatto == null) return;

        TableCell<Contatto, String> cell = (TableCell<Contatto, String>) event.getTarget();
        TableRow<Contatto> riga = cell.getTableRow();

        if (riga == null || riga.getIndex() != mpView.getTabellaContatti().getSelectionModel().getSelectedIndex()) return;

        mcController.setContatto(contatto);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuContatto);

        rubrica.getContatti().sort(null);
        listaContatti.sort(null);
        mpView.getTabellaContatti().refresh();
    }
    /**
     * @brief invia i file selezionati al metodo importaContatti della classe fileManager.
     * @see FileManager
     */
    private void importaContatto() {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showOpenDialog(stage);
        int contattiNonImportati = 0;

        if(file == null) return;

        try {

            contattiNonImportati = FileManager.importaContatti(file, rubrica);
        } catch (IOException e) {

            System.out.println("Errore nella lettura del file!");
        }

        rubrica.getContatti().sort(null);
        listaContatti.clear();
        listaContatti.addAll(rubrica.getContatti());

        String msg = "";

        if(contattiNonImportati == 0) msg = "Tutti i contatti sono stati importati";
        else if(contattiNonImportati == 1) msg = "1 contatto non è stato importato";
        else if(contattiNonImportati > 1) msg = contattiNonImportati + " contatti non sono stati importati";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Importazione contatti");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    /**
     * @brief invia i contatti selezionati al metodo esportaContatti della classe fileManager.
     * @see FileManager
     *
     */
    private void esportaContatto() {

        List<Contatto> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.isEmpty()) return;

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);

        if(file == null) return;

        if(!file.getName().endsWith(".vcf"))
            file = new File(file.getAbsolutePath() + ".vcf");

        try {

            FileManager.esportaContatti(contattiSelezionati, file);
        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Esportazione contatti");
            alert.setContentText("Errore nell'esportazione dei contatti!");
            alert.showAndWait();
        }
    }

    private void cambiaTema(Button btn){
        Preferences prefs = Preferences.userNodeForPackage(MenuPrincipaleController.class);
        Boolean newVal = !prefs.getBoolean("TemaScuro", false);
        prefs.putBoolean("TemaScuro", newVal);
        if (newVal.booleanValue()){
            btn.setGraphic(immagineTemaScuro);
            mpScene.getStylesheets().add("darkTheme.css");
            menuContatto.getStylesheets().add("darkTheme.css");
        } else {
            btn.setGraphic(immagineTemaChiaro);
            mpScene.getStylesheets().add("lightTheme.css");
            menuContatto.getStylesheets().add("lightTheme.css");
        }
    }

     /**
     * @brief Aggiunge un contatto alla rubrica e aggiorna la vista.
     * @param contatto Il nuovo contatto da aggiungere.
     */
    public void passaNuovoContatto(Contatto contatto) {

        rubrica.aggiungiContatto(contatto);

        listaContatti.add(contatto);
        listaContatti.sort(null);
    }
}
