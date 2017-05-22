package extras.merchant.galaxy.roman.calc;


import extras.merchant.galaxy.RomanNumbers;

import java.util.HashMap;
import java.util.Map;

public class PreviousSubtractingRule implements RomanIterator.ExtractionRule{
    public int nextGoodWordLength(String word, int current) {
        if (current == word.length()-1)
            return 0;
        String sub = extractTwoCharWord(word, current);
        if(mapForCouples.containsKey(sub))
            return sub.length();
        else
            return 0;
    }

    public Integer nextGoodWordValue(String word, int current) {
        if (current == word.length()-1)
            return 0;
        String sub = extractTwoCharWord(word, current);
        if(mapForCouples.containsKey(sub))
            return mapForCouples.get(sub);
        else
            return 0;
    }

    private String extractTwoCharWord(String word, int current) {
        return word.substring(current, current+2);
    }

    Map<String, Integer> mapForCouples;
    public PreviousSubtractingRule() {
        mapForCouples = new HashMap<String, Integer>();
        mapForCouples.put("IV", RomanNumbers.V.getDecimal() - RomanNumbers.I.getDecimal());
        mapForCouples.put("IX", RomanNumbers.X.getDecimal() - RomanNumbers.I.getDecimal());
        mapForCouples.put("CD", RomanNumbers.D.getDecimal() - RomanNumbers.C.getDecimal());
        mapForCouples.put("CM", RomanNumbers.M.getDecimal() - RomanNumbers.C.getDecimal());
        mapForCouples.put("XL", RomanNumbers.L.getDecimal() - RomanNumbers.X.getDecimal());
        mapForCouples.put("XC", RomanNumbers.C.getDecimal() - RomanNumbers.X.getDecimal());
    }

}
