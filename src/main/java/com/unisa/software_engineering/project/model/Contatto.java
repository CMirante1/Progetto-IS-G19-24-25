package com.unisa.software_engineering.project.model;


import com.unisa.software_engineering.project.exceptions.InfoContattoException;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
/**
 * @file Contatto.java
 * @class Contatto
 * @brief Classe che definisce un contatto all'interno della rubrica.
 *
 * Contiene informazioni come nome, cognome, numeri di telefono, email e immagine del profilo.
 */
public class Contatto implements Serializable, Comparable<Contatto> {

    private String nome;
    private String cognome;
    private String[] numeri;
    private String[] emails;
    private byte[] immagineProfilo;

    public static final int MAX_NUMERI = 3;
    public static final int MAX_EMAILS = 3;
    /**
     * @brief Costruttore della classe Contatto.
     * Costruttore del un contatto che riceve in input tutti gli attributi.
     *
     * @param nome Il nome del contatto.
     * @param cognome Il cognome del contatto.
     * @param numeri Un array di numeri di telefono.
     * @param emails Un array di indirizzi email.
     * @param immagineProfilo L'immagine del profilo del contatto come BufferedImage.
     * @throws InfoContattoException Se i dati forniti non sono validi.
     * @throws IOException Se si verifica un errore durante la conversione dell'immagine.
     */
   public Contatto(String nome, String cognome, String[] numeri, String[] emails, BufferedImage immagineProfilo) throws InfoContattoException, IOException {

       verificaDati(nome, cognome, numeri, emails);
       this.nome = nome;
       this.cognome = cognome;

       this.numeri = new String[MAX_NUMERI];
       for(int i = 0; i < this.numeri.length && i < numeri.length; i++) this.numeri[i] = numeri[i];

       this.emails = new String[MAX_EMAILS];
       for(int i = 0; i < this.emails.length && i < emails.length; i++) this.emails[i] = emails[i];

       this.immagineProfilo = immagineAByte(immagineProfilo);
   }
   /**
    * @brief Verifica la validità dei dati forniti per il contatto.
    * Questo metodo controlla che i campi nome, cognome, numeri di telefono e email rispettino
    * i requisiti di formato e lancia un'eccezione in caso contrario.
    *
    * @param nome Il nome del contatto (può essere vuoto ma non contenere caratteri speciali).
    * @param cognome Il cognome del contatto (può essere vuoto ma non contenere caratteri speciali).
    * @param numeri Un array di numeri di telefono (deve contenere solo cifre).
    * @param emails Un array di indirizzi email (deve rispettare il formato email standard).
    * @throws InfoContattoException Se i dati non rispettano i requisiti di validità.
    */

    private void verificaDati(String nome, String cognome, String[] numeri, String[] emails) throws InfoContattoException {

        if (nome.isEmpty() && cognome.isEmpty())
            throw new InfoContattoException("Almeno uno tra i campi nome e cognome non deve essere vuoto!");

        if (!nome.isEmpty() && !nome.matches("[a-zA-z0-9 'èòà]+"))
            throw new InfoContattoException("Il nome non può contenere caratteri speciali!");

        if (!cognome.isEmpty() && !cognome.matches("[a-zA-z0-9 'èòà]+"))
            throw new InfoContattoException("Il cognome non può contenere caratteri speciali!");

        for (String numero : numeri) {

            if (numero.isEmpty()) continue;

            if (!numero.matches("[0-9]+"))
                throw new InfoContattoException("Il numero deve contenere soltanto cifre!");
        }

        for(String email : emails) {

            if(email.isEmpty()) continue;

            if(!email.contains("@"))
                throw new InfoContattoException("L'indirizzo email deve contenere '@'!");

            if((email.split("@").length != 2))
                throw new InfoContattoException("Formato email non valido!");

            if (!email.split("@")[0]
                .matches("^[a-zA-Z0-9._+&*-]+([a-zA-Z0-9._+&*-]*[a-zA-Z0-9])?$"))
                    throw new InfoContattoException("Local part dell'email non valida!");

            if (!email.split("@")[1]
                .matches("^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"))
                    throw new InfoContattoException("Dominio dell'email non valido");
        }
    }

    /**
     * Modifica gli attributi del contatto.
     *
     * @param nome Il nuovo nome del contatto.
     * @param cognome Il nuovo cognome del contatto.
     * @param numeri I nuovi numeri di telefono.
     * @param emails I nuovi indirizzi email.
     * @param immagineProfilo La nuova immagine del profilo come BufferedImage.
     * @throws InfoContattoException Se i dati forniti non sono validi.
     * @throws IOException Se si verifica un errore durante la conversione dell'immagine.
     */
   public void modificaContatto(String nome, String cognome, String[] numeri, String[] emails, BufferedImage immagineProfilo) throws InfoContattoException, IOException {

       verificaDati(nome, cognome, numeri, emails);
       this.nome = nome;
       this.cognome = cognome;
       for(int i = 0; i < this.numeri.length && i < numeri.length; i++) this.numeri[i] = numeri[i];
       for(int i = 0; i < this.emails.length && i < emails.length; i++) this.emails[i] = emails[i];
       this.immagineProfilo = immagineAByte(immagineProfilo);
   }

    private byte[] immagineAByte(BufferedImage immagineProfilo) throws IOException {

        if(immagineProfilo == null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(immagineProfilo, "PNG", baos);

        return baos.toByteArray();
    }

    public String getNome() {

        return nome;
    }

    public String getCognome() {

        return cognome;
    }

    public String[] getNumeri() {

        return numeri;
    }

    public String[] getEmails() {

        return emails;
    }

    public Image getImmagineProfilo() throws IOException{

       if(immagineProfilo == null)  return null;

       ByteArrayInputStream bais = new ByteArrayInputStream(immagineProfilo);

        return new Image(bais);
    }
    /**
     * Confronta il contatto corrente con un altro contatto in base al cognome, se questo dovesse risultare uguale i contatti vengono confrontati in base al nome.
     *
     * @param contatto Il contatto con cui confrontare.
     * @return Un valore negativo, zero o positivo in base all'ordine lessicografico.
     */
    @Override
    public int compareTo(Contatto contatto) {

       if(this.cognome.compareTo(contatto.cognome) == 0)
           return this.nome.compareTo(contatto.nome);

       return this.cognome.compareTo(contatto.cognome);
    }

    @Override
    public String toString(){
        return
            nome + " " + cognome + ":\n" +
            "Tel:\n" +
            (numeri[0] != null ? numeri[0] : "") + "\n" +
            (numeri[1] != null ? numeri[1] : "") + "\n" +
            (numeri[2] != null ? numeri[2] : "") + "\n" +
            "Email:\n" +
            (emails[0] != null ? emails[0] : "") + "\n" +
            (emails[1] != null ? emails[1] : "") + "\n" +
            (emails[2] != null ? emails[2] : "") + "\n" +
            "Image size: " + (immagineProfilo != null ? immagineProfilo.length : 0);
    }
}
