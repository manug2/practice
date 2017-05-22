package extras.merchant.galaxy;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by maverick on 5/23/2016.
 */
public class TestMerchant2 {

    @Before public void setUp() {
        lines.add("glob is I");
        lines.add("prok is V");
        lines.add("pish is X");
        lines.add("tegj is L");
        lines.add("glob glob Silver is 34 Credits");
        lines.add("glob prok Gold is 57800 Credits");
        lines.add("pish pish Iron is 3910 Credits");
        lines.add("how much is pish tegj glob glob ?");
        lines.add("how many Credits is glob prok Silver ?");
        lines.add("how many Credits is glob prok Gold ?");
        lines.add("how many Credits is glob prok Iron ?");
        lines.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

        output.add("pish tegj glob glob is 42");
        output.add("glob prok Silver is 68 Credits");
        output.add("glob prok Gold is 57800 Credits");
        output.add("glob prok Iron is 782 Credits");
        output.add("I have no idea what you are talking about");


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
    List<String> lines = new ArrayList<String>(30);
    List<String> output = new ArrayList<String>(10);

    @Test public void should_generate_expected_output() {
        List<String> result = new ArrayList<String>(10);
        for (String line : lines) {
            append(result, merchant.analyzeAndRespond(line));
        }
        assertArrayEquals(output.toArray(), result.toArray());
    }

    private void append(List<String> result, String response) {
        if (response.length()>0)
            result.add(response);
    }

}
