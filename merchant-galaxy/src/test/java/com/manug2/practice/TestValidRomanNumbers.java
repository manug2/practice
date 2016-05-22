package com.manug2.practice;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestValidRomanNumbers {
    @Test public void Q_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("Q"));
    }
    @Test public void I_should_be_valid() {
        assertTrue(RomanNumbers.isValid("I"));
    }
    @Test public void Z_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("Z"));
    }
    @Test public void V_should_be_valid() {
        assertTrue(RomanNumbers.isValid("V"));
    }
    @Test public void X_should_be_valid() {
        assertTrue(RomanNumbers.isValid("X"));
    }
    @Test public void L_should_be_valid() {
        assertTrue(RomanNumbers.isValid("L"));
    }
    @Test public void C_should_be_valid() {
        assertTrue(RomanNumbers.isValid("C"));
    }
    @Test public void D_should_be_valid() {
        assertTrue(RomanNumbers.isValid("D"));
    }
    @Test public void M_should_be_valid() {
        assertTrue(RomanNumbers.isValid("M"));
    }
    @Test public void XXXX_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("XXXX"));
    }
    @Test public void XXX_should_be_valid() {
        assertTrue(RomanNumbers.isValid("XXX"));
    }
    @Test public void XXXIX_should_be_valid() {
        assertTrue(RomanNumbers.isValid("XXXIX"));
    }
    @Test public void CCCC_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("CCCC"));
    }
    @Test public void CCC_should_be_valid() {
        assertTrue(RomanNumbers.isValid("CCC"));
    }
    @Test public void CCCLC_should_be_valid() {
        assertTrue(RomanNumbers.isValid("CCCLC"));
    }
    @Test public void IIII_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("IIII"));
    }
    @Test public void III_should_be_valid() {
        assertTrue(RomanNumbers.isValid("III"));
    }
    @Test public void II_should_be_valid() {
        assertTrue(RomanNumbers.isValid("II"));
    }
    @Test public void MMMM_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("MMMM"));
    }
    @Test public void MMM_should_be_valid() {
        assertTrue(RomanNumbers.isValid("MMM"));
    }
    @Test public void MMMCM_should_be_valid() {
        assertTrue(RomanNumbers.isValid("MMMCM"));
    }
    @Test public void VV_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("VV"));
    }
    @Test public void LL_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("LL"));
    }
    @Test public void DD_should_be_invalid() {
        assertFalse(RomanNumbers.isValid("DD"));
    }
}
