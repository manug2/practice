package extras.merchant.galaxy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maverick on 5/23/2016.
 */
public class Merchant {

    List<TextAnalyzer> analyzers = new ArrayList<TextAnalyzer>(5);
    public void withAnalyzer(TextAnalyzer analyzer) {
        analyzers.add(analyzer);
    }


    public String analyzeAndRespond(String input) {
        for (TextAnalyzer ta : analyzers) {
            try {
                if (ta.parse(input))
                    return ta.respond(input);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return "I have no idea what you are talking about";
            }
        }
        return "I have no idea what you are talking about";
    }

}
