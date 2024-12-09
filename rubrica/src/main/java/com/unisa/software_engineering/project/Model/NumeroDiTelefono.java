package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

public class NumeroDiTelefono extends InfoContatto implements Serializable {

    public NumeroDiTelefono(String info) { super(info); }

    @Override
    public boolean verifica(){

        return true;
    }
}
