package extras.merchant.galaxy;


import extras.merchant.galaxy.roman.RomanConverterFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestRomanToDecimal {

    @Parameterized.Parameters(name="{1}-{0}")
    public static List<Object[]> parameters() {
        List<Object[]> list = new ArrayList<Object[]>(20);
        list.add(new Object[] {1, "I"});
        list.add(new Object[] {5, "V"});
        list.add(new Object[] {10, "X"});
        list.add(new Object[] {50, "L"});
        list.add(new Object[] {100, "C"});
        list.add(new Object[] {500, "D"});
        list.add(new Object[] {1000, "M"});
        list.add(new Object[] {2006, "MMVI"});
        list.add(new Object[] {2000, "MM"});
        list.add(new Object[] {1944, "MCMXLIV"});
        list.add(new Object[] {2, "II"});
        list.add(new Object[] {4, "IV"});
        list.add(new Object[] {20, "XX"});
        list.add(new Object[] {42, "XLII"});
        return list;
    }

    @Test public void should_convert_to_expected_decimal() {
        assertEquals(expected,
                new RomanConverterFactory().build().convert(input));
    }

    public TestRomanToDecimal(int expected, String input) {
        this.expected = expected;
        this.input = input;
    }

    private final int expected;
    private final String input;

}
