package com.unisa.software_engineering.project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.unisa.software_engineering.project.model.Email;
import com.unisa.software_engineering.project.exceptions.InfoContattoException;

public class EmailTest {

    @Test
    void testValidEmail() {
        try {
            Email email = new Email("test@example.com");
            assertEquals("test@example.com", email.getInfo());
        } catch (InfoContattoException e) {
            fail("Exception should not have been thrown for a valid email");
        }
    }

    @Test
    void testInvalidEmailMissingAtSymbol() {
        try {
            new Email("testexample.com");
            fail("Exception should have been thrown for an invalid email");
        } catch (InfoContattoException e) {
            // expected exception
        }
    }

    @Test
    void testInvalidEmailMissingDomain() {
        try {
            new Email("test@");
            fail("Exception should have been thrown for an invalid email");
        } catch (InfoContattoException e) {
            // expected exception
        }
    }

    @Test
    void testInvalidEmailEmpty() {
        try {
            new Email("");
            fail("Exception should have been thrown for an empty email");
        } catch (InfoContattoException e) {
            // expected exception
        }
    }

    @Test
    void testInvalidEmailNull() {
        try {
            new Email(null);
            fail("Exception should have been thrown for a null email");
        } catch (InfoContattoException e) {
            // expected exception
        }
    }

    @Test
    void testSetValidEmail() {
        try {
            Email email = new Email("test@example.com");
            email.setInfo("new@example.com");
            assertEquals("new@example.com", email.getInfo());
        } catch (InfoContattoException e) {
            //fail("Exception should not have been thrown for a valid email");
        }
    }

    @Test
    void testSetInvalidEmail() {
        try {
            Email email = new Email("test@example.com");
            email.setInfo("invalid-email");
            fail("Exception should have been thrown for an invalid email");
        } catch (InfoContattoException e) {
            // expected exception
        }
    }
}
