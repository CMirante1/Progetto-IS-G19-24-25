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
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
/**
 * @file MenuPrincipaleController.java
 * @class MenuPrincipaleController
 * @brief Gestisce le azioni dell'interfaccia grafica e l'interazione con il la Rubrica.
 */
public class MenuPrincipaleController {

    private final MenuPrincipaleView mpView;
    private final Rubrica rubrica;
    private ObservableList<Contatto> listaContatti;

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
    public MenuPrincipaleController(Rubrica rubrica, Stage stage,  MenuPrincipaleView mpView, Scene menuPrincipale) {

        this.rubrica = rubrica;
        this.mpView = mpView;
        this.stage = stage;

        mcView = new MenuContattoView();
        mcController = new MenuContattoController(this, mcView, menuPrincipale);
        menuContatto = new Scene(mcView, 750, 600);

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());

        mpView.getTabellaContatti().setItems(listaContatti);
        mpView.getTabellaContatti().setOnMouseClicked(event -> visualizzaContatto(event));

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

        mpView.getAggiungiBtn().setOnAction(event -> aggiungiContatto());

        mpView.getImportaBtn().setOnAction((event) -> importaContatto());

        mpView.getEliminaBtn().setOnAction(event -> eliminaContatto());

        mpView.getEsportaBtn().setOnAction(event -> esportaContatto());
    }

    private void aggiungiContatto() {

        Contatto contatto = null;
        mcController.setContatto(contatto);

        stage.setScene(menuContatto);
    }

    private void eliminaContatto() {

        List<Contatto> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.isEmpty()) return;

        for(Contatto contatto : contattiSelezionati) {

            rubrica.rimuoviContatto(contatto);
        }

        mpView.getTabellaContatti().getItems().removeAll(mpView.getTabellaContatti().getSelectionModel().getSelectedItems());
    }

    private void visualizzaContatto(MouseEvent event) {

        if(event.getClickCount() != 2) return;

        if (event.getTarget() instanceof TableColumnHeader) return;

        Contatto contatto = mpView.getTabellaContatti().getSelectionModel().getSelectedItem();
        mcController.setContatto(contatto);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuContatto);

        rubrica.getContatti().sort(null);
        listaContatti.sort(null);
        mpView.getTabellaContatti().refresh();
    }

    private void importaContatto() {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showOpenDialog(stage);
        int contattiImportati = 0;

        if(file == null) return;

        try {

            contattiImportati = FileManager.importaContatti(file, rubrica);
        } catch (IOException e) {

            System.out.println("Errore nella lettura del file!");
        }

        rubrica.getContatti().sort(null);
        listaContatti.clear();
        listaContatti.addAll(rubrica.getContatti());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Sono stati importati " + contattiImportati + " contatti.");
        alert.showAndWait();
    }

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
            alert.setContentText("Errore nell'esportazione dei contatti!");
            alert.showAndWait();
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
