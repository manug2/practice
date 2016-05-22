package com.manug2.practice;

import java.util.HashMap;
import java.util.Map;



public class CostPerUnitParser {

    final GalacticToRomanParser galacticToRoman;
    private Map<String, Double> map;

    public CostPerUnitParser(GalacticToRomanParser galacticToRoman) {
        this.galacticToRoman = galacticToRoman;
        map = new HashMap<String, Double>();
    }

    public boolean parse(String s) {
        String[] words = s.split(" ");
        if (words.length < 5)
            return false;
        if (!"Credits".equals(words[words.length-1]))
            return false;

        final int cost;
        try {
            cost = Integer.parseInt(words[words.length-2]);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        if (!"is".equals(words[words.length-3]))
            return false;

        final String itemName = words[words.length -4];

        String romanWord="";
        for (int i=0; i<words.length-4; i++) {
            try {
                RomanNumbers roman = galacticToRoman.evaluate(words[i]);
                romanWord += roman.toString();
            } catch (RuntimeException e) {
                e.printStackTrace();
                return false;
            }
        }
        int num_of_units = RomanToDecimal.convert(romanWord);
        map.put(itemName, (1.0*cost)/num_of_units);
        return true;
    }

    public Double evaluate(String itemName) {
        return map.get(itemName);
    }

}
