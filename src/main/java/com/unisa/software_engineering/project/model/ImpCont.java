package com.unisa.software_engineering.project.model;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ImpCont {

    public int impCont(File file, Rubrica rubrica) throws IOException {

        String riga;
        int contattiImportati = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            String nome = new String();
            String cognome = new String();
            String[] numeri = new String[Contatto.MAX_NUMERI];
            String[] emails = new String[Contatto.MAX_EMAILS];

            int numIndex = 0;
            int emailsIndex = 0;

            while ((riga = br.readLine()) != null) {

                if(riga.equals("BEGIN:VCARD")) {

                    nome = "";
                    cognome = "";
                    for(String numero : numeri) numero = "";
                    for(String email : emails) email = "";
                    numIndex = 0;
                    emailsIndex = 0;
                } else if(riga.startsWith("N:")) {

                    String[] partiNome = riga.substring(2).split(";");

                    if(partiNome.length >= 2) {

                        cognome = partiNome[0];
                        nome = partiNome[1];
                    } else cognome = partiNome[0];
                } else if(riga.startsWith("TEL:")) {

                    if(numIndex == numeri.length) continue;

                    numeri[numIndex++] = riga.substring(4);
                } else if (riga.startsWith("EMAIL:")) {

                    if(emailsIndex == emails.length) continue;

                    emails[emailsIndex++] = riga.substring(6);
                } else if(riga.equals("END:VCARD")) {

                    try {

                        Contatto contatto = new Contatto(nome, cognome, numeri, emails, null);
                        rubrica.aggiungiContatto(contatto);
                        contattiImportati++;
                    } catch(InfoContattoException e) {}
                }
            }

        } catch(IOException e) {

            throw e;
        }

        return contattiImportati;
    }
}
