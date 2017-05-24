package extras.merchant.galaxy.roman.calc;

import extras.merchant.galaxy.RomanNumbers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maverick on 6/3/2016.
 */
public class TestSingleCharExtractionRule {


    private SingleCharExtractionRule rule;

    @Before
    public void setUp() {
        this.rule = new SingleCharExtractionRule();
    }

    @Test public void should_extract_correct_char_from_start() {
        assertEquals(RomanNumbers.X.getDecimal(), rule.nextGoodWordValue("XI", 0).intValue());
    }

    @Test public void should_give_correct_count_for_first_char() {
        assertEquals(1, rule.nextGoodWordLength("XI", 0));
    }

    @Test public void should_extract_correct_char_for_second_char() {
        assertEquals(RomanNumbers.I.getDecimal(), rule.nextGoodWordValue("XI", 1).intValue());
    }

    @Test public void should_give_correct_count_for_second_char() {
        assertEquals(1, rule.nextGoodWordLength("XI", 1));
    }
}
