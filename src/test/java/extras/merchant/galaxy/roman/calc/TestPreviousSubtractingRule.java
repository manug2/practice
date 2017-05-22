package extras.merchant.galaxy.roman.calc;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestPreviousSubtractingRule {


    private PreviousSubtractingRule rule;

    @Before
    public void setUp() {
        this.rule = new PreviousSubtractingRule();
    }

    @Test public void should_extract_correct_char_from_start() {
        assertEquals(9, rule.nextGoodWordValue("IX", 0).intValue());
    }

    @Test public void should_give_correct_count_for_first_char() {
        assertEquals(2, rule.nextGoodWordLength("IX", 0));
    }

    @Test public void should_extract_correct_char_for_second_char() {
        assertEquals(9, rule.nextGoodWordValue("XIX", 1).intValue());
    }

    @Test public void should_give_correct_count_for_second_char() {
        assertEquals(2, rule.nextGoodWordLength("XIX", 1));
    }

    @Test public void should_not_extract_when_single_case_at_start() {
        assertEquals(0, rule.nextGoodWordValue("II", 0).intValue());
    }

    @Test public void should_give_0_count_when_single_case_at_start() {
        assertEquals(0, rule.nextGoodWordLength("II", 0));
    }

    @Test public void should_not_extract_when_single_case_at_end() {
        assertEquals(0, rule.nextGoodWordValue("II", 1).intValue());
    }

    @Test public void should_give_0_count_when_single_case_at_end() {
        assertEquals(0, rule.nextGoodWordLength("II", 1));
    }

    @Test public void should_extract_correct_value_of_XL() {
        assertEquals(40, rule.nextGoodWordValue("XL", 0).intValue());
    }

}
