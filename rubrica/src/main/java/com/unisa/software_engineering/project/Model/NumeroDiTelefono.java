package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

import com.unisa.software_engineering.project.Exceptions.NumeroNonValidoException;

public class NumeroDiTelefono extends InfoContatto implements Serializable {

    public NumeroDiTelefono(String info) { super(info); }

    @Override
    public void verifica() throws NumeroNonValidoException{

        return;
    }
}
