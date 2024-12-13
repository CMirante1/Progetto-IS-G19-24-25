package com.unisa.software_engineering.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import com.unisa.software_engineering.project.model.*;
import com.unisa.software_engineering.project.exceptions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ContattoTest {

    private Contatto contatto;

    @Test
    void testCreazioneContattoValido1(){

        try{
            contatto = new Contatto(
                "Giorgio","Ammazzapreti",
                new String[]{"294723946"},
                new String[]{"email@bella.it"},
                new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
        }catch(Exception e){
            fail();
        }
    }

    @Test
    void testCreazioneContattoValido2(){

        try{
            contatto = new Contatto(
                "Peppe","Mast",
                new String[]{"23985692", "19146849"},
                new String[]{"email.brutta@google.it", "forseunemail@gmoil.orm"},
                null);
        }catch(Exception e){
            fail();
        }
    }

    @Test
    void testCreazioneContattoValido3(){

        try{
            contatto = new Contatto(
                "Kekko","Ione",
                new String[]{},
                new String[]{"nonunemailgiuro@dominio.it", "anchequestaemail@ebrutta.it"},
                null);
        }catch(Exception e){
            fail();
        }
    }

    @Test
    void testCreazioneContattoValido4(){

        try{
            contatto = new Contatto(
                "Nome SecondoNome","",
                new String[]{"8935791", "123512351", "97564875273", "2985682935"},
                new String[]{""},
                null);
        }catch(Exception e){
            fail();
        }
    }

    @Test
    void testCreazioneContattoNomeECognomeVuoti(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "","",
                new String[]{"8935791", "123512351", "97564875273", "2985682935"},
                new String[]{""},
                null);
        });
    }

    @Test
    void testCreazioneContattoConNomeSpeciale(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Cia€","Belo",
                new String[]{"2379695"},
                new String[]{""},
                null);
            contatto = new Contatto(
                "C|ao","Belo",
                new String[]{"2379695"},
                new String[]{""},
                null);
        });
    }

    @Test
    void testCreazioneContattoConCognomeSpeciale(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Ciao","Bel*",
                new String[]{"2379695"},
                new String[]{""},
                null);
            contatto = new Contatto(
                "Ciao","B//lo",
                new String[]{"2379695"},
                new String[]{""},
                null);
        });
    }

    @Test
    void testCreazioneContattoNumeroInvalido(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{"-23947553"},
                new String[]{""},
                null);
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{"2379AAA9534"},
                new String[]{""},
                null);
        });
    }

    @Test
    void testCreazioneContattoNumeriSurplus(){

        try{
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{"23947553", "0347683435", "345182352", "327516235"},
                new String[]{""},
                null);
        }catch(Exception e){
            fail();
        }
    }

    @Test
    void testCreazioneContattoEmailSenzaAt(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BellaQuestaEmail.com"},
                null);
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BruttaQuestaAltra"},
                null);
        });
    }

    @Test
    void testCreazioneContattoEmailDominioInvalida(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BellaQuestaEmail@.com"},
                null);
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BruttaQuestaAltra@kebab"},
                null);
        });
    }

    @Test
    void testCreazioneContattoEmailLocalPartInvalid(){

        assertThrows(InfoContattoException.class, () -> {
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BellaQu||estaEmail@hotmail.com"},
                null);
            contatto = new Contatto(
                "Ciao","Belo",
                new String[]{""},
                new String[]{"BruttaQu..estaAltra@gmail.it"},
                null);
        });
    }

    @Test
    void testModificaContatto() throws InfoContattoException, IOException{
        
        Rubrica rubrica = new Rubrica();
        String[] numeri = {"123456789"};
        String[] emails = {"email@example.com"};
        BufferedImage immagine = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto = new Contatto("Mario", "Rossi", numeri, emails, immagine);

        rubrica.aggiungiContatto(contatto);
        String[] nuoviNumeri = {"987654321"};
        String[] nuoveEmails = {"newemail@example.com"};
        BufferedImage nuovaImmagine = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);

        contatto.modificaContatto("Luigi", "Verdi", nuoviNumeri, nuoveEmails, nuovaImmagine);

        assertEquals("Luigi", contatto.getNome());
        assertEquals("Verdi", contatto.getCognome());
        assertEquals("987654321", contatto.getNumeri()[0]);
        assertEquals("newemail@example.com", contatto.getEmails()[0]);
    }

    @Test
    void testToString() throws InfoContattoException, IOException {
        contatto = new Contatto(
                "Marco","Bello",
                new String[]{"123456789"},
                new String[]{"email@mail.com"},
                null);
        String expected =
            "Marco Bello:\n" +
            "Tel:\n" +
            "123456789\n" +
            "\n" +
            "\n" +
            "Email:\n" +
            "email@mail.com\n" +
            "\n" +
            "\n" +
            "Image size: 0";

        assertEquals(expected, contatto.toString());
    }
}
