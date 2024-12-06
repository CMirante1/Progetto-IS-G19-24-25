package com.unisa.software_engineering.project.Model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Contatto implements Comparable<Contatto>, Serializable{
    
    private String nome;
    private String cognome;
    private String[] numeriDiTelefono;
    private String[] emails;
    private byte[] immagineProfilo;

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

   private byte[] ImmagineAByte(BufferedImage immagineProfilo) throws IOException{
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       ImageIO.write(immagineProfilo, "JPEG", baos);
       return baos.toByteArray();
   }
   
   public BufferedImage getImmagineProfilo() throws IOException{
       ByteArrayInputStream bais = new ByteArrayInputStream(immagineProfilo);
       return ImageIO.read(bais);
   }
   
   public void modificaContatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails) {
       
       
   }
    
    @Override
    public int compareTo(Contatto c) {
        
        return this.nome.compareTo(c.nome);
    }
}
