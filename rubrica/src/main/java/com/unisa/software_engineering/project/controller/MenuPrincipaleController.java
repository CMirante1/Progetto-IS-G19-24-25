package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.model.Contatto;
import com.unisa.software_engineering.project.model.ContattoV2;
import com.unisa.software_engineering.project.model.FileManager;
import com.unisa.software_engineering.project.model.Rubrica;
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
    private ObservableList<ContattoV2> listaContatti;
    private ObservableList<ContattoV2> listaContattiFiltrati;

    private MenuContattoView mcView;
    private MenuContattoController mcController;
    private Scene menuContatto;

    public MenuPrincipaleController(Rubrica rubrica, MenuPrincipaleView mpView, Scene menuPrincipale) {

        this.rubrica = rubrica;
        this.mpView = mpView;

        mcView = new MenuContattoView();
        mcController = new MenuContattoController(mcView, menuPrincipale);
        menuContatto = new Scene(mcView, 750, 600);

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        listaContatti = FXCollections.observableArrayList(rubrica.getContatti());
        listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);

        mpView.getTabellaContatti().setItems(listaContatti);
        mpView.getTabellaContatti().setOnMouseClicked(event -> visualizzaContatto(event));

        mpView.getBarraRicerca();

        mpView.getAggiungiBtn().setOnAction(event -> aggiungiContatto(event));

        mpView.getImportaBtn().setOnAction((event) -> importaContatto(event));

        mpView.getEliminaBtn().setOnAction(event -> eliminaContatto());

        mpView.getEsportaBtn().setOnAction(event -> esportaContatto(event));
    }

    private void esportaContatto(ActionEvent event) {

        List<ContattoV2> contattiSelezionati = mpView.getTabellaContatti().getSelectionModel().getSelectedItems();

        if(contattiSelezionati.size() == 0) return;

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        if(!file.getName().endsWith(".vcf"))
            file = new File(file.getAbsolutePath() + ".vcf");

        FileManager.esportaContatti(contattiSelezionati, file);
    }

    private void eliminaContatto() {

        ContattoV2 contattoSelezionato = mpView.getTabellaContatti().getSelectionModel().getSelectedItem();
        listaContatti.remove(contattoSelezionato);
    }

    //metodo completato
    private void importaContatto(ActionEvent event) {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        File file = fileChooser.showOpenDialog(stage);

        if(file == null) return;
        FileManager.importaContatti(file);
    }

    //metodo completato
    private void visualizzaContatto(MouseEvent event) {

        if(event.getClickCount() == 2) {

            ContattoV2 contatto = mpView.getTabellaContatti().getSelectionModel().getSelectedItem();
            mcController.setContatto(contatto);

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        }
    }

    //metodo completato
    private void aggiungiContatto(ActionEvent event) {

        ContattoV2 contatto = null;
        mcController.setContatto(contatto);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuContatto);
    }

//    public void nuovoContatto(ContattoV2 contatto) {
//
//
//    }
}
