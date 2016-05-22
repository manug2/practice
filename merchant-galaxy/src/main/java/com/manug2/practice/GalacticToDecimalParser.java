package com.manug2.practice;


public class GalacticToDecimalParser {

    final GalacticToRomanParser galacticToRoman;

    public GalacticToDecimalParser(GalacticToRomanParser galacticToRoman) {
        this.galacticToRoman = galacticToRoman;
    }

    public boolean parse(String s) {
        return (s.startsWith("how much is ") && s.endsWith(" ?"));
    }

    public Integer evaluate(String s) {
        String[] words = s.split(" ");
        String romanWord = getRomanNumber(words);
        return RomanToDecimal.convert(romanWord);
    }

    private String getRomanNumber(String[] words) {
        String romanWord = "";
        for (int i=3; i < words.length-1; i++)
            romanWord += galacticToRoman.evaluate(words[i]);
        return romanWord;
    }

}
