package com.manug2.practice;

import java.util.HashMap;
import java.util.Map;


public class CostPerUnitParser implements TextAnalyzer<Double> {

    final TextAnalyzer<RomanNumbers> galacticToRoman;
    private Map<String, Double> map;

    public CostPerUnitParser(TextAnalyzer<RomanNumbers> galacticToRoman) {
        this.galacticToRoman = galacticToRoman;
        map = new HashMap<String, Double>();
    }

    public boolean parse(String s) {
        String[] words = s.split(" ");
        if (checkNumberOfWords(words)) return false;
        if (checkOccurenceOfCredits(words)) return false;
        if (checkOccurenceOfIs(words)) return false;

        final int cost;
        try {
            cost = Integer.parseInt(words[words.length-2]);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        final String itemName = getItemName(words);
        String romanWord = getRomanNumber(words);

        if (romanWord.length()==0) return false;

        int num_of_units = RomanToDecimal.convert(romanWord);
        map.put(itemName, (1.0*cost)/num_of_units);

        return true;
    }

    private String getRomanNumber(String[] words) {
        try {
            String romanWord="";
            for (int i=0; i<words.length-4; i++) {
                RomanNumbers roman = galacticToRoman.evaluate(words[i]);
                romanWord += roman.toString();
            }
            return romanWord;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getItemName(String[] words) {
        return words[words.length - 4];
    }

    private boolean checkOccurenceOfIs(String[] words) {
        return ! "is".equals(words[words.length - 3]);
    }

    private boolean checkOccurenceOfCredits(String[] words) {
        return ! "Credits".equals(words[words.length - 1]);
    }

    private boolean checkNumberOfWords(String[] words) {
        if (words.length < 5)
            return true;
        return false;
    }

    public Double evaluate(String itemName) {
        return map.get(itemName);
    }

    public String respond(String input) {
        return "";
    }

}
