package graphs;

import java.util.*;

public class StringGraph {
    public final List<String> V;

    public StringGraph() {
        V = new ArrayList<>();
        E = new HashMap<>();
    }

    final private Map<String, List<String>> E;

    public void add(String s, String t) {
        if (! V.contains(s))
            V.add(s);

        if (! V.contains(t))
            V.add(t);

        if (!E.containsKey(s))
            E.put(s, new ArrayList<>());
        E.get(s).add(t);
    }

    public StringGraph transpose() {
        StringGraph gt = new StringGraph();
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
        return this.V.size();
    }
}
