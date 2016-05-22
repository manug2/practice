package com.manug2.practice;


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

    public static boolean isValid(String word) {
        try {
            return RomanNumbers.valueOf(word) != null;
        } catch (IllegalArgumentException e) {
            return word.length() > 1 && checkRulesForMultiCharacterWord(word);
        }
    }

    private static boolean checkRulesForMultiCharacterWord(String word) {
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
