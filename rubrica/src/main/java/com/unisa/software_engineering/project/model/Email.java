package com.unisa.software_engineering.project.model;

import java.io.Serializable;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class Email extends InfoContatto implements Serializable{

    public Email(String info) throws InfoContattoException{ super(info); }
    
    @Override
    public void verifica(String info) throws InfoContattoException{

        if (info == null)
            throw new InfoContattoException("Email null");

        if (info.isEmpty())
            throw new InfoContattoException("Email vuota");

        if (!info.contains("@"))
            throw new InfoContattoException("Email senza @");

        if (info.split("@").length != 2)
            throw new InfoContattoException("Formato email non valido");

        if (!info.split("@")[0]
            .matches("^[a-zA-Z0-9._+&*-]+([a-zA-Z0-9._+&*-]*[a-zA-Z0-9])?$"))
            throw new InfoContattoException("Email local part non valida");

        if (!info.split("@")[1]
            .matches("^[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,}$"))
            throw new InfoContattoException("Email dominio non valido");
        //if(!info.matches("^[\\\\w!#$%&’*+/=?{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$"))
        //    throw new InfoContattoException("Email dominio non valido");
    }
}
