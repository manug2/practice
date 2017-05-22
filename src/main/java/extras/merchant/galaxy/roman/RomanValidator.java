package extras.merchant.galaxy.roman;

import extras.merchant.galaxy.RomanNumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maverick on 6/2/2016.
 */
public class RomanValidator {

    private List<Rule> rules = new ArrayList<Rule>();

    public boolean isValid(String word) {
        try {
            return RomanNumbers.valueOf(word) != null;
        } catch (IllegalArgumentException e) {
            return word.length() > 1 && applyRules(word);
        }
    }

    private boolean applyRules(String word){
        for (Rule rule : rules)
            if (! rule.isValid(word))
                return false;
        return true;
    }

    public RomanValidator withRule(Rule rule) {
        this.rules.add(rule);
        return this;
    }
}
