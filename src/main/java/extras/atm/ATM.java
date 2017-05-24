package extras.atm;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.String.format;

public class ATM {

    TreeMap<Integer, Integer> notes = new TreeMap<>();

    public Change extract(final int requestedSum) {
        int remainingAmount = requestedSum;
        Map<Integer, Integer> change = new HashMap<>();

        for (int denomination: notes.descendingKeySet()) {
            int a = calcNumOfNotes(denomination, remainingAmount);
            change.put(denomination, a);
            remainingAmount -= denomination * a;
        }

        if (remainingAmount>0)
            throw new RuntimeException(
                    format("cannot yield request amount '%s'", requestedSum));

        for (int denomination: change.keySet()) {
            decrement(denomination, change.get(denomination));
        }

        return new Change(change);
    }

    public int calcNumOfNotes(int denomination, int remainingAmount) {
        int q = remainingAmount / denomination;
        int inventory = notes.get(denomination);
        if (q < inventory)
            return q;
        else
            return inventory;
    }

    private void decrement(int denomination, int quantity) {
        if (quantity<=0)
            return ;
        if (! notes.containsKey(denomination) )
            throw new RuntimeException(format("note '%s' not in inventory", denomination));
        if (notes.get(denomination) < quantity)
            throw new RuntimeException(format("note '%s' not in inventory", denomination));
        notes.put(denomination, notes.get(denomination)-quantity);
    }

    public ATM load(int denomination, int quantity) {
        if (notes.containsKey(denomination))
            notes.put(denomination, notes.get(denomination+1));
        else
            notes.put(denomination, quantity);

        return this;
    }

    private boolean isAvailable(int denomination) {
        if (notes.containsKey(denomination))
            return notes.get(denomination) >= 1;
        else
            return false;
    }
}
