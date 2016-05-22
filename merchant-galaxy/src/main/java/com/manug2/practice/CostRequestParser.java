package com.manug2.practice;


public class CostRequestParser {
    private final GalacticToRomanParser galacticParser;
    private final CostPerUnitParser costPerUnitParser;

    public CostRequestParser(GalacticToRomanParser galacticParser,
                 CostPerUnitParser costPerUnitParser) {
        this.galacticParser = galacticParser;
        this.costPerUnitParser = costPerUnitParser;
    }

    public boolean parse(String s) {
        if (!s.startsWith("how many Credits is ") || !s.endsWith(" ?"))
            return false;

        final String[] words = s.split(" ");
        return (words.length >= 7);
    }

    public Double evaluate(String s) {
        final String[] words = s.split(" ");

        final String itemName = words[words.length-2];

        String romanWord = "";
        for (int i=4; i < words.length-2; i++)
            romanWord += galacticParser.evaluate(words[i]);

        final int num_of_units = RomanToDecimal.convert(romanWord);

        return costPerUnitParser.evaluate(itemName) * num_of_units;
    }

}
