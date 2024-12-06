package com.unisa.software_engineering.project.Model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * @class Contatto
 * @brief La struttura Contatto nella rubrica
 * 
 * Questa è la struttura dati che definisce un contatto con tutte le informazioni associate
 * 
 * @ingroup Models
 * @see Rubrica.java
 * @author paolo
 * @date 02/12/24
 * @todo Decidere una volta e per tutte dove bisogna verificare i dati
 */
public class Contatto implements Comparable<Contatto>, Serializable{
    
    private String nome;
    private String cognome;
    private String[] numeriDiTelefono;
    private String[] emails;
    private byte[] immagineProfilo;

    /**
     * @constructor
     * @param nome Nome del contatto
     * @param cognome Cognome del contatto
     * @param numeriDiTelefono Tutti i numeri di telefono
     * @param emails Tutte le email
     * @param immagineProfilo L'immagine del contatto
     */
    public Contatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails, BufferedImage immagineProfilo) {
        
        this.nome = nome;
        this.cognome = cognome;
        this.numeriDiTelefono = numeriDiTelefono;
        this.emails = emails;
        try{
            this.immagineProfilo = ImmagineAByte(immagineProfilo);
        }catch(IOException e){
            this.immagineProfilo = new byte[0];
        }
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

    /**
     * @brief Converte la BufferedImage a byte[]
     * 
     * Convertendo la BufferedImage a byte[] permette la serializzazione e quindi il salvataggio delle immagini
     * di profilo
     * 
     * @param immagineProfilo Immagine del profilo
     * @return byte[] dell'immagine
     * @author carmine
     * @date 06/12/24
     * @throws IOException
     */
   private byte[] ImmagineAByte(BufferedImage immagineProfilo) throws IOException{
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       ImageIO.write(immagineProfilo, "JPEG", baos);
       return baos.toByteArray();
   }
   
   /**
    *  
    * Converte il byte[] in una BufferedImage che può essere gestita più facilmente dai metodi che
    * chiamano la get
    *
    * @author carmine
    * @date 06/12/24
    * @throws IOException
    */
   public BufferedImage getImmagineProfilo() throws IOException{
       ByteArrayInputStream bais = new ByteArrayInputStream(immagineProfilo);
       return ImageIO.read(bais);
   }
   
   /**
    * 
    * Qualcuno spieghi a che serve sta robba
    *
    */
   public void modificaContatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails) {
       
       
   }
    
   /**
    * 
    * Confronta i contatti per nome
    *
    * @todo Aggiungere anche il cognome al confronto
    */
    @Override
    public int compareTo(Contatto c) {
        
        return this.nome.compareTo(c.nome);
    }
}
