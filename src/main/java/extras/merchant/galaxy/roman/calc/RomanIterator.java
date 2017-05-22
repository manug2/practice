package extras.merchant.galaxy.roman.calc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class RomanIterator implements Iterator<Integer> {

    final String word;
    private List<ExtractionRule> rules = new ArrayList<ExtractionRule>();

    public RomanIterator(String word) {
        this.word = word;
    }

    private int index=0;
    public boolean hasNext() {
        return index < word.length();
    }

    public Integer next() {
        if (rules.isEmpty())
            throw new IllegalStateException("RomanIterator setup with no rules");
        for (ExtractionRule rule : rules) {
            final int count = rule.nextGoodWordLength(word, index);
            if (count > 0) {
                int value = rule.nextGoodWordValue(word, index);
                index += count;
                return value;
            }
        }
        throw new IllegalStateException(
                String.format("cannot find next value for word [%s] current index [%d]",
                word, index));
    }

    public RomanIterator withRule(ExtractionRule rule) {
        this.rules.add(rule);
        return this;
    }

    public interface ExtractionRule {
        int nextGoodWordLength(String word, int current);
        Integer nextGoodWordValue(String word, int current);
    }

}


