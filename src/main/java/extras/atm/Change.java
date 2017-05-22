package extras.atm;

import java.util.HashMap;
import java.util.Map;


public class Change {

    private Map<Integer, Integer> changes=new HashMap<>();

    public Change append(int denomination) {
        if (! changes.containsKey(denomination))
            changes.put(denomination, 1);
        else
            changes.put(denomination, changes.get(denomination)+1);
        return this;
    }

    public int getQuantity(int denomination) {
        if (changes.containsKey(denomination))
            return changes.get(denomination);
        else
            return 0;
    }
}

