package graphs;

import java.util.*;

public class StringDFS {
    public final StringGraph g;

    public StringDFS(StringGraph g) {
        this.g = g;
    }

    public List<String> getSCCroots(StringForest f) {
        StringGraph gt = g.transpose();
        StringForest f2 = new StringForest(g.numOfVertices());

        for (String u : f.finishOrder) {
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

    public StringForest visit() {
        StringForest f = new StringForest(g.numOfVertices());
        for (String u : g.V)
            if (f.isWhite(u))
                visit(g, f, u);
        return f;
    }

    private static void visit(StringGraph g, StringForest f, String u) {
        f.start(u);
        for (String v : g.adjacencies(u)) {
            if (f.isWhite(v)) {
                f.parents.put(v, u);
                visit (g,f,v);
            }
        }
        f.end(u);
    }

    public int findLongestSCC() {
        StringForest f = visit();
        StringGraph gt = g.transpose();
        StringForest f2 = new StringForest(g.numOfVertices());

        for (String u : f.finishOrder) {
            if (f2.isWhite(u)) {
                visit(gt, f2, u);
            }
        }

        //String largestSCC=null;
        int longest = 0;
        for (String u : g.V) {
            if (!f2.parents.containsKey(u)) {
                int size = f2.ends.get(u) - f2.starts.get(u);
                if (size>longest) {
                    longest = size;
                    //largestSCC = u;
                }
            }
        }
        return (longest+1)/2;
    }
}

class StringForest {
    final public Map<String, String> parents;
    final public Map<String, Integer> starts, ends;
    final public Map<String, Color> colors;
    final public Stack<String> finishOrder = new Stack<>();

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
        colors.put(u, Color.BLACK);
        finishOrder.push(u);
    }
    public boolean isWhite(String u) {
        return ! colors.containsKey(u) || colors.get(u)==Color.WHITE;
    }
}
