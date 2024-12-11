package com.unisa.software_engineering.project.model;

import java.io.Serializable;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class Email extends InfoContatto implements Serializable{

    public Email(String info) throws InfoContattoException{ super(info); }
    
    @Override
    public void verifica(String info) throws InfoContattoException{
        if (info == null) throw new InfoContattoException("Email null");
        if (info.isEmpty()) throw new InfoContattoException("Email vuota");
        if (!info.contains("@")) throw new InfoContattoException("Email senza @");
        
    }
}
