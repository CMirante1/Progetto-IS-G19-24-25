package com.unisa.software_engineering.project.model;

import java.io.Serializable;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class NomeCompleto extends InfoContatto implements Serializable{

    public NomeCompleto(String nome, String cognome) throws InfoContattoException {
        super(cognome + "," + nome);
    }

    public void setNomi(String nome) throws InfoContattoException{
        if(nome.matches("[^a-zA-Z0-9\\s]"))
            throw new InfoContattoException("Nome con caratteri speciali");
        info = info.split(",")[0] + "," + nome;
    }

    public void setCognomi(String cognome) throws InfoContattoException {
        if(cognome.matches("[^a-zA-Z0-9\\s]"))
            throw new InfoContattoException("Nome con caratteri speciali");
        info = cognome + "," + info.split(",")[1];
    }

    public String getNomi() { return info.split(",")[1]; }

    public String getCognomi() { return info.split(",")[0]; }

    @Override
    public void verifica(String info) throws InfoContattoException {
        if(info.split(",")[0].matches("[^a-zA-Z0-9\\s]"))
            throw new InfoContattoException("Nome con caratteri speciali");
        if(info.split(",")[1].matches("[^a-zA-Z0-9\\s]"))
            throw new InfoContattoException("Nome con caratteri speciali");
    }

    @Override
    public void setInfo(String info) {}
}
