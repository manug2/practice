package extras.merchant.galaxy.roman;

import extras.merchant.galaxy.roman.calc.RomanIterator;
import extras.merchant.galaxy.roman.calc.RomanIteratorFactory;

public class RomanConverter {

    protected RomanConverter() {}

    public int convert(String romanWord) {
        int total = 0;
        RomanIterator iterator = new RomanIteratorFactory().build(romanWord);
        while(iterator.hasNext())
            total += iterator.next();
        return total;
    }

}
