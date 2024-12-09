package com.unisa.software_engineering.project.Model;

import java.io.Serializable;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.NumberParseException;

public class NumeroDiTelefono extends InfoContatto implements Serializable {

    public NumeroDiTelefono(String info) throws InfoContattoException { super(info); }

    @Override
    public void verifica(String info) throws InfoContattoException {

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

        try{

            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(info, "");

            if(!phoneNumberUtil.isValidNumber(phoneNumber))
                throw new InfoContattoException("Numero non valido");
        } catch(NumberParseException e) {

            throw new InfoContattoException("Numero non valido");
        }
    }
}
