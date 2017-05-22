package extras.merchant.galaxy.roman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestRomanConverterFactory {

    @Test
    public void should_instantiate_Roman_Validator_Factory() {
        assertNotNull(new RomanConverterFactory());
    }

    @Test
    public void should_produce_Roman_Validator() {
        assertNotNull(new RomanConverterFactory().build());
    }

    @Test
    public void should_produce_Roman_Validator_type() {
        assertEquals(RomanConverter.class, new RomanConverterFactory().build().getClass());
    }

}
