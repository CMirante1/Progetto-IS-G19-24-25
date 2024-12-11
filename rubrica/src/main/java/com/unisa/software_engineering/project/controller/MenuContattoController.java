package com.unisa.software_engineering.project.controller;

import com.unisa.software_engineering.project.model.ContattoV2;
import com.unisa.software_engineering.project.view.MenuContattoView;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;;

public class MenuContattoController {

    private MenuContattoView mcView;
    private Scene menuPrincipale;
    private ContattoV2 contatto;

    public MenuContattoController(MenuContattoView mcView, Scene menuPrincipale) {

        this.mcView = mcView;
        this.menuPrincipale = menuPrincipale;

        inizializzaComponenti();
    }

    private void inizializzaComponenti() {

        mcView.getEscBtn().setOnAction(event -> tornaIndietro(event));
    }

    public void tornaIndietro(ActionEvent event) {

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(menuPrincipale);
    }

    public void setContatto(ContattoV2 contatto) {

        this.contatto = contatto;
        System.out.println(contatto);
    }
}
