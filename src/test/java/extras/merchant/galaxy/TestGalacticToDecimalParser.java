package extras.merchant.galaxy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class TestGalacticToDecimalParser {

    @Before public void setUp() {
        GalacticToRomanParser galacticToRomanParser = new GalacticToRomanParser();
        galacticToRomanParser.parse("glob is I");
        galacticToRomanParser.parse("prok is V");
        galacticToRomanParser.parse("pish is X");
        galacticToRomanParser.parse("tegj is L");
        parser = new GalacticToDecimalParser(galacticToRomanParser);
    }
    private GalacticToDecimalParser parser;

    @Test public void should_parse_a_valid_request() {
        assertTrue(parser.parse("how much is pish tegj glob glob ?"));
    }

    @Test
    public void should_not_parse_an_invalid_request() {
        assertFalse(parser.parse("how very much is pish tegj glob glob ?"));
    }

    @Test public void should_evaluate_a_valid_request() {
        assertEquals(new Integer(42), parser.evaluate("how much is pish tegj glob glob ?"));
    }

    @Test public void should_respond_to_a_valid_request() {
        assertEquals("pish tegj glob glob is 42", parser.respond("how much is pish tegj glob glob ?"));
    }

}
