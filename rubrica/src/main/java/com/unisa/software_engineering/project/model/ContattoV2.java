/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unisa.software_engineering.project.model;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import java.io.Serializable;

/**
 *
 * @author paolo
 */
public class ContattoV2 implements Comparable<ContattoV2>, Serializable {

    private String nome;
    private String cognome;
    private final static int nDati=3;;
    private String[] numeri;
    private String[] emails;

    public ContattoV2(String nome, String cognome, String[] numeri, String[] emails)  {

            //verificaDati(nome,cognome,numeri,emails);
            this.nome=nome;
            this.cognome=cognome;
            this.numeri=numeri;
            this.emails=emails;

    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public static int getnDati() {
        return nDati;
    }

    public String[] getNumeri() {
        return numeri;
    }

    public String[] getEmails() {
        return emails;
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
   @Override
    public int compareTo(ContattoV2 c) {
        if((this.cognome.compareTo(c.getCognome()) == 0)){ return this.nome.compareTo(c.getNome());}
        return this.cognome.compareTo(c.getCognome());
    }

}
