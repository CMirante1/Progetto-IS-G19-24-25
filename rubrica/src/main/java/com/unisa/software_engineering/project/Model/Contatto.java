package com.unisa.software_engineering.project.Model;

import java.io.Serializable;
import javafx.scene.image.Image;

public class Contatto implements Comparable<Contatto>, Serializable{
    
    private String nome;
    private String cognome;
    private String[] numeriDiTelefono;
    private String[] emails;
    private byte[] immagineProfiloBytes;

    public Contatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails, Image immagineProfilo) {
        
        this.nome = nome;
        this.cognome = cognome;
        this.numeriDiTelefono = numeriDiTelefono;
        this.emails = emails;
        this.immagineProfiloBytes = convertiImmagine(immagineProfilo);
    }

    public String getNome() {
        
        return nome;
    }

    public String getCognome() {
        
        return cognome;
    }

    public String[] getNumeriDiTelefono() {
        
        return numeriDiTelefono;
    }

    public String[] getEmails() {
        
        return emails;
    }

   private byte[] convertiImmagine(Image immagineProfilo) {
       
   }
   
   public Image getImmagineProfilo() {
       
       
   }
   
   public void modificaContatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails) {
       
       
   }
    
    @Override
    public int compareTo(Contatto c) {
        
        return this.nome.compareTo(c.nome);
    }
}
