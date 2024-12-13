package com.unisa.software_engineering.project.model;


import com.unisa.software_engineering.project.exceptions.InfoContattoException;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Contatto implements Serializable {

    private String nome;
    private String cognome;
    private String[] numeri;
    private String[] emails;
    private byte[] immagineProfilo;

    public static final int MAX_NUMERI = 3;
    public static final int MAX_EMAILS = 3;

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

    private void verificaDati(String nome, String cognome, String[] numeri, String[] emails) throws InfoContattoException {

        if (nome.isEmpty() && cognome.isEmpty())
            throw new InfoContattoException("Almeno uno tra i campi nome e cognome non deve essere vuoto!");

        if (!nome.isEmpty() && !nome.matches("[a-zA-z0-9 ]+"))
            throw new InfoContattoException("Il nome non può contenere caratteri speciali!");

        if (!cognome.isEmpty() && !cognome.matches("[a-zA-z0-9 ]+"))
            throw new InfoContattoException("Il cognome non può contenere caratteri speciali!");

        for (String numero : numeri)
            if (!numero.isEmpty() && !numero.matches("[0-9]+"))
                throw new InfoContattoException("Il numero deve contenere soltanto cifre!");

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

   public void modificaContatto(String nome, String cognome, String[] numeri, String[] emails, BufferedImage immagineProfilo) throws InfoContattoException, IOException {

       verificaDati(nome, cognome, numeri, emails);
       this.nome = nome;
       this.cognome = cognome;
       for(int i = 0; i < this.numeri.length && i < numeri.length; i++) this.numeri[i] = numeri[i];
       for(int i = 0; i < this.emails.length && i < emails.length; i++) this.emails[i] = emails[i];
       this.immagineProfilo = immagineAByte(immagineProfilo);
   }

    private byte[] immagineAByte(BufferedImage immagineProfilo) throws IOException {

        if(immagineProfilo == null) return new byte[0];

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

        ByteArrayInputStream bais = new ByteArrayInputStream(immagineProfilo);
        return new Image(bais);
    }
}
