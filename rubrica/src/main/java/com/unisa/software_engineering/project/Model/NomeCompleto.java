package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;
import com.unisa.software_engineering.project.Exceptions.NomeNonValidoException;

public class NomeCompleto extends InfoContatto implements Serializable{

    public NomeCompleto(String nome, String cognome) throws InfoContattoException {
        super(cognome + "," + nome);
    }

    public void setNomi(String nome) throws NomeNonValidoException{
        if(nome.matches("[^a-zA-Z0-9\\s]"))
            throw new NomeNonValidoException("Nome con caratteri speciali");
        info = info.split(",")[0] + "," + nome;
    }

    public void setCognomi(String cognome) throws NomeNonValidoException {
        if(cognome.matches("[^a-zA-Z0-9\\s]"))
            throw new NomeNonValidoException("Nome con caratteri speciali");
        info = cognome + "," + info.split(",")[1];
    }

    public String getNomi() { return info.split(",")[1]; }

    public String getCognomi() { return info.split(",")[0]; }

    @Override
    public void verifica(String info) throws NomeNonValidoException {
        if(info.indexOf(",", 0) == -1)
            throw new NomeNonValidoException("NomeCompleto senza \",\"");
        if(info.split(",").length > 2)
            throw new NomeNonValidoException("NomeCompleto formattato male");
        if(info.split(",")[0].matches("[^a-zA-Z0-9\\s]"))
            throw new NomeNonValidoException("Nome con caratteri speciali");
        if(info.split(",")[1].matches("[^a-zA-Z0-9\\s]"))
            throw new NomeNonValidoException("Nome con caratteri speciali");
    }
}
