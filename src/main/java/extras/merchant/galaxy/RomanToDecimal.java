package extras.merchant.galaxy;


import extras.merchant.galaxy.roman.RomanConverter;
import extras.merchant.galaxy.roman.RomanConverterFactory;
import extras.merchant.galaxy.roman.RomanValidator;
import extras.merchant.galaxy.roman.RomanValidatorFactory;

public class RomanToDecimal {

    final static RomanValidator romanValidator = new RomanValidatorFactory().build();
    final static RomanConverter romanConverter = new RomanConverterFactory().build();

    public static int convert(String romanWord) {
        try {
            return RomanNumbers.valueOf(romanWord).getDecimal();
        } catch (IllegalArgumentException e) {

            if (romanValidator.isValid(romanWord))
                return romanConverter.convert(romanWord);
            else
                throw new RuntimeException(
                    String.format("not a valid roman number '%s'", romanWord));
        }
    }

}
