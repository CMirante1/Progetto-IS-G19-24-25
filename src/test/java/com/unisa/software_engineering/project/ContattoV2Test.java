package com.unisa.software_engineering.project;

import com.unisa.software_engineering.project.exceptions.InfoContattoException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
;

public class ContattoV2Test {

    @Test
    public void testConstructorValidData() {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        try {
            ContattoV2 contatto = new ContattoV2("Giovanni", "Rossi", numeri, emails);
            assertNotNull(contatto);
            assertEquals("Giovanni", contatto.getNome());
            assertEquals("Rossi", contatto.getCognome());
            assertArrayEquals(numeri, contatto.getNumeri());
            assertArrayEquals(emails, contatto.getEmails());
        } catch (InfoContattoException ex) {
            fail("Exception should not be thrown for valid data");
        }
    }

    @Test
    public void testConstructorInvalidNome() {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        assertThrows(InfoContattoException.class, () -> {
            new ContattoV2("Giov@anni", "Rossi", numeri, emails);
        });
    }

    @Test
    public void testConstructorInvalidCognome() {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        assertThrows(InfoContattoException.class, () -> {
            new ContattoV2("Giovanni", "Ro$$i", numeri, emails);
        });
    }

    @Test
    public void testConstructorNullNomeCognome() {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        assertThrows(InfoContattoException.class, () -> {
            new ContattoV2(null, null, numeri, emails);
        });
    }

    @Test
    public void testConstructorInvalidPhoneNumber() {
        String[] numeri = {"123ABC", "0987654321", "1122334455"};
        String[] emails = {"email1@example.com", "email2@example.com", "email3@example.com"};
        assertThrows(InfoContattoException.class, () -> {
            new ContattoV2("Giovanni", "Rossi", numeri, emails);
        });
    }

    @Test
    public void testConstructorInvalidEmail() {
        String[] numeri = {"1234567890", "0987654321", "1122334455"};
        String[] emails = {"email1@example", "email2@example.com", "email3@example.com"};
        assertThrows(InfoContattoException.class, () -> {
            new ContattoV2("Giovanni", "Rossi", numeri, emails);
        });
    }

    @Test
    public void testCompareToEqual() {
        String[] numeri1 = {"1234567890", "0987654321", "1122334455"};
        String[] emails1 = {"email1@example.com", "email2@example.com", "email3@example.com"};
        String[] numeri2 = {"2234567890", "1987654321", "2122334455"};
        String[] emails2 = {"email4@example.com", "email5@example.com", "email6@example.com"};

        try {
            ContattoV2 contatto1 = new ContattoV2("Giovanni", "Rossi", numeri1, emails1);
            ContattoV2 contatto2 = new ContattoV2("Giovanni", "Rossi", numeri2, emails2);
            assertEquals(0, contatto1.compareTo(contatto2));
        } catch (InfoContattoException ex) {
            fail("Exception should not be thrown for valid data");
        }
    }

    @Test
    public void testCompareToDifferentCognome() {
        String[] numeri1 = {"1234567890", "0987654321", "1122334455"};
        String[] emails1 = {"email1@example.com", "email2@example.com", "email3@example.com"};
        String[] numeri2 = {"2234567890", "1987654321", "2122334455"};
        String[] emails2 = {"email4@example.com", "email5@example.com", "email6@example.com"};

        try {
            ContattoV2 contatto1 = new ContattoV2("Giovanni", "Rossi", numeri1, emails1);
            ContattoV2 contatto2 = new ContattoV2("Giovanni", "Bianchi", numeri2, emails2);
            assertTrue(contatto1.compareTo(contatto2) > 0);
        } catch (InfoContattoException ex) {
            fail("Exception should not be thrown for valid data");
        }
    }
}
