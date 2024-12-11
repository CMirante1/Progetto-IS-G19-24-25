package com.unisa.software_engineering.project.model;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class InfoContatto {

    protected String info;

    public InfoContatto(String info) throws InfoContattoException { verifica(info); this.info = info; }

    public void setInfo(String info) throws InfoContattoException { verifica(info); this.info = info; }
    public String getInfo() { return info; };
    public void verifica(String info) throws InfoContattoException { }
}
