package extras.merchant.galaxy.roman.calc;

import extras.merchant.galaxy.RomanNumbers;

public class SingleCharExtractionRule implements RomanIterator.ExtractionRule {

    public int nextGoodWordLength(String word, int current) {
        if (word.length() > current)
            return 1;
        else
            return 0;
    }

    public Integer nextGoodWordValue(String word, int current) {
        if (nextGoodWordLength(word, current) > 0)
            return RomanNumbers.valueOf(word.substring(current, current+1)).getDecimal();
        else
            return 0;
    }
}
