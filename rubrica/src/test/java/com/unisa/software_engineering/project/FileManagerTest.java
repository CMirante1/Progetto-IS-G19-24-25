package com.unisa.software_engineering.project;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.*;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import com.unisa.software_engineering.project.model.*;

class FileManagerTest {

    @Test
    void testSalvaRubrica() throws InfoContattoException {
        Rubrica rubrica = new Rubrica();
        ContattoV2 contatto = new ContattoV2("John", "Doe", new String[]{"123456789", "", ""}, new String[]{"john.doe@example.com", "", ""});
        rubrica.aggiungiContatto(contatto);

        FileManager.salvaRubrica(rubrica);

        File file = new File("res/rubrica.dat");
        assertTrue(file.exists());
    }

    @Test
    void testCaricaRubrica() {
        Rubrica rubrica = FileManager.caricaRubrica();
        assertNotNull(rubrica);
    }

    @Test
    void testEsportaContatti() throws IOException, InfoContattoException {
        Rubrica rubrica = new Rubrica();
        ContattoV2 contatto = new ContattoV2("Jane", "Doe", new String[]{"987654321", "", ""}, new String[]{"jane.doe@example.com", "", ""});
        rubrica.aggiungiContatto(contatto);

        File file = new File("test.vcf");
        FileManager.esportaContatti(rubrica.getContatti(), file);

        assertTrue(file.exists());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        assertTrue(line.contains("BEGIN:VCARD"));
        reader.close();
    }

    @Test
    void testImportaContatti() throws IOException {
        File file = new File("test.vcf");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("BEGIN:VCARD");
            writer.println("VERSION:4.0");
            writer.println("N:Doe;Jane;");
            writer.println("TEL:987654321");
            writer.println("EMAIL:jane.doe@example.com");
            writer.println("END:VCARD");
        }

        Rubrica rubrica = new Rubrica();
        FileManager.importaContatti(file, rubrica);

        assertEquals(1, rubrica.getContatti().size());
        assertEquals("Jane", rubrica.getContatti().get(0).getNome());
        assertEquals("Doe", rubrica.getContatti().get(0).getCognome());
    }

    @Test
    void testImportaContatti_InvalidFile() {
        File file = new File("invalid.vcf");
        try {
            FileManager.importaContatti(file, new Rubrica());
            fail("IOException should have been thrown");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Errore nella lettura del file!"));
        }
    }

    @Test
    void testCaricaRubrica_FileNotFound() {
        File file = new File("res/rubrica.dat");
        if (file.exists()) {
            file.delete();
        }

        Rubrica rubrica = FileManager.caricaRubrica();
        assertNotNull(rubrica);
    }

    @Test
    void testSalvaRubrica_ExceptionHandling() throws InfoContattoException, FileNotFoundException {
        Rubrica rubrica = new Rubrica();
        ContattoV2 contatto = new ContattoV2("Alice", "Smith", new String[]{"12345", "", ""}, new String[]{"alice.smith@example.com", "", ""});
        rubrica.aggiungiContatto(contatto);

        System.setOut(new PrintStream(new FileOutputStream("error.log")));
        FileManager.salvaRubrica(rubrica);

        File file = new File("res/rubrica.dat");
        assertFalse(file.exists());
    }
}
