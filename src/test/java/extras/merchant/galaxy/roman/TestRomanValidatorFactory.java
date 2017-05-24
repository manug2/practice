package extras.merchant.galaxy.roman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestRomanValidatorFactory {

    @Test
    public void should_instantiate_Roman_Validator_Factory() {
        assertNotNull(new RomanValidatorFactory());
    }

    @Test
    public void should_produce_Roman_Validator() {
        assertNotNull(new RomanValidatorFactory().build());
    }

    @Test
    public void should_produce_Roman_Validator_type() {
        assertEquals(RomanValidator.class, new RomanValidatorFactory().build().getClass());
    }

}
