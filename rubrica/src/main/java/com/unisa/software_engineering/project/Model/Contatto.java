package com.unisa.software_engineering.project.Model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;

/**
 * @class Contatto
 * @brief definisce un contatto
 * 
 * Rappresenta ciò che verrà inserito all'interno della struttura dati rubrica
 * @ingroup Models
 * @see Rubrica.java
 * @author gruppo_19
 * @date 02/12/24
 */
public class Contatto implements Comparable<Contatto>, Serializable{
    
    private NomePersona Nomi;
    private NomePersona Cognomi;
    private NumeroDiTelefono[] numeriDiTelefono;
    private Email[] emails;
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
        
        Nomi = new NomePersona(nome);
        Cognomi = new NomePersona(cognome);

        for(int index = 0; index < this.numeriDiTelefono.length; index++)
            this.numeriDiTelefono[index] = new NumeroDiTelefono(numeriDiTelefono[index]);

        for(int index = 0; index < this.emails.length; index++)
            this.emails[index] = new Email(emails[index]);

        try{
            this.immagineProfilo = ImmagineAByte(immagineProfilo);
        }catch(IOException e){
            this.immagineProfilo = new byte[0];
        }
    }

    public String getNomeCompleto(){
        return Nomi.getInfo() + " " + Cognomi.getInfo();
    }

    public String getNomi() { return Nomi.getInfo(); }
    
    public String getCognomi() { return Cognomi.getInfo(); }

    public String[] getNumeriDiTelefono() {
        
        String[] numeriDiTelefono = new String[this.numeriDiTelefono.length];
        for(int index = 0; index < this.numeriDiTelefono.length; index++)
        numeriDiTelefono[index] = this.numeriDiTelefono[index].getInfo();
        return numeriDiTelefono;
    }

    public String[] getEmails() {
        
        String[] emails = new String[this.emails.length];
        for(int index = 0; index < this.emails.length; index++)
            emails[index] = this.emails[index].getInfo();
        return emails;
    }

    /* //La verifica avviene in automatico quando si chiama setInfo();
    public boolean verificaNome(){ return Nomi.verifica() && Cognomi.verifica(); }

    public boolean verificaNumeri() {
        for(InfoContatto info : numeriDiTelefono) if(!info.verifica()) return false;
        return true;
    }

    public boolean verificaEmail() {
        for(InfoContatto info : emails) if(!info.verifica()) return false;
        return true;
    }
    */

    /**
     * @brief Converte la BufferedImage a byte[]
     * 
     * Convertendo la BufferedImage a byte[] permette la serializzazione e quindi il salvataggio delle immagini
     * di profilo
     * 
     * @param immagineProfilo Immagine del profilo
     * @return byte[] dell'immagine
     * @author gruppo_19
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
    * @brief Converte il byte[] in una BufferedImage che può essere gestita più facilmente dai metodi che
    * chiamano la get
    *
    * @author gruppo_19
     * @return 
    * @date 06/12/24
    * @throws IOException
    */
   public BufferedImage getImmagineProfilo() throws IOException{
       ByteArrayInputStream bais = new ByteArrayInputStream(immagineProfilo);
       return ImageIO.read(bais);
   }
   
   /**
    * @brief Modifica un contatto
    * prende in input i nuovi dati del contatto e li sostituisce a quelli precedentemente inseriti
    * @author gruppo_19
    * @param nome
    * @param cognome
    * @param numeriDiTelefono
    * @param emails
    */
   public void modificaContatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails, BufferedImage immagineDiProfilo) throws InfoContattoException {

        Nomi.setInfo(nome);
        Cognomi.setInfo(cognome);

        for(int index = 0; index < this.numeriDiTelefono.length; index++)
            this.numeriDiTelefono[index].setInfo(numeriDiTelefono[index]);

        for(int index = 0; index < this.emails.length; index++)
            this.emails[index].setInfo(emails[index]);

        try{
            this.immagineProfilo = ImmagineAByte(immagineDiProfilo);
        }catch(IOException e){
            this.immagineProfilo = new byte[0];
        }
       
   }
    
   /* 
    * @brief Confronta i contatti per nome
    * Permette di confrontare i contatti secondo un loro attributo, in questo caso il nome al fine di ordinarli all'interno della struttura dati Rubrica
    * Se i nomi di due contatti dovessero risultare uguali questi andranno ordinati secondo il cognome
    * @param c
    */
    @Override
    public int compareTo(Contatto c) {
        if(Cognomi.getInfo().compareTo(c.getCognomi()) == 0){
            return Nomi.getInfo().compareTo(c.getNomi());
        }
        return Cognomi.getInfo().compareTo(c.getCognomi());
    }
}
