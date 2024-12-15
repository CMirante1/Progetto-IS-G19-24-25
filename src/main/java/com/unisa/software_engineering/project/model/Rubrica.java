package com.unisa.software_engineering.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @file Rubrica.java
 * @class Rubrica
 * @brief La struttura dati Rubrica
 *
 * Definisce la struttura dati della rubrica che contiene i contatti dell'utente.
 *
 * @ingroup Models
 * @see Contatto
 * @see FileManager
 */
public class Rubrica implements Serializable {

    private List<Contatto> contatti;

    /**
     * @constructor
     *
     */
    public Rubrica(){
        contatti = new ArrayList<>();
    }

    /**
     * @brief Aggiunge un contatto alla rubrica
     *
     * @param contatto Il contatto già verificato
     */
    public void aggiungiContatto(Contatto contatto){

        contatti.add(contatto);
        contatti.sort(null);
    }

    /**
     * @brief Rimuove un contatto dalla rubrica
     *
     * @param contatto Il contatto da rimuovere
     */
    public void rimuoviContatto(Contatto contatto){

        contatti.remove(contatto);
    }
    /**
     *
     * @brief Rimuove un contatto dalla rubrica
     * @return contatti ritorna il riferimento alla lista di contatti
     */
    public List<Contatto> getContatti(){
        return contatti; }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("Rubrica:\n");

        for(Contatto contatto : contatti) sb.append(contatto + "\n");

        return sb.toString();
    }
 }
