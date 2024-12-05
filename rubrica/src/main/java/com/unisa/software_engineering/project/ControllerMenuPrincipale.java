/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.unisa.software_engineering.project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class ControllerMenuPrincipale implements Initializable {

    @FXML
    private TableView<?> tableView;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem exportMenuItem;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleDelete(ActionEvent event) {
    }

    @FXML
    private void handleExport(ActionEvent event) {
    }
    
}
