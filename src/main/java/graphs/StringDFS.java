package graphs;

import java.util.*;

public class StringDFS {
    public List<String> getSCCroots(StringGraph g, StringForest f) {
        StringGraph gt = g.transpose();
        StringForest f2 = new StringForest(g.numOfVertices());

        for (String u : f.getNodesOrderedByDescendingEndTimes()) {
            if (f2.isWhite(u)) {
                visit(gt, f2, u);
            }
        }

        List<String> roots = new ArrayList<>();
        for (String u : g.V)
            if (! f2.parents.containsKey(u))
                roots.add(u);

        return roots;
    }
    public StringForest visit(StringGraph g) {
        StringForest f = new StringForest(g.numOfVertices());
        for (String u : g.V)
            if (f.isWhite(u))
                visit(g, f, u);
        return f;
    }
    private void visit(StringGraph g, StringForest f, String u) {
        f.start(u);
        for (String v : g.adjacencies(u)) {
            if (f.isWhite(v)) {
                f.parents.put(v, u);
                visit (g,f,v);
            }
        }
        f.end(u);
    }
}

class StringForest {
    final public Map<String, String> parents;
    final public Map<String, Integer> starts, ends;
    final public TreeMap<Integer, String> ends2nodes = new TreeMap<>();
    final public Map<String, Color> colors;
    private int time=0;
    public StringForest (int n) {
        parents = new HashMap<>(n+1);
        starts = new HashMap<>(n+1);
        ends = new HashMap<>(n+1);
        colors = new HashMap<>(n+1);
    }
    public void start(String u) {
        starts.put(u, ++time);
        colors.put(u, Color.GRAY);
    }
    public void end(String u) {
        final int t = ++time;
        ends.put(u, t);
        ends2nodes.put(t, u);
        colors.put(u, Color.BLACK);
    }
    public boolean isWhite(String u) {
        return Color.WHITE.equals(colors.get(u));
    }
    public List<String> getNodesOrderedByDescendingEndTimes() {
        List<String> nodes = new ArrayList<>(ends.size());
        for (int t : ends2nodes.descendingKeySet()) {
            nodes.add(ends2nodes.get(t));
        }
        return nodes;
    }
}
