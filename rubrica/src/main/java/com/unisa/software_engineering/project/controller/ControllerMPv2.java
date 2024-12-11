package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.model.ContattoV2;
import com.unisa.software_engineering.project.model.Rubrica;
import com.unisa.software_engineering.project.view.MenuPrincipaleView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class ControllerMPv2 {

    private MenuPrincipaleView mpView;
    private Rubrica rubrica;
    private ObservableList<ContattoV2> listaContatti;
    private ObservableList<ContattoV2> listaContattiFiltrati;

    public ControllerMPv2(Rubrica rubrica, MenuPrincipaleView mpView) {

        this.rubrica = rubrica;
        this.mpView = mpView;
        this.listaContatti = FXCollections.observableArrayList(rubrica.getContatti());
        this.listaContattiFiltrati = FXCollections.observableArrayList(listaContatti);
        mpView.getTabellaContatti().setItems(listaContatti);
    }

    public void aggiungiContatto(ActionEvent event) {


    }
}
