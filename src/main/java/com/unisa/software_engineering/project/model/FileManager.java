/**
 * @class FileManager
 * @brief Gestisce il caricamento, il salvataggio, l'importazione e l'esportazione dei contatti.
 *
 * Questa classe offre metodi statici per gestire la persistenza dei dati della rubrica,
 * inclusa la lettura e scrittura su file, e l'importazione/esportazione di contatti in formato .vcf
 *
 */
package com.unisa.software_engineering.project.model;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

import java.io.*;
import java.util.List;

/**
 * @file FileManager.java
 * @class FileManager
 * @brief Carica, salva, importa e esporta
 *
 * Si occupa del caricamento iniziale della rubrica, il salvataggio in memoria e l'esportazione e
 * importazione di contatti sempre sottoforma di rubrica.
 *
 * @ingroup Models
 * @see Rubrica
 * @author andre
 * @date 05/12/24
 */
public abstract class FileManager {

    private static final String FILE_BACKUP = "rubrica.dat";
    /**
     * @brief Salva la rubrica su file.
     *
     * Serializza la rubrica e i contatti contenuti al suo interno, salvandoli
     * in un file di backup "FILE_BACKUP"
     *
     * @param rubrica La rubrica da salvare.
     */
    public static void salvaRubrica(Rubrica rubrica) throws IOException {

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/" + FILE_BACKUP))) {

            oos.writeObject(rubrica);
        }
    }

    /**
     * @brief Carica la rubrica salvata su file.
     *
     * Legge i dati serializzati da un file di backup e li converte in un'istanza della classe Rubrica.
     * Se il file non esiste, restituisce una nuova rubrica vuota.
     *
     * @return rubrica. Ritorna la rubrica contenente i dati caricati dal file.
     */
    public static Rubrica caricaRubrica() throws IOException, ClassNotFoundException {

        File file = new File("src/main/resources/" + FILE_BACKUP);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.getPath()))){
            return (Rubrica)ois.readObject();
        } catch (IOException ex){
            return new Rubrica();
        }
    }

    /**
     * @brief Esporta una lista di contatti in formato `.vcf`.
     *
     *
     * Scrive i contatti selezionati in un file in formato  VFC, includendo nome, cognome,
     * numeri di telefono ed email mostrando eventuali messaggi di errore
     *
     * @param contatti La lista di contatti da esportare.
     * @param file Il file di destinazione per l'esportazione.
     */
    public static void esportaContatti(List<Contatto> contatti, File file) throws IOException {

        try(PrintWriter pw = new PrintWriter(new FileWriter(file.getAbsolutePath()))) {

            String[] numeriDiTelefono;
            String[] emails;

            for(Contatto contatto : contatti) {

                numeriDiTelefono = contatto.getNumeri();
                emails = contatto.getEmails();

                pw.println("BEGIN:VCARD");
                pw.println("VERSION:4.0");
                pw.println("N:" + contatto.getCognome() + ";" + contatto.getNome());
                for(int i = 0; i < numeriDiTelefono.length; i++) {

                    if(numeriDiTelefono[i] == null) continue;

                    pw.println("TEL:" + numeriDiTelefono[i]);
                }
                for(int i = 0; i < emails.length; i++) {

                    if(emails[i] == null) continue;

                    pw.println("EMAIL:" + emails[i]);
                }
                pw.println("END:VCARD");
            }

        } catch(IOException e) {

            throw e;
        }
    }


    /**
     * @brief Importa contatti da un file in formato .vcf.
     *
     * Legge un file .vcf contenente uno o più contatti e li aggiunge alla rubrica dell'utente mostrando eventuali errori in fase di parsing
     *
     * @param file Il file `.vcf` da importare.
     * @param rubrica La rubrica in cui aggiungere i contatti importati.
     */
    public static int importaContatti(File file, Rubrica rubrica) throws IOException {

        String riga;
        String nome = "";
        String cognome = "";
        String[] numeri = new String[Contatto.MAX_NUMERI];
        for(int i = 0; i < numeri.length; i++) numeri[i] = "";
        String[] emails = new String[Contatto.MAX_EMAILS];
        for(int i = 0; i < emails.length; i++) emails[i] = "";

        int numIndex = 0;
        int emailIndex = 0;
        //int contattiImportati = 0;
        int contattiNonImportati = 0;

        boolean creazioneContatto = false;

        try(BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()))) {

            while((riga = br.readLine()) != null) {

                if(riga.equals("BEGIN:VCARD")){
                    numIndex = 0;
                    emailIndex = 0;
                    nome = "";
                    cognome = "";
                    for(int i = 0; i < numeri.length; i++) numeri[i] = "";
                    for(int i = 0; i < emails.length; i++) emails[i] = "";
                    creazioneContatto = true;
                    continue;
                }

                if(!creazioneContatto) continue;

                if (riga.startsWith("N:")) {

                    String[] partiNome = riga.substring(2).split(";");

                    if(partiNome.length > 0) cognome = partiNome[0];
                    if(partiNome.length > 1) nome = partiNome[1];

                } else if (riga.startsWith("TEL:")) {

                    if(numIndex >= Contatto.MAX_NUMERI) continue;
                    numeri[numIndex++] = riga.substring(4).replace("\n", "");

                } else if (riga.startsWith("EMAIL:")) {

                    if(emailIndex >= Contatto.MAX_EMAILS) continue;
                    emails[emailIndex++] = riga.substring(6).replace("\n", "");

                } else if (riga.equals("END:VCARD")) {

                    creazioneContatto = false;
                    try {
                        Contatto contatto = new Contatto(nome, cognome, numeri, emails, null);
                        rubrica.aggiungiContatto(contatto);
                    } catch(InfoContattoException e) {
                        contattiNonImportati++;
                    }
                }
            }
        }
        return contattiNonImportati;
    }
}
