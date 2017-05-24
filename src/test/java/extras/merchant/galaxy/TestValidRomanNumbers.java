package extras.merchant.galaxy;

import extras.merchant.galaxy.roman.RomanValidator;
import extras.merchant.galaxy.roman.RomanValidatorFactory;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestValidRomanNumbers {

    RomanValidator validator = new RomanValidatorFactory().build();
    @Test public void Q_should_be_invalid() {
        assertFalse(validator.isValid("Q"));
    }
    @Test public void I_should_be_valid() {
        assertTrue(validator.isValid("I"));
    }
    @Test public void Z_should_be_invalid() {
        assertFalse(validator.isValid("Z"));
    }
    @Test public void V_should_be_valid() {
        assertTrue(validator.isValid("V"));
    }
    @Test public void X_should_be_valid() {
        assertTrue(validator.isValid("X"));
    }
    @Test public void L_should_be_valid() {
        assertTrue(validator.isValid("L"));
    }
    @Test public void C_should_be_valid() {
        assertTrue(validator.isValid("C"));
    }
    @Test public void D_should_be_valid() {
        assertTrue(validator.isValid("D"));
    }
    @Test public void M_should_be_valid() {
        assertTrue(validator.isValid("M"));
    }
    @Test public void XXXX_should_be_invalid() {
        assertFalse(validator.isValid("XXXX"));
    }
    @Test public void XXX_should_be_valid() {
        assertTrue(validator.isValid("XXX"));
    }
    @Test public void DD_should_be_invalid() {
        assertFalse(validator.isValid("DD"));
    }
}
