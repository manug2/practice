package extras.merchant.galaxy;


public enum RomanNumbers {

    I("I", 1), V("V", 5), X("X", 10), L("L", 50),
    C("C", 100), D("D", 500), M("M", 1000)
    ;

    private final String s;
    private final int decimal;

    RomanNumbers(String s, int decimal) {
        this.s = s;
        this.decimal = decimal;
    }

    public int getDecimal() {
        return decimal;
    }

}
