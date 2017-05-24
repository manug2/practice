package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DFS {
    public List<Integer> getSCCroots(Graph g, Forest f) {
        Graph gt = g.transpose();
        Forest f2 = new Forest(g.V);

        for (int u : f.getNodesOrderedByDescendingEndTimes()) {
            if (f2.isWhite(u)) {
                visit(gt, f2, u);
            }
        }

        List<Integer> roots = new ArrayList<>();
        for (int u=1 ; u<=g.V; u++)
            if (f2.parents[u] == 0)
                roots.add(u);

        return roots;
    }
    public Forest visit(Graph g) {
        Forest f = new Forest(g.V);
        for (int u=1 ; u<=g.V; u++)
            if (f.isWhite(u))
                visit(g, f, u);
        return f;
    }
    private void visit(Graph g, Forest f, int u) {
        f.start(u);
        for (int v : g.adjacencies(u)) {
            if (f.isWhite(v)) {
                f.parents[v] = u;
                visit (g,f,v);
            }
        }
        f.end(u);
    }
}

class Forest {
    final public int[] parents, starts, ends;
    final public TreeMap<Integer, Integer> ends2nodes = new TreeMap<>();
    final public Color[] colors;
    private int time=0;
    public Forest (int n) {
        parents = new int[n+1];
        starts = new int[n+1];
        ends = new int[n+1];
        colors = new Color[n+1];
        for (int i=0; i<  colors.length; i++)
            colors[i] = Color.WHITE;
    }
    public void start(int u) {
        starts[u] = ++time;
        colors[u] = Color.GRAY;
    }
    public void end(int u) {
        final int t = ++time;
        ends[u] = t;
        ends2nodes.put(t, u);
        colors[u] = Color.BLACK;
    }
    public boolean isWhite(int u) {
        return Color.WHITE == colors[u];
    }
    public int[] getNodesOrderedByDescendingEndTimes() {
        int[] nodes = new int[ends.length];
        int index = 0;
        for (int t : ends2nodes.descendingKeySet()) {
            nodes[index++] = ends2nodes.get(t);
        }
        return nodes;
    }
}
