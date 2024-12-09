package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

public class NomePersona extends InfoContatto implements Serializable{

    public NomePersona(String nome){
        super(nome);
    }

    @Override
    public boolean verifica(){

        return true;
    }
}
