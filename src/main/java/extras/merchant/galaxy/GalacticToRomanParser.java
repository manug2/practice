package extras.merchant.galaxy;



import extras.merchant.galaxy.roman.RomanValidator;
import extras.merchant.galaxy.roman.RomanValidatorFactory;

import java.util.HashMap;
import java.util.Map;

public class GalacticToRomanParser implements TextAnalyzer<RomanNumbers> {

    Map<String, RomanNumbers> map = new HashMap<String, RomanNumbers>();
    final RomanValidator romanValidator = new RomanValidatorFactory().build();

    public boolean parse(String s) {
        String[] words = s.split(" ");
        boolean canParse = words.length==3 && "is".equals(words[1])
                && romanValidator.isValid(words[2]);
        if (canParse)
            map.put(words[0], RomanNumbers.valueOf(words[2]));
        return canParse;
    }

    public RomanNumbers evaluate(String glob) {
        if (map.containsKey(glob))
            return map.get(glob);
        else
            throw new RuntimeException(
                String.format("Unknown galactic number '%s'", glob));
    }

    public String respond(String input) {
        return "";
    }
}
