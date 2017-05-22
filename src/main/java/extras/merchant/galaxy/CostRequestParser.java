package extras.merchant.galaxy;


public class CostRequestParser implements TextAnalyzer<Double> {
    private final TextAnalyzer<RomanNumbers> galacticParser;
    private final CostPerUnitParser costPerUnitParser;

    public CostRequestParser(TextAnalyzer<RomanNumbers> galacticParser,
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

        final String itemName = getItemName(words);
        String romanWord = getRomanNumber(words);
        final int num_of_units = RomanToDecimal.convert(romanWord);

        return costPerUnitParser.evaluate(itemName) * num_of_units;
    }

    public String respond(String input) {
        final String[] words = input.split(" ");
        String response = "";
        for (int i=4; i < words.length-2; i++)
            response += words[i] + " ";
        response += getItemName(words) + " is " + evaluate(input).intValue() + " Credits";
        return response;
    }

    private String getRomanNumber(String[] words) {
        String romanWord = "";
        for (int i=4; i < words.length-2; i++)
            romanWord += galacticParser.evaluate(words[i]);
        return romanWord;
    }

    private String getItemName(String[] words) {
        return words[words.length - 2];
    }

}
