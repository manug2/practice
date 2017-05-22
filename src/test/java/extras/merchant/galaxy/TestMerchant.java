package extras.merchant.galaxy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMerchant {

    @Before public void setUp() {
        GalacticToRomanParser galacticToRomanParser = new GalacticToRomanParser();
        GalacticToDecimalParser galacticToDecimalParser = new GalacticToDecimalParser(galacticToRomanParser);
        CostPerUnitParser costPerUnitParser = new CostPerUnitParser(galacticToRomanParser);
        CostRequestParser costEvaluator  = new CostRequestParser(galacticToRomanParser, costPerUnitParser);

        merchant = new Merchant();
        merchant.withAnalyzer(galacticToRomanParser);
        merchant.withAnalyzer(galacticToDecimalParser);
        merchant.withAnalyzer(costPerUnitParser);
        merchant.withAnalyzer(costEvaluator);
    }
    private Merchant merchant;

    @Test public void should_generate_empty_response_when_input_is_glob_is_I() {
        assertEquals("", merchant.analyzeAndRespond("glob is I"));
    }

    @Test public void should_generate_no_idea_response_when_incorrect_input() {
        assertEquals("I have no idea what you are talking about"
                , merchant.analyzeAndRespond("how s the weather"));
    }

    @Test public void should_generate_no_idea_response_when_input_is_glob_glob_Silver_is_34_Credits() {
        assertEquals("I have no idea what you are talking about"
                , merchant.analyzeAndRespond("glob glob Silver is 34 Credits"));
    }

    @Test public void should_generate_empty_response_when_input_is_glob_glob_Silver_is_34_Credits() {
        merchant.analyzeAndRespond("glob is I");
        assertEquals("", merchant.analyzeAndRespond("glob glob Silver is 34 Credits"));
    }

    @Test public void should_generate_empty_response_when_input_is_how_many_Credits_is_glob_prok_Silver() {
        String response = merchant.analyzeAndRespond("glob is I");
        response += merchant.analyzeAndRespond("prok is V");
        response += merchant.analyzeAndRespond("glob glob Silver is 34 Credits");
        response += merchant.analyzeAndRespond("how many Credits is glob prok Silver ?");
        assertEquals("glob prok Silver is 68 Credits", response);
    }


}
