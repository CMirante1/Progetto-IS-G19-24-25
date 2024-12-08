/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.Exceptions;

/**
 * @class RubricaExceptions
 * @brief L'eccezione lanciata dai componenti della rubrica
 *
 * Questa è l'eccezione generica, superclasse di tutte le eccezioni legate alla gestione della rubrica
 *
 * @ingroup Exceptions
 * @todo Creare le sottoclassi exception
 * @author paolo
 * @date 06/12/24
 */
public class ContattoException extends Exception {

    public ContattoException(String msg) {

        super(msg);
    }
}
