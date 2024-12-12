package com.unisa.software_engineering.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @class Rubrica
 * @brief La struttura dati Rubrica
 *
 * Questa è la struttura dati della rubrica che contiene i contatti dell'utente.
 *
 * @ingroup Models
 * @see Contatto
 * @see FileManager
 * @author paolo
 * @date 02/12/24
 * @todo Tutto, manca tutto
 */
public class Rubrica implements Serializable {

    private List<ContattoV3> contatti;

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
    public void aggiungiContatto(ContattoV3 contatto){

        contatti.add(contatto);
    }

    /**
     * @brief Rimuove un contatto dalla rubrica
     *
     * @param contatto Il contatto da rimuovere
     */
    public void rimuoviContatto(ContattoV3 contatto){

        if(contatti.contains(contatto)) contatti.remove(contatto);

    }
    /**
     * 
     * @brief Rimuove un contatto dalla rubrica
     * @return contatti ritorna il riferimento alla lista di contatti
     */
    public List<ContattoV3> getContatti(){
        return contatti; }
 }
