package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.model.*;
import com.unisa.software_engineering.project.view.MenuContattoView;
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class MenuPrincipaleController {

    private MenuPrincipaleView mpView;
    private Rubrica rubrica;
    private ObservableList<ContattoV3> listaContatti;

    private Stage stage;
    private MenuContattoView mcView;
    private MenuContattoController mcController;
    private Scene menuContatto;

    public MenuPrincipaleController(Rubrica rubrica, Stage stage,  MenuPrincipaleView mpView, Scene menuPrincipale) {

        this.rubrica = rubrica;
        this.mpView = mpView;
        this.stage = stage;

        mcView = new MenuContattoView();
        mcController = new MenuContattoController(mcView, menuPrincipale);
        menuContatto = new Scene(mcView, 750, 600);

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());

        mpView.getTabellaContatti().setItems(listaContatti);
        mpView.getTabellaContatti().setOnMouseClicked(event -> visualizzaContatto(event));

        mpView.getBarraRicerca().textProperty().addListener((observable, vecchiaStringa, nuovaStringa) -> {

            ObservableList<ContattoV3> listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);

            for(ContattoV3 contatto : listaContatti) {

                if(contatto.getNome().toLowerCase().contains(nuovaStringa.toLowerCase()) ||
                    contatto.getCognome().toLowerCase().contains(nuovaStringa.toLowerCase()))

                    listaContattiFiltrati.add(contatto);
            }

            mpView.getTabellaContatti().setItems(listaContattiFiltrati);

            if(mpView.getBarraRicerca().getText().isEmpty())
                mpView.getTabellaContatti().setItems(listaContatti);
        });

        mpView.getAggiungiBtn().setOnAction(event -> aggiungiContatto(event));

        mpView.getImportaBtn().setOnAction((event) -> importaContatto(event));

        mpView.getEliminaBtn().setOnAction(event -> eliminaContatto());

        mpView.getEsportaBtn().setOnAction(event -> esportaContatto(event));
    }

    private void esportaContatto(ActionEvent event) {

        List<ContattoV3> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.isEmpty()) return;

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        if(!file.getName().endsWith(".vcf"))
            file = new File(file.getAbsolutePath() + ".vcf");

        FileManager.esportaContatti(contattiSelezionati, file);
    }

    private void eliminaContatto() {

        List<ContattoV3> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.isEmpty()) return;

        for(ContattoV3 contattoV3 : contattiSelezionati) {

            rubrica.rimuoviContatto(contattoV3);
            listaContatti.remove(contattoV3);
        }

        FileManager.salvaRubrica(rubrica);
    }

    //metodo completato
    private void importaContatto(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showOpenDialog(stage);

        if(file == null) return;

        FileManager.importaContatti(file, rubrica);

        listaContatti.clear();
        listaContatti.addAll(rubrica.getContatti());

        FileManager.salvaRubrica(rubrica);
    }

    //metodo completato
    private void visualizzaContatto(MouseEvent event) {

        if(event.getClickCount() == 2) {

            ContattoV3 contatto = mpView.getTabellaContatti().getSelectionModel().getSelectedItem();
            mcController.setContatto(contatto);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        }
    }

    //metodo completato
    private void aggiungiContatto(ActionEvent event) {

        ContattoV3 contatto = null;
        mcController.setContatto(contatto);

        stage.setScene(menuContatto);
    }

    public void passaNuovoContatto(ContattoV3 contatto) {

        rubrica.aggiungiContatto(contatto);

        listaContatti.add(contatto);

        FileManager.salvaRubrica(rubrica);
    }
}
