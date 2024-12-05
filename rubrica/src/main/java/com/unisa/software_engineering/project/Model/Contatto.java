package com.unisa.software_engineering.project.Model;

public class Contatto implements Comparable<Contatto> {
    
    private String nome;
    private String cognome;
    private String[] numeriDiTelefono;
    private String[] emails;
    private ImmagineProfilo immagineProfilo;

    public Contatto(String nome, String cognome, String[] numeriDiTelefono, String[] emails, ImmagineProfilo immagineProfilo) {
        
        this.nome = nome;
        this.cognome = cognome;
        this.numeriDiTelefono = numeriDiTelefono;
        this.emails = emails;
        this.immagineProfilo = immagineProfilo;
    }

    public String getNome() {
        
        return nome;
    }

    public void setNome(String nome) {
        
        this.nome = nome;
    }

    public String getCognome() {
        
        return cognome;
    }

    public void setCognome(String cognome) {
        
        this.cognome = cognome;
    }

    public String[] getNumeriDiTelefono() {
        
        return numeriDiTelefono;
    }

    public void setNumeriDiTelefono(String[] numeriDiTelefono) {
        
        this.numeriDiTelefono = numeriDiTelefono;
    }

    public String[] getEmails() {
        
        return emails;
    }

    public void setEmails(String[] emails) {
        
        this.emails = emails;
    }

    public ImmagineProfilo getImmagineProfilo() {
        return immagineProfilo;
    }

    public void setImmagineProfilo(ImmagineProfilo immagineProfilo) {
        this.immagineProfilo = immagineProfilo;
    }
    
    @Override
    public int compareTo(Contatto c) {
        
        return this.nome.compareTo(c.nome);
    }
}
