package extras;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPrefixToPostfix {

    PrefixToPostfix converter = new PrefixToPostfix();

    @Test public void should_covert_simple() {
        assertEquals("12+", converter.convert("+12"));
    }

    @Test public void should_covert_simplex2() {
        assertEquals("ABC*+D+", converter.convert("++A*BCD"));
    }

    @Test public void should_covert_2() {
        assertEquals("AB*CD*+", converter.convert("+*AB*CD"));
    }
}
