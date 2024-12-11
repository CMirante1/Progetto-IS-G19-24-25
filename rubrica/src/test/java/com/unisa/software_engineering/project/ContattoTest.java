import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.unisa.software_engineering.project.model.Contatto;
import com.unisa.software_engineering.project.exceptions.InfoContattoException;

class ContattoTest {

    @Test
    void testContattoConstructor() throws InfoContattoException {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"test@example.com", "test2@example.com", "test3@example.com"};
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto = new Contatto("John", "Doe", numeri, emails, image);
        
        assertNotNull(contatto);
        assertEquals("John Doe", contatto.getNomeCompleto());
        assertArrayEquals(numeri, contatto.getNumeriDiTelefono());
        assertArrayEquals(emails, contatto.getEmails());
    }

    @Test
    void testModificaContatto() throws InfoContattoException {
        String[] numeri = {"1234567890"};
        String[] emails = {"test@example.com"};
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto = new Contatto("John", "Doe", numeri, emails, image);

        String[] nuoviNumeri = {"2223334444"};
        String[] nuoveEmails = {"new@example.com"};
        BufferedImage nuovaImmagine = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        
        contatto.modificaContatto("Jane", "Smith", nuoviNumeri, nuoveEmails, nuovaImmagine);

        assertEquals("Jane Smith", contatto.getNomeCompleto());
        assertArrayEquals(nuoviNumeri, contatto.getNumeriDiTelefono());
        assertArrayEquals(nuoveEmails, contatto.getEmails());
    }

    @Test
    void testGetImmagineProfilo() throws InfoContattoException, IOException {
        String[] numeri = {"1234567890"};
        String[] emails = {"test@example.com"};
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto = new Contatto("John", "Doe", numeri, emails, image);
        
        BufferedImage immagine = contatto.getImmagineProfilo();
        assertNotNull(immagine);
        assertEquals(100, immagine.getWidth());
        assertEquals(100, immagine.getHeight());
    }

    @Test
    void testCompareTo() throws InfoContattoException {
        String[] numeri1 = {"1234567890"};
        String[] emails1 = {"test1@example.com"};
        BufferedImage image1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto1 = new Contatto("John", "Doe", numeri1, emails1, image1);

        String[] numeri2 = {"9876543210"};
        String[] emails2 = {"test2@example.com"};
        BufferedImage image2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Contatto contatto2 = new Contatto("Jane", "Doe", numeri2, emails2, image2);

        assertTrue(contatto1.compareTo(contatto2) < 0);
    }
}
