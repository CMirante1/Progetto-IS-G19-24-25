package com.unisa.software_engineering.project.Model;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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
    private static Alert alert;
    private static Stage stage;
    /**
     * @brief Salva la rubrica sulla memoria di massa
     *
     * Serializza la rubrica e i contatti al suo interno e la salva ad un path predefinito
     *
     * @param rubrica La rubrica da salvare
     */
    public static void salvaRubrica(Rubrica rubrica) {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("res/" + FILE_BACKUP))) {

            oos.writeObject(rubrica);
        } catch(IOException e) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore nel salvataggio del file");
            alert.show();
        }
    }

    
    public static Rubrica caricaRubrica() {

        File file = new File("res/" + FILE_BACKUP);
        Rubrica rubrica;

        if(file != null) {

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

                rubrica = (Rubrica) ois.readObject();

                return rubrica;
            } catch(IOException | ClassNotFoundException e) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Errore nell'apertura del file");
                alert.show();
            }
        }

        return new Rubrica();
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

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(stage);


    }

    /**
     * @param nomeFile
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
    public static List<Contatto> importaContatti(String nomeFile) {

    }

    public static void setStage(Stage stagePrincipale) {

        stage = stagePrincipale;
    }
}
