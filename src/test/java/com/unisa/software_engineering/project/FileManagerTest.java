package com.unisa.software_engineering.project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.*;
import com.unisa.software_engineering.project.model.*;
import com.unisa.software_engineering.project.exceptions.*;

import com.unisa.software_engineering.project.model.*;

public class FileManagerTest {

    private Rubrica rubrica;
    private Contatto contatto1;
    private Contatto contatto2;
    private Contatto contatto3;
    
    @BeforeEach
    void setUp() throws InfoContattoException, IOException {

        contatto1 = new Contatto(
            "Peppe",
            "Max",
            new String[]{"34645674"},
            new String[]{"email@mail.it"},
            null);
        contatto2 = new Contatto(
            "Giorgio",
            "",
            new String[]{"23443536", "23452346"},
            new String[]{"mailmail@mail.it", "altra@mail.com"},
            null);
        contatto3 = new Contatto(
            "Contatto",
            "Bello",
            new String[]{""},
            new String[]{"boh@mail.it"},
            null);

        rubrica = new Rubrica();
        rubrica.aggiungiContatto(contatto1);
        rubrica.aggiungiContatto(contatto2);
        rubrica.aggiungiContatto(contatto3);
    }

    @Test
    void testSalva(){
        try {
            FileManager.salvaRubrica(rubrica);
        } catch(Exception ex){
            fail();
        }
    }

    @Test
    void testCarica(){
        try{
            FileManager.caricaRubrica();
        } catch(Exception ex){
            fail();
        }
    }

    @Test
    void testSalvaCarica(){
        Rubrica rubricaCaricata = null;
        try{
            FileManager.salvaRubrica(rubrica);
            rubricaCaricata = FileManager.caricaRubrica();
        } catch(Exception ex){
            fail();
        }
        assertEquals(rubricaCaricata.getContatti().size(), rubrica.getContatti().size());

    }
}
