package com.unisa.software_engineering.project.Model;

import java.util.List;

/**
 * @class FileManager
 * @brief Carica, salva, importa e esporta
 * 
 * Si occupa del caricamento iniziale della rubrica, il salvataggio in memoria e l'esportazione e 
 * importazione di contatti sempre sottoforma di rubrica.
 * 
 * @ingroup Models
 * @see Rubrica.java
 * @todo Scegliere se l'importazione supporta anche i singoli contatti
 * @todo Scegliere se la rubrica va passata da parametro o è unica e statica
 * @author andre
 * @date 05/12/24
 */
public abstract class FileManager {
    
    private static final String FILE_BACKUP = "rubrica.dat";
    
    /**
     * @brief Salva la rubrica sulla memoria di massa
     * 
     * Serializza la rubrica e i contatti al suo interno e la salva ad un path predefinito
     * 
     * @param rubrica La rubrica da salvare
     */
    public static void salvaRubrica(Rubrica rubrica) {
        
      
    }
    
    /**
     * @brief Carica la rubrica dalla memoria di massa
     * 
     * Deserializza la rubrica e i contatti al suo interno e inizializza la rubrica
     * Che rubrica serializza?
     * Non viene passata una rubrica per parametro
     * E' una rubrica statica?
     * Chi è l'autore di questa atrocità?
     * 
     */
    public static Rubrica caricaRubrica() {
        
        
    }
    
    /**
     * @brief Esporta i contatti selezionati
     * 
     * Esporta la lista di contatti selezionati dall'utente e li serializza in un file .vcf
     * 
     * @param contatti I contatti da esportare
     * @param nomeFile Il nome del file da esportare
     */
    public static void esportaContatti(List<Contatto> contatti, String nomeFile) {
        
        
    }
    
    /**
     * @brief Importa i contatti selezionati
     * 
     * Importa la rubrica dei contatti selezionata dall'utente e aggiunge quelli semanticamente corretti,
     * per quelli incorretti appare un pop up riassuntivo di errore.
     * 
     * Al momento non ha alcun senso la scelta di questi parametri
     * Se gli elemnti selezionati dall'utente sono contatti significa che l'utente sta già
     * scegliendo da un file, a che serve nomeFile?
     * Ma poi non può importare un intera rubrica .vcf?
     * 
     */
    public static void importaContatti(List<Contatto> contatti, String nomeFile) {
        
        
    }
}
