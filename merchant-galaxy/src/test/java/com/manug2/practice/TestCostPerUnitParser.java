package com.manug2.practice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestCostPerUnitParser {

    private CostPerUnitParser parser;

    @Before public void setUp() {
        GalacticToRomanParser galacticToRomanParser = new GalacticToRomanParser();
        galacticToRomanParser.parse("glob is I");
        galacticToRomanParser.parse("prok is V");
        galacticToRomanParser.parse("pish is X");
        parser = new CostPerUnitParser(galacticToRomanParser);
    }

    @Test public void should_parse_a_valid_text() {
        assertTrue(parser.parse("glob glob Silver is 34 Credits"));
    }
    @Test public void should_not_parse_text_of_4_words() {
        assertFalse(parser.parse("Silver is 34 Credits"));
    }
    @Test public void should_not_parse_text_of_3_words() {
        assertFalse(parser.parse("Silver is Credits"));
    }
    @Test public void should_not_parse_text_of_2_words() {
        assertFalse(parser.parse("34 Credits"));
    }
    @Test public void should_not_parse_text_of_1_word() {
        assertFalse(parser.parse("Silver"));
    }
    @Test public void should_not_parse_text_without_trailing_word_Credits() {
        assertFalse(parser.parse("glob glob Silver is 34"));
    }
    @Test public void should_not_parse_when_second_last_word_is_not_an_integer() {
        assertFalse(parser.parse("glob glob Silver is forty Credits"));
    }
    @Test public void should_not_parse_when_third_last_word_is_not_is() {
        assertFalse(parser.parse("glob glob Silver was 34 Credits"));
    }
    @Test public void should_give_cost_per_unit_when_glob_glob_Silver_is_34_Credits() {
        parser.parse("glob glob Silver is 34 Credits");
        assertEquals(17.0, parser.evaluate("Silver"), 0.000001);
    }
    @Test public void should_give_cost_per_unit_when_glob_prok_Gold_is_57800_Credits() {
        parser.parse("glob prok Gold is 57800 Credits");
        assertEquals(14450.0, parser.evaluate("Gold"), 0.000001);
    }
    @Test public void should_give_cost_per_unit_when_pish_pish_Iron_is_3910_Credits() {
        parser.parse("pish pish Iron is 3910 Credits");
        assertEquals(195.5, parser.evaluate("Iron"), 0.000001);
    }
}
