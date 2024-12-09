package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

public class Email extends InfoContatto implements Serializable{

    public Email(String info) { super(info); }

    @Override
    public boolean verifica(){
        return !(info == null ||
        info.trim().isEmpty() ||
        !info.matches(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
    }
}
