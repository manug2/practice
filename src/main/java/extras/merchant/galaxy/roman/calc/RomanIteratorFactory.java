package extras.merchant.galaxy.roman.calc;

/**
 * Created by maverick on 6/3/2016.
 */
public class RomanIteratorFactory {
    public RomanIterator build(String word) {
        return new RomanIterator(word)
                .withRule(new PreviousSubtractingRule())
                .withRule(new SingleCharExtractionRule())
                ;
    }
}
