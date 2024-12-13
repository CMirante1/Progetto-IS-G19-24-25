package com.unisa.software_engineering.project;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.unisa.software_engineering.project.model.Contatto;

public class ContattoTest {

    @Test
    void testCreazioneContattoValido(){
        try{
            Contatto contatto;
            contatto = new Contatto(
                "Giorgio","Ammazzapreti",
                new String[]{"294723946"},
                new String[]{"email@bella.it"},
                null);
            contatto = new Contatto(
                "Peppe","Mast",
                new String[]{"23985692", "19146849"},
                new String[]{"email.brutta@google.it", "forseunemail@gmoil.orm"},
                null);
            contatto = new Contatto(
                "Kekko","Ione",
                new String[]{},
                new String[]{"nonunemailgiuro@dominio.it", "anchequestaemail@ebrutta.it"},
                null);
            contatto = new Contatto(
                "Nome SecondoNome","",
                new String[]{"8935791", "123512351", "97564875273", "2985682935"},
                new String[]{""},
                null);
        }catch(Exception e){
            fail();
        }
    }
}
