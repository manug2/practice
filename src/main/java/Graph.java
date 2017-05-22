import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    public final int V;

    Graph(int n) {
        V = n;
        E = new HashMap<>(n, 1.0f);
    }

    final public Map<Integer, List<Integer>> E;
    boolean undirected = false;
    public Graph undirected() {
        this.undirected = true;
        return this;
    }

    public void add(int s, int t) {
        if (!E.containsKey(s))
            E.put(s, new ArrayList<>());
        E.get(s).add(t);

        if (undirected) {
            if (!E.containsKey(t))
                E.put(t, new ArrayList<>());
            E.get(t).add(s);
        }
    }

    public Graph transpose() {
        Graph gt = new Graph(this.V);
        for (int s : E.keySet()) {
            for (int t : E.get(s))
                gt.add(t, s);
        }
        return gt;
    }

    public List<Integer> adjacencies(int s) {
        if (E.containsKey(s))
            return E.get(s);
        return new ArrayList<>();

    }

}
enum Color { WHITE, GRAY, BLACK }