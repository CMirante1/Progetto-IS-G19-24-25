package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;
import com.unisa.software_engineering.project.Exceptions.NumeroNonValidoException;

public class NumeroDiTelefono extends InfoContatto implements Serializable {

    public NumeroDiTelefono(String info) throws InfoContattoException { super(info); }

    @Override
    public void verifica(String info) throws NumeroNonValidoException {

        return;
    }
}
