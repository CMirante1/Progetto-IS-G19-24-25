package com.unisa.software_engineering.project.Model;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.Buffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static Alert alert = new Alert(Alert.AlertType.ERROR);
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

            alert.setContentText("Errore nel salvataggio della rubrica");
            alert.showAndWait();
        }
    }


    public static Rubrica caricaRubrica() {

        File file = new File("res/" + FILE_BACKUP);
        Rubrica rubrica = null;

        if(file.exists()) {

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()))) {

                rubrica = (Rubrica) ois.readObject();

                return rubrica;
            } catch(IOException | ClassNotFoundException e) {

                alert.setContentText("Errore nell'apertura della rubrica");
                alert.showAndWait();
            }
        }
        else rubrica = new Rubrica();

        return rubrica;
    }

    /**
     * @brief Esporta i contatti selezionati
     *
     * Esporta la lista di contatti selezionati dall'utente e li serializza in un file .vcf
     *
     * @param contatti I contatti da esportare
     *
     */
    public static void esportaContatti(List<Contatto> contatti) {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showSaveDialog(stage);

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        if(!file.getName().endsWith(".vcf"))
            file = new File(file.getAbsolutePath() + ".vcf");

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getName())))) {

            String[] numeriDiTelefono;
            String[] emails;

            for(Contatto contatto : contatti) {

                numeriDiTelefono = contatto.getNumeriDiTelefono();
                emails = contatto.getEmails();

                pw.println("BEGIN:VCARD");
                pw.println("VERSION:4.0");
                pw.println("N:" + contatto.getCognomi() + ";" + contatto.getNomi() + ";");
                for(int i = 0; i < numeriDiTelefono.length; i++) {

                    if(numeriDiTelefono[i] == null) break;

                    pw.println("TEL:" + numeriDiTelefono[i]);
                }
                for(int i = 0; i < emails.length; i++) {

                    if(emails[i] == null) break;

                    pw.println("EMAIL:" + emails[i]);
                }
                pw.println("END:VCARD");
            }
        } catch (IOException e) {

            alert.setContentText("Errore nell'esportazione dei contatti");
            alert.showAndWait();
        }

    }

    /**
<<<<<<< HEAD
=======
     *
>>>>>>> a4bfe4d14dab1d207cfa026526ca5d24d04d1063
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
    public static void importaContatti(String nomeFile) {

        FileChooser fileChooser = new FileChooser();

        File fileSelezionato = fileChooser.showOpenDialog(stage);

        FileChooser.ExtensionFilter filtro = new FileChooser.ExtensionFilter("vCard Files (*.vcf)", "*.vcf");
        fileChooser.getExtensionFilters().add(filtro);

        if(fileSelezionato == null) return;
        String riga;
        String nome;
        String cognome;
        String[] numeri = new String[3];
        String[] emails = new String[3];
        int index;
        try(BufferedReader br = new BufferedReader(new FileReader(fileSelezionato))) {
            nome=null;
            cognome=null;
            index = 0;
            for(int i = 0; i<3; i++){
                numeri[i]=null;
                emails[i]=null;
            }
            while((riga = br.readLine()) != null) {

                if(riga.startsWith("N:")) {
                    // Dividi il nome usando gli spazi, ma prendi il primo come nome e il resto come cognome
                    String[] partiNome = riga.substring(2).split(";");
                    cognome = partiNome[0];
                    nome = partiNome[1];
                }
                else if(riga.startsWith("TEL:")) {
                    numeri[index++] = riga.substring(4);
                }
                else if(riga.startsWith("EMAIL:")) {
                    emails[index++] = riga.substring(7);
                }
                else if(riga.startsWith("END")) {
                    if (nome != null || cognome != null) {
                        Contatto contatto = new Contatto(nome, cognome, numeri, emails,null);
                        System.out.println(contatto);
                    } else {
                        System.out.println("Dati incompleti per un contatto");
                    }
                }

            }
        } catch (IOException   e) {

            System.out.println("Errore nella lettura del file");
        } catch (InfoContattoException ex) {
            System.out.println("Errore nel formato dati del contatto");
        }

    }

    public static void setStage(Stage stagePrincipale) {

        stage = stagePrincipale;
    }
}
