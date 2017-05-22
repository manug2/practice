package extras.merchant.galaxy;


public class GalacticToDecimalParser implements TextAnalyzer<Integer> {

    final TextAnalyzer<RomanNumbers> galacticToRoman;

    public GalacticToDecimalParser(TextAnalyzer<RomanNumbers> galacticToRoman) {
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

    public String respond(String input) {
        String response = "";
        String[] words = input.split(" ");
        for (int i=3; i < words.length-1; i++)
            response += words[i] + " ";
        response += "is " + evaluate(input);
        return response;
    }

    private String getRomanNumber(String[] words) {
        String romanWord = "";
        for (int i=3; i < words.length-1; i++)
            romanWord += galacticToRoman.evaluate(words[i]);
        return romanWord;
    }

}
