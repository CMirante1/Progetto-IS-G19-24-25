package com.unisa.software_engineering.project;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import com.unisa.software_engineering.project.model.Contatto;
import com.unisa.software_engineering.project.model.FileManager;
import com.unisa.software_engineering.project.model.Rubrica;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;


public class FileManagerTest {

    private Rubrica rubrica1, rubrica2, rubrica3, rubrica4;
    private final String testFile1 = "src/main/resources/test_rubrica1.dat";
    private final String testFile2 = "src/main/resources/test_rubrica2.dat";
    private final String testFile3 = "src/main/resources/test_rubrica3.dat";
    private final String testFile4 = "src/main/resources/test_rubrica4.dat";
    private final String testFile5 = "src/main/resources/test_rubrica5.dat";

    @BeforeEach
    public void setUp() throws InfoContattoException, IOException {
        rubrica1 = new Rubrica();
        rubrica1.aggiungiContatto(new Contatto("Mario", "Rossi", new String[]{"123456789"}, new String[]{"test1@example.com"}, null));
        rubrica1.aggiungiContatto(new Contatto("Luigi", "Bianchi", new String[]{"987654321"}, new String[]{"test2@example.com"}, null));
        rubrica1.aggiungiContatto(new Contatto("Anna", "Verdi", new String[]{"555555555"}, new String[]{"anna@example.com"}, null));

        rubrica2 = new Rubrica();
        rubrica2.aggiungiContatto(new Contatto("Giulia", "Verdi", new String[]{"111111111"}, new String[]{"giulia@example.com"}, null));
        rubrica2.aggiungiContatto(new Contatto("Marco", "Gialli", new String[]{"222333444"}, new String[]{"marco@example.com"}, null));
        rubrica2.aggiungiContatto(new Contatto("Paolo", "Neri", new String[]{"666777888"}, new String[]{"paolo@example.com"}, null));

        rubrica3 = new Rubrica();
        rubrica3.aggiungiContatto(new Contatto("Andrea", "Neri", new String[]{"222222222", "333333333"}, new String[]{"andrea@example.com"}, null));
        rubrica3.aggiungiContatto(new Contatto("Elisa", "Blu", new String[]{"444555666"}, new String[]{"elisa@example.com"}, null));
        rubrica3.aggiungiContatto(new Contatto("Chiara", "Rosa", new String[]{"777888999"}, new String[]{"chiara@example.com"}, null));

        rubrica4 = new Rubrica();
        rubrica4.aggiungiContatto(new Contatto("Sara", "Blu", new String[]{}, new String[]{"sara@example.com"}, null));
        rubrica4.aggiungiContatto(new Contatto("Luca", "Viola", new String[]{"999888777"}, new String[]{"luca@example.com"}, null));
        rubrica4.aggiungiContatto(new Contatto("Federico", "Marroni", new String[]{"666555444"}, new String[]{"federico@example.com"}, null));

        new File(testFile1).delete();
        new File(testFile2).delete();
        new File(testFile3).delete();
        new File(testFile4).delete();
        new File(testFile5).delete();
    }


    @Test
    public void testSalvaRubrica() throws IOException {
        FileManager.salvaRubrica(rubrica1);

        File file = new File("src/main/resources/rubrica.dat");
        assertTrue(file.exists());
    }

    @Test
    public void testCaricaRubricaFileNonEsistente() throws IOException, ClassNotFoundException {
        File fileBackup = new File("src/main/resources/rubrica.dat");
        if (fileBackup.exists()) {
            assertTrue(fileBackup.delete());
        }
        Rubrica nuovaRubrica = FileManager.caricaRubrica();

        assertNotNull(nuovaRubrica);
        assertEquals(0, nuovaRubrica.getContatti().size());
    }

    @Test
    public void testSalvaECaricaRubrica1() throws IOException, ClassNotFoundException {
        FileManager.salvaRubrica(rubrica1);
        Rubrica rubricaCaricata = FileManager.caricaRubrica();
        assertEquals(rubrica1.toString(), rubricaCaricata.toString());
    }

    @Test
    public void testSalvaECaricaRubrica2() throws IOException, ClassNotFoundException {
        FileManager.salvaRubrica(rubrica2);
        Rubrica rubricaCaricata = FileManager.caricaRubrica();
        assertEquals(rubrica2.toString(), rubricaCaricata.toString());
    }

    @Test
    public void testSalvaECaricaRubrica3() throws IOException, ClassNotFoundException {
        FileManager.salvaRubrica(rubrica3);
        Rubrica rubricaCaricata = FileManager.caricaRubrica();
        assertEquals(rubrica3.toString(), rubricaCaricata.toString());
    }

    @Test
    public void testSalvaECaricaRubrica4() throws IOException, ClassNotFoundException {
        FileManager.salvaRubrica(rubrica4);
        Rubrica rubricaCaricata = FileManager.caricaRubrica();
        assertEquals(rubrica4.toString(), rubricaCaricata.toString());
    }

    @Test
    public void testEsportaEImportaRubrica1() throws IOException, ClassNotFoundException, InfoContattoException {
        File fileVcf = new File("src/main/resources/rubrica1_test.vcf");
        FileManager.esportaContatti(rubrica1.getContatti(), fileVcf);

        Rubrica rubricaImportata = new Rubrica();
        FileManager.importaContatti(fileVcf, rubricaImportata);

        assertEquals(rubrica1.toString(), rubricaImportata.toString());
        fileVcf.delete();
    }

    @Test
    public void testEsportaEImportaRubrica2() throws IOException, ClassNotFoundException, InfoContattoException {
        File fileVcf = new File("src/main/resources/rubrica2_test.vcf");
        FileManager.esportaContatti(rubrica2.getContatti(), fileVcf);

        Rubrica rubricaImportata = new Rubrica();
        FileManager.importaContatti(fileVcf, rubricaImportata);

        assertEquals(rubrica2.toString(), rubricaImportata.toString());
        fileVcf.delete();
    }

    @Test
    public void testEsportaEImportaRubrica3() throws IOException, ClassNotFoundException, InfoContattoException {
        File fileVcf = new File("src/main/resources/rubrica3_test.vcf");
        FileManager.esportaContatti(rubrica3.getContatti(), fileVcf);

        Rubrica rubricaImportata = new Rubrica();
        FileManager.importaContatti(fileVcf, rubricaImportata);

        assertEquals(rubrica3.toString(), rubricaImportata.toString());
        fileVcf.delete();
    }

    @Test
    public void testEsportaEImportaRubrica4() throws IOException, ClassNotFoundException, InfoContattoException {
        File fileVcf = new File("src/main/resources/rubrica4_test.vcf");
        FileManager.esportaContatti(rubrica4.getContatti(), fileVcf);

        Rubrica rubricaImportata = new Rubrica();
        FileManager.importaContatti(fileVcf, rubricaImportata);

        assertEquals(rubrica4.toString(), rubricaImportata.toString());
        fileVcf.delete();
    }

    @AfterEach
    public void tearDown() {
        new File(testFile1).delete();
        new File(testFile2).delete();
        new File(testFile3).delete();
        new File(testFile4).delete();
        new File(testFile5).delete();
    }
}