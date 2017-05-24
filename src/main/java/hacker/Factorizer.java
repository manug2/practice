package hacker;

import java.util.ArrayList;
import java.util.List;

public class Factorizer {

    final long N, B;

    public Factorizer(long n, long b) {
        N = n;
        B = b;
    }

    public List<long[]> factors() {
        List<long[]> factorSets = new ArrayList<long[]>();
        if (B==1) {
            factorSets.add(new long[] {N});
        } else if (B==2) {
            if (N>1)
                factorSets.add(new long[] {1, N-1});
        } else {

        }

        return factorSets;
    }
}
