package extras.merchant.galaxy.roman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by maverick on 6/2/2016.
 */
public class TestRomanCombinationRules {

    private RomanCombinatinRule rule;

    @Test
    public void should_have_combination_rules() {
        new RomanCombinatinRule();
    }

    @Before public void setUp() {
        this.rule = new RomanCombinatinRule();
    }

    @Test public void XXXX_should_be_invalid() {
        assertFalse(rule.isValid("XXXX"));
    }
    @Test public void XXX_should_be_valid() {
        assertTrue(rule.isValid("XXX"));
    }
    @Test public void XXXIX_should_be_valid() {
        assertTrue(rule.isValid("XXXIX"));
    }
    @Test public void CCCC_should_be_invalid() {
        assertFalse(rule.isValid("CCCC"));
    }
    @Test public void CCC_should_be_valid() {
        assertTrue(rule.isValid("CCC"));
    }
    @Test public void CCCLC_should_be_valid() {
        assertTrue(rule.isValid("CCCLC"));
    }
    @Test public void IIII_should_be_invalid() {
        assertFalse(rule.isValid("IIII"));
    }
    @Test public void III_should_be_valid() {
        assertTrue(rule.isValid("III"));
    }
    @Test public void II_should_be_valid() {
        assertTrue(rule.isValid("II"));
    }
    @Test public void MMMM_should_be_invalid() {
        assertFalse(rule.isValid("MMMM"));
    }
    @Test public void MMM_should_be_valid() {
        assertTrue(rule.isValid("MMM"));
    }
    @Test public void MMMCM_should_be_valid() {
        assertTrue(rule.isValid("MMMCM"));
    }
    @Test public void VV_should_be_invalid() {
        assertFalse(rule.isValid("VV"));
    }
    @Test public void LL_should_be_invalid() {
        assertFalse(rule.isValid("LL"));
    }
    @Test public void DD_should_be_invalid() {
        assertFalse(rule.isValid("DD"));
    }
}
