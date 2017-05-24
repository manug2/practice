package extras.merchant.galaxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by maverick on 5/23/2016.
 */
public class MerchantRunner {

    public static void main(String args[]) throws IOException {
        if (args.length == 0)
            throw new IllegalArgumentException("Expecting a file path as input parameter to this program");

        Path filePath = Paths.get(args[0]);
        if (!Files.exists(filePath))
            throw new IllegalArgumentException(
                String.format("Could not find file at path [%s]", args[0]));

        Merchant merchant = buildMerchant();
        List<String> inputLines = Files.readAllLines(filePath);
        for (String line : inputLines) {
            String response = merchant.analyzeAndRespond(line);
            if (response.length() > 0)
                System.out.println(response);
        }

        //System.out.println("Press any key to finish!");
        System.in.read();
        System.exit(0);
    }

    private static Merchant buildMerchant() {
        GalacticToRomanParser galacticToRomanParser = new GalacticToRomanParser();
        GalacticToDecimalParser galacticToDecimalParser = new GalacticToDecimalParser(galacticToRomanParser);
        CostPerUnitParser costPerUnitParser = new CostPerUnitParser(galacticToRomanParser);
        CostRequestParser costEvaluator  = new CostRequestParser(galacticToRomanParser, costPerUnitParser);

        Merchant merchant = new Merchant();
        merchant.withAnalyzer(galacticToRomanParser);
        merchant.withAnalyzer(galacticToDecimalParser);
        merchant.withAnalyzer(costPerUnitParser);
        merchant.withAnalyzer(costEvaluator);

        return merchant;
    }
}
