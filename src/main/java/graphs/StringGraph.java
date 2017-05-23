package graphs;

import java.util.*;

public class StringGraph {
    public final List<String> V;
    final private int N;

    public StringGraph(int n) {
        this.N = n;
        V = new ArrayList<>(n);
        E = new HashMap<>(n, 1.0f);
    }

    final private Map<String, List<String>> E;

    public void add(String s, String t) {
        if (!E.containsKey(s))
            E.put(s, new ArrayList<>());
        E.get(s).add(t);
    }

    public StringGraph transpose() {
        StringGraph gt = new StringGraph(this.N);
        for (String s : E.keySet()) {
            for (String t : E.get(s))
                gt.add(t, s);
        }
        return gt;
    }

    public List<String> adjacencies(String s) {
        if (E.containsKey(s))
            return Collections.unmodifiableList(E.get(s));
        return new ArrayList<>();
    }

    public int numOfVertices() {
        return this.N;
    }
}
