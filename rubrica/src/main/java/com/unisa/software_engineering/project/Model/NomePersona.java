package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

import com.unisa.software_engineering.project.Exceptions.NomeNonValidoException;

public class NomePersona extends InfoContatto implements Serializable{

    public NomePersona(String nome){
        super(nome);
    }

    @Override
    public void verifica() throws NomeNonValidoException{

        return;
    }
}
