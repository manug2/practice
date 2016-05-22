package com.manug2.practice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class TestGalacticToRomanParser {

    private GalacticToRomanParser parser;

    @Before public void setup() {
        this.parser = new GalacticToRomanParser();
    }
    @Test public void should_parse_when_input_is_valid_for_conversion() {
        assertTrue(parser.parse("glob is I"));
    }
    @Test public void should_not_parse_when_input_does_not_contain_word_is() {
        assertFalse(parser.parse("its new day"));
    }
    @Test public void should_not_parse_when_input_has_first_word_is() {
        assertFalse(parser.parse("is new day"));
    }
    @Test public void should_not_parse_when_input_has_less_than_3_words() {
        assertFalse(parser.parse("new day"));
    }
    @Test public void should_not_parse_when_input_has_more_than_3_words() {
        assertFalse(parser.parse("new is day today"));
    }
    @Test public void should_not_parse_when_input_and_invalid_Roman_like_number() {
        assertFalse(parser.parse("glob is Q"));
    }
    @Test public void should_parse_prok_is_V() {
        assertTrue(parser.parse("prok is V"));
    }
    @Test public void should_parse_pish_is_X() {
        assertTrue(parser.parse("pish is X"));
    }
    @Test public void should_parse_tegj_is_L() {
        assertTrue(parser.parse("tegj is L"));
    }
    @Test public void should_parse_and_convert_glob_to_I() {
        parser.parse("glob is I");
        assertEquals(RomanNumbers.I, parser.evaluate("glob"));
    }
    @Test public void should_parse_and_convert_prok_to_V() {
        parser.parse("prok is V");
        assertEquals(RomanNumbers.V, parser.evaluate("prok"));
    }
    @Test public void should_parse_and_convert_pish_to_X() {
        parser.parse("pish is X");
        assertEquals(RomanNumbers.X, parser.evaluate("pish"));
    }

}
