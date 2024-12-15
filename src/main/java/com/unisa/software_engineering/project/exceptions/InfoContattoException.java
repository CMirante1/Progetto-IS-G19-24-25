/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.exceptions;

/**
 * @file InfoContattoException.java
 * @class RubricaExceptions
 * @brief L'eccezione lanciata dai componenti della rubrica
 *
 * Questa è l'eccezione generica, superclasse di tutte le eccezioni legate alla gestione della rubrica
 *
 */
public class InfoContattoException extends Exception {

    public InfoContattoException(String msg) {

        super(msg);
    }
}
