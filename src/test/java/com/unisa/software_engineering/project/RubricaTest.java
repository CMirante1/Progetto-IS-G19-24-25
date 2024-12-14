package com.unisa.software_engineering.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.unisa.software_engineering.project.model.*;
import com.unisa.software_engineering.project.exceptions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class RubricaTest {

    private Rubrica rubrica;
    private Contatto contatto1;
    private Contatto contatto2;
    private Contatto contatto3;

    @BeforeEach
    void setUp() throws InfoContattoException, IOException {
        rubrica = new Rubrica();
        contatto1 = new Contatto(
            "Mario", 
            "Rossi",
            new String[]{"123456789"}, 
            new String[]{"email@esempio.it"}, 
            new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB));
        contatto2 = new Contatto(
            "Luigi", 
            "Verdi",
            new String[]{"987654321"}, 
            new String[]{"email2@esempio.it"}, 
            new BufferedImage(75, 75, BufferedImage.TYPE_INT_ARGB));
        contatto3 = new Contatto(
            "Bowser", 
            "Gialli",
            new String[]{"123454321"}, 
            new String[]{"email3@esempio.it"}, 
            new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));    
    }

    @Test
    public void testAggiungiContatto(){

        rubrica.aggiungiContatto(contatto1);

        assertEquals(1, rubrica.getContatti().size());
        assertEquals(contatto1, rubrica.getContatti().get(0));
    }

    @Test
    public void testRimuoviContatto(){

        rubrica.aggiungiContatto(contatto2);
        rubrica.rimuoviContatto(contatto2);

        assertEquals(0, rubrica.getContatti().size());
    }

    @Test
    public void testRimuoviContattoNonEsistente(){

        rubrica.rimuoviContatto(contatto3);

        assertEquals(0, rubrica.getContatti().size());
    }

    @Test
    public void testToString(){

        rubrica.aggiungiContatto(contatto1);
        rubrica.aggiungiContatto(contatto2);
        String expected = "Rubrica:\n" + contatto1.toString() + "\n" + contatto2.toString() + "\n";

        assertEquals(expected, rubrica.toString());
    }
}
