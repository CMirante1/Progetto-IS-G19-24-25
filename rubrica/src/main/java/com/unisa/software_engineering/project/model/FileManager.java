package com.unisa.software_engineering.project.model;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import javafx.scene.control.Alert;
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
 * @see Rubrica
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
            alert.setContentText("Errore nel salvataggio della rubrica");
            alert.showAndWait();
        }
    }

    /**
     * @brief Carica la rubrica srializzata salvata in memoria
     *
     * Legge i dati serializzati all'interno di un file e li converte in Rubrica
     *
     * @return rubrica restituisce la rubrica letta dal file.
     */
    public static Rubrica caricaRubrica() {

        File file = new File("res/" + FILE_BACKUP);
        Rubrica rubrica = null;

        if(file.exists()) {

            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()))) {

                rubrica = (Rubrica) ois.readObject();

                return rubrica;
            } catch(IOException | ClassNotFoundException e) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Errore nell'apertura della rubrica!");
                alert.showAndWait();
            }
        }
        else rubrica = new Rubrica();

        return rubrica;
    }

    /**
     * @param contatti riceve in input i contatti da esportare
     * @brief Esporta i contatti selezionati
     * <p>
     * Esporta la lista di contatti selezionati dall'utente formattati in formato .vcf
     */
    public static void esportaContatti(List<ContattoV2> contatti, File file) {

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file.getName())))) {

            String[] numeriDiTelefono;
            String[] emails;

            for(ContattoV2 contatto : contatti) {

                numeriDiTelefono = contatto.getNumeri();
                emails = contatto.getEmails();

                pw.println("BEGIN:VCARD");
                pw.println("VERSION:4.0");
                pw.println("N:" + contatto.getCognome() + ";" + contatto.getNome() + ";");
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

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Errore nell'esportazione dei contatti!");
            alert.showAndWait();
        }

    }

    /**
     * @param file
     * @param rubrica
     * @brief Importa i contatti selezionati
     * <p>
     * Riceve in inngresso i file selezioanti dall'utente e ne legge i contatti contenuti importandoli nella rubtrica
     */
    public static void importaContatti(File file, Rubrica rubrica) throws IOException{

        String riga;
        String nome;
        String cognome;
        String[] numeri = new String[3];
        String[] emails = new String[3];

        int index;
        int contattiNonImportati = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
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
                    emails[index++] = riga.substring(6);
                }
                else if(riga.startsWith("END")) {
                    if (nome != null || cognome != null) {
                        ContattoV2 contatto = new ContattoV2(nome, cognome, numeri, emails);
                        rubrica.aggiungiContatto(contatto);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file!");
            throw e;
        } catch (InfoContattoException ex) {
            contattiNonImportati++;
        }

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contattiNonImportati + "non stati importati!");
        alert.showAndWait();
    }
}
