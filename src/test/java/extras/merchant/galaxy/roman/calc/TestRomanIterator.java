package extras.merchant.galaxy.roman.calc;

import extras.merchant.galaxy.RomanNumbers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestRomanIterator {

    @Test
    public void should_have_next() {
        assertTrue(new RomanIteratorFactory().build("XX").hasNext());
    }

    @Test
    public void should_have_give_correct_char_upon_first_call_to_next() {
        assertEquals(RomanNumbers.X.getDecimal(), new RomanIteratorFactory().build("XX").next().intValue());
    }

    @Test
    public void should_have_give_correct_char_upon_second_call_to_next() {
        RomanIterator iter = new RomanIteratorFactory().build("XX");
        iter.next();
        assertEquals(RomanNumbers.X.getDecimal(), iter.next().intValue());
    }

    @Test
    public void should_have_give_correct_value_upon_first_call_to_next_when_previous_subtracting_case() {
        assertEquals(9, new RomanIteratorFactory().build("IX").next().intValue());
    }

    @Test
    public void should_have_give_correct_value_after_previous_subtracting_case() {
        RomanIterator iter = new RomanIteratorFactory().build("IXX");
        iter.next();
        assertEquals(10, iter.next().intValue());
    }

}
