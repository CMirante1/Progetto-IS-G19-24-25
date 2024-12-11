package com.unisa.software_engineering.project.model;

import java.io.Serializable;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class Email extends InfoContatto implements Serializable{

    public Email(String info) throws InfoContattoException{ super(info); }
    
    @Override
    public void verifica(String info) throws InfoContattoException{
        if (!(info == null ||
            info.trim().isEmpty() ||
            !info.matches(
            "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")))
            throw new InfoContattoException("Email non valida");
    }
}
