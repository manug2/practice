package extras.atm;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class ATM {

    Map<Integer, Integer> notes = new HashMap<>();

    public Change extract(final int requestedSum) {
        Change answer = new Change();
        int remainingAmount = requestedSum;

        while (remainingAmount > 0) {
            int intialRemaining = remainingAmount;

            remainingAmount -= calcAndDeduct(100, remainingAmount, answer);
            if (remainingAmount < intialRemaining)
                continue;
            remainingAmount -= calcAndDeduct(50, remainingAmount, answer);
            if (remainingAmount < intialRemaining)
                continue;
            remainingAmount -= calcAndDeduct(10, remainingAmount, answer);
            if (remainingAmount < intialRemaining)
                continue;

            remainingAmount -= calcAndDeduct(5, remainingAmount, answer);

            if (remainingAmount>0 && remainingAmount == intialRemaining)
                throw new RuntimeException("cannot yield request amount");
        }
        return answer;
    }

    public int calcAndDeduct(int denomination, int remainingAmount, Change answer) {
        int q = remainingAmount / denomination;
        decrement(denomination);
        answer.append(denomination);
        return q * denomination;
    }

    private void decrement(int denomination) {
        if (! notes.containsKey(denomination) )
            throw new RuntimeException(format("note '%s' not in inventory", denomination));
        if (notes.get(denomination)==0)
            throw new RuntimeException(format("note '%s' not in inventory", denomination));
        notes.put(denomination, notes.get(denomination)-1);
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
