package com.unisa.software_engineering.project.model;


import com.unisa.software_engineering.project.exceptions.InfoContattoException;

import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class ContattoV3 implements Serializable {

    private String nome;
    private String cognome;
    private String[] numeri;
    private String[] emails;
    private byte[] immagineProfilo;

    public static final int MAX_NUMERI = 3;
    public static final int MAX_EMAILS = 3;

   public ContattoV3(String nome, String cognome, String[] numeri, String[] emails, BufferedImage immagineProfilo) throws InfoContattoException, IOException {

       verificaDati(nome, cognome, numeri, emails);
       this.nome = nome;
       this.cognome = cognome;
       this.numeri = numeri;
       this.emails = emails;
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

            if(!email.isEmpty() && !email.contains("@"))
                throw new InfoContattoException("L'indirizzo email deve contenere '@'!");

            if(!email.isEmpty() && (email.split("@").length != 2))
                throw new InfoContattoException("Formato email non valido!");

            if (!email.isEmpty() && !email.split("@")[0]
                .matches("^[a-zA-Z0-9._+&*-]+([a-zA-Z0-9._+&*-]*[a-zA-Z0-9])?$"))
                    throw new InfoContattoException("Local part dell'email non valida!");

            if (!email.isEmpty() && !email.split("@")[1]
                .matches("^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"))
                    throw new InfoContattoException("Dominio dell'email non valido");
        }
    }

   public void modificaContatto(String nome, String cognome, String[] numeri, String[] emails, BufferedImage immagineProfilo) throws InfoContattoException, IOException {

       verificaDati(nome, cognome, numeri, emails);
       this.nome = nome;
       this.cognome = cognome;
       this.numeri = numeri;
       this.emails = emails;
       this.immagineProfilo = immagineAByte(immagineProfilo);
   }

    private byte[] immagineAByte(BufferedImage immagineProfilo) throws IOException {

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
