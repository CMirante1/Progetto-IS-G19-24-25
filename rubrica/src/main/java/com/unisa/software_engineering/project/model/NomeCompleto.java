package com.unisa.software_engineering.project.model;

import java.io.Serializable;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class NomeCompleto extends InfoContatto implements Serializable{

    private static final String SPECIAL_CHAR = "[^a-zA-Z0-9\\s]";
    private static final String SPECIAL_ERR = "Nome con caratteri speciali";

    public NomeCompleto(String nome, String cognome) throws InfoContattoException {
        super(cognome + "," + nome);
    }

    public void setNomi(String nome) throws InfoContattoException{
        if(nome.matches(SPECIAL_CHAR))
            throw new InfoContattoException(SPECIAL_ERR);
        info = info.split(",")[0] + "," + nome;
    }

    public void setCognomi(String cognome) throws InfoContattoException {
        if(cognome.matches(SPECIAL_CHAR))
            throw new InfoContattoException(SPECIAL_ERR);
        info = cognome + "," + info.split(",")[1];
    }

    public String getNomi() { return info.split(",")[1]; }

    public String getCognomi() { return info.split(",")[0]; }

    @Override
    public void verifica(String info) throws InfoContattoException {
        if(info.split(",")[0].matches(SPECIAL_CHAR))
            throw new InfoContattoException(SPECIAL_ERR);
        if(info.split(",")[1].matches(SPECIAL_CHAR))
            throw new InfoContattoException(SPECIAL_ERR);
    }

    @Override
    public void setInfo(String info) {}
}
