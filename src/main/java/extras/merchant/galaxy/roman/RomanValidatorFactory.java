package extras.merchant.galaxy.roman;

public class RomanValidatorFactory {
    public RomanValidator build() {
        return new RomanValidator().withRule(new RomanCombinatinRule());
    }
}
interface Rule {
    boolean isValid(String word);
}