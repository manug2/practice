package extras.merchant.galaxy.roman;


public class RomanCombinatinRule implements Rule {
    public boolean isValid(String word) {
        boolean result;

        result  = ! word.contains("IIII");
        result &= ! word.contains("XXXX");
        result &= ! word.contains("CCCC");
        result &= ! word.contains("MMMM");

        result &= ! word.contains("VV");
        result &= ! word.contains("LL");
        result &= ! word.contains("DD");

        return result;
    }
}
