package com.manug2.practice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestCostRequestParser {

    @Before public void setUp() {
        GalacticToRomanParser galacticToRomanParser = new GalacticToRomanParser();
        galacticToRomanParser.parse("glob is I");
        galacticToRomanParser.parse("prok is V");
        galacticToRomanParser.parse("pish is X");
        galacticToRomanParser.parse("tegj is L");

        CostPerUnitParser costPerUnitParser = new CostPerUnitParser(galacticToRomanParser);
        costPerUnitParser.parse("glob glob Silver is 34 Credits");

        parser = new CostRequestParser(galacticToRomanParser, costPerUnitParser);
    }

    private CostRequestParser parser;

    @Test public void should_evaluate_cost_when_request_is_valid() {
        assertEquals(68.0
                , parser.evaluate("how many Credits is glob prok Silver ?"), 0.000001);
    }
    @Test public void should_parse_cost_when_request_is_valid() {
        assertTrue(parser.parse("how many Credits is glob prok Silver ?"));
    }

    @Test
    public void should_not_evaluate_when_request_is_invalid() {
        assertFalse(parser.parse("how many stars is glob prok Silver ?"));
    }

    @Test
    public void should_not_evaluate_when_request_has_invalid_item() {
        assertFalse(parser.parse("how many stars is glob prok Spoons ?"));
    }
    @Test
    public void should_not_evaluate_when_request_has_invalid_galatic_number() {
        assertFalse(parser.parse("how many stars is merci Spoons ?"));
    }

}
