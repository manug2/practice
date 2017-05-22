import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BurnOut {

    private final int[][] emp;

    public BurnOut(int[][] emp) {
        this.emp = emp;
    }

    public int min() {
        if (this.emp.length<1)
            return 0;

        Graph g = make();
        //g.print();
        BFS bfs = new BFS(this.emp.length+1);
        bfs.visit(g);

        int count=0;
        for (int i=0; i<bfs.burned.length; i++)
            if (bfs.burned[i]>0)
                ++count;
        return count;
    }

    public Graph make() {
        Graph g = new Graph(emp.length+1);
        g.add(0, 0);//, 0); // insert CEO
        for (int i=1; i<=emp.length; i++) {
            final int parent = emp[i-1][0];
            final int bo = emp[i-1][1];
            g.add(parent, i);//, 1-bo);
        }
        return g;
    }
}

class BFS{
    final public int[] parents;
    final public int[] burned;
    final public Color[] colors;
    final private int[] d;
    public int[] levels() {
        return Arrays.copyOf(d, d.length);
    }

    final Queue<Integer> q;
    public BFS(int n) {
        parents = new int[n];
        d = new int[n];
        burned = new int[n];
        colors = new Color[n];
        for (int i=0;i<n;i++)
            colors[i] = Color.WHITE;
        q = new ArrayDeque<>(parents.length);
    }

    public void visit(Graph g) {
        q.add(0);
        while (!q.isEmpty()) {
            _visit(g);
        }
    }

    private void _visit(Graph g) {
        int u = q.poll();
        colors[u] = Color.GRAY;
        d[u] = d[parents[u]] + 1;

/*
        if (g.burned[u]==1) {
            if (burned[parents[u]] == 1) {
                burned[parents[u]] = 0;
                burned[u] = 2;
            } else if (burned[parents[u]]>1) {
                burned[parents[u]] += 1;
            } else {
                burned[u] = 1;
            }
        }
*/

        for (int v : g.adjacencies(u)) {
            if (Color.WHITE.equals(colors[v])) {
                parents[v] = u;
                q.add(v);
            }
        }
        colors[u] = Color.BLACK;
    }

    public void collect_impact(int[] impact) {
        for (int i=0; i<impact.length; i++)
            impact[i] = burned[i];

        for (int i=2; i<impact.length; i++) {
            if (impact[i-1]==1) {
                if (impact[i]>0) {
                    impact[i - 1] = 0;
                    impact[i] += 1;
                }
            } else if (impact[i-1]>1) {
                impact[i - 1] += impact[i];
                impact[i] = 0;
            }
        }
    }
}
