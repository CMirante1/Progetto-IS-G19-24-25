/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.View.MenuContattoView;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author paolo
 */
public class ControllerMenuContatto implements Initializable {
    
    @FXML
    private TextField nomeTXF;
    @FXML
    private TextField cognomeTXF;
    @FXML
    private TextField cell2TXF;
    @FXML
    private TextField cell3TXF;
    @FXML
    private TextField cell1TXF;
    @FXML
    private TextField email1TXF;
    @FXML
    private TextField email2TXF;
    @FXML
    private TextField email3TXF;
    @FXML
    private Label nomeLBL;
    @FXML
    private Label cognomeLBL;
    @FXML
    private Label cell1LBL;
    @FXML
    private Label cell2LBL;
    @FXML
    private Label cell3LBL;
    @FXML
    private Label email1LBL;
    @FXML
    private Label email2LBL;
    @FXML
    private Label email3LBL;
    @FXML
    private ImageView imageField;
    @FXML
    private Button moficicaButton;
    @FXML
    private Button salvaButton;
    @FXML
    private Button BackButton;
    @FXML
    private ImageView backIage;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
