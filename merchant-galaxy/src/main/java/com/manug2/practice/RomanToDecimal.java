package com.manug2.practice;


public class RomanToDecimal {

    public static int convert(String romanWord) {
        try {
            return RomanNumbers.valueOf(romanWord).getDecimal();
        } catch (IllegalArgumentException e) {
            if (RomanNumbers.isValid(romanWord))
                return convertAfterSplitting(romanWord);
            else
                throw new RuntimeException(
                    String.format("not a valid roman number '%s'", romanWord));
        }
    }

    private static int convertAfterSplitting(String roman) {
        int total = 0;

        for (int i=0; i < roman.length(); i++) {
            String sub = "" + roman.charAt(i);
            RomanNumbers first = RomanNumbers.valueOf(sub);

            if (hasMoreCharacters(roman, i)) {
                String sub2 = "" + roman.charAt(i + 1);
                RomanNumbers second = RomanNumbers.valueOf(sub2);
                if (needsSubtraction(first, second)) {
                    int subTotal = -1 * first.getDecimal() + second.getDecimal();
                    total += subTotal;
                    i++;
                } else {
                    total += first.getDecimal();
                }
            } else {
                total += first.getDecimal();
            }

        }

        return total;
    }

    private static boolean hasMoreCharacters(String roman, int i) {
        return i < roman.length()-1;
    }

    private static boolean needsSubtraction(RomanNumbers first, RomanNumbers second) {
        switch (first) {
            case V: return false;
            case L: return false;
            case D: return false;
            case M: return false;

            case C:
                return checkForC(second);
            case X:
                return checkForX(second);
            case I:
                return checkForI(second);
            default: return false;
        }
    }

    private static boolean checkForI(RomanNumbers second) {
        switch (second) {
            case V: return true;
            case X: return true;
            default: return false;
        }
    }

    private static boolean checkForX(RomanNumbers second) {
        switch (second) {
            case L: return true;
            case C: return true;
            default: return false;
        }
    }

    private static boolean checkForC(RomanNumbers second) {
        switch (second) {
            case D: return true;
            case M: return true;
            default: return false;
        }
    }

}
