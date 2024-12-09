package com.unisa.software_engineering.project.Model;

import com.unisa.software_engineering.project.Exceptions.InfoContattoException;

public class InfoContatto {

    protected String info;

    public InfoContatto(String info) { this.info = info; }

    public void setInfo(String info) throws InfoContattoException { this.info = info; verifica(); }
    public String getInfo() { return info; };
    public void verifica() throws InfoContattoException { }
}
