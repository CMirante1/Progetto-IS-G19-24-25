/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.Model;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paolo
 */
public class ContattoV2 implements Comparable<Contatto>, Serializable {
    private String nome;
    private String cognome;
    private final int nDati;
    private String[] numeri;
    private String[] emails;
    
    public ContattoV2(String nome, String cognome, String[] numeri, String[] emails){
        nDati=3;
        try {
            verificaDati(nome,cognome,numeri,emails);
            this.nome=nome;
            this.cognome=cognome;
            this.numeri=numeri;
            this.emails=emails;
            } catch (InfoContattoException ex) {
            System.out.println("Errore nell'aggiunta del contatto.\n" + ex.getMessage());
        }
    }
    @Override
    public int compareTo(Contatto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void verificaDati(String nome, String cognome, String[] numeri, String[] emails)throws InfoContattoException{
        if(nome == null && cognome == null) throw new InfoContattoException("Il campo nome o cognome non possono essere vuoti");
        
        if (nome != null && !nome.matches("[a-zA-Z0-9]+")) throw new InfoContattoException("Il nome non può contenere caratteri speciali!");
        if (cognome != null && !cognome.matches("[a-zA-Z0-9]+")) throw new InfoContattoException("Il cognome non può contenere caratteri speciali!");
        for(int i = 0; i < nDati;i++){
            if (!(numeri[i] != null && numeri[i].matches("\\d+"))) throw new InfoContattoException("I numeri telefonici possono contenere soltanto numeri.");
            if (!(emails[i] != null && emails[i].matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"))) throw new InfoContattoException("Gli indirizzi email devono contenere una @ e un dominio.");
        }
    }
}
