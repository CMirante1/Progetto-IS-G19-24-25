package com.unisa.software_engineering.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import java.util.List;

import com.unisa.software_engineering.project.model.*;

class RubricaTest {

    private Rubrica rubrica;
    private ContattoV2 contatto1;
    private ContattoV2 contatto2;

    @BeforeEach
    void setUp() throws InfoContattoException {
        rubrica = new Rubrica();
        contatto1 = new ContattoV2("Paolo", "Rossi", new String[]{"12345", "67890", ""}, new String[]{"paolo@example.com", "rossi@example.com", ""});
        contatto2 = new ContattoV2("Marco", "Bianchi", new String[]{"54321", "98765", ""}, new String[]{"marco@example.com", "bianchi@example.com", ""});
    }

    @Test
    void testAggiungiContatto() {
        rubrica.aggiungiContatto(contatto1);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertEquals(1, contatti.size());
        assertTrue(contatti.contains(contatto1));
    }

    @Test
    void testRimuoviContatto() {
        rubrica.aggiungiContatto(contatto1);
        rubrica.rimuoviContatto(contatto1);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertEquals(0, contatti.size());
    }

    @Test
    void testRimuoviContattoNonEsistente() {
        rubrica.aggiungiContatto(contatto1);
        rubrica.rimuoviContatto(contatto2);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertEquals(1, contatti.size());
    }

    @Test
    void testContattiDopoAggiunta() {
        rubrica.aggiungiContatto(contatto1);
        rubrica.aggiungiContatto(contatto2);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertEquals(2, contatti.size());
        assertTrue(contatti.contains(contatto1));
        assertTrue(contatti.contains(contatto2));
    }

    @Test
    void testAggiungiContattoRimuoviContatto() {
        rubrica.aggiungiContatto(contatto1);
        rubrica.aggiungiContatto(contatto2);
        rubrica.rimuoviContatto(contatto1);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertEquals(1, contatti.size());
        assertTrue(contatti.contains(contatto2));
        assertFalse(contatti.contains(contatto1));
    }

    @Test
    void testRimuoviContattoDaRubricaVuota() {
        rubrica.rimuoviContatto(contatto1);
        List<ContattoV2> contatti = rubrica.getContatti();
        assertTrue(contatti.isEmpty());
    }
}
