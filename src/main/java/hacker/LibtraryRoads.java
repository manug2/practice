package hacker;

import graphs.Color;
import graphs.Graph;

import java.util.*;

public class LibtraryRoads {
    private final int cLib, cRoad;

    public LibtraryRoads(int cLib, int cRoad) {
        this.cLib = cLib;
        this.cRoad = cRoad;
    }

    public int cost(Graph g, int s) {

        if (cLib<=cRoad)
            return g.V * cLib;

        //return min_cost(g);

        Collection<Integer> costs = visit(g, new Adj(s));
        SortedSet<Integer> sorted = new TreeSet<>(costs);
        return sorted.first();
    }

    public int min_cost(Graph g) {
        int min = Integer.MAX_VALUE;
        for (int i=0; i<g.V;i++) {
            int cost = cLib * (g.V-i) + cRoad * (i);
            if (cost < min)
                min = cost;
        }
        return min;
    }

    public Collection<Integer> visit(Graph g, Adj start) {
        final Map<Adj, Integer> levels = new HashMap<>();
        final Map<Adj, Color> colors = new HashMap<>();
        final Queue<Adj> q = new ArrayDeque<>(g.V);
        final Map<Adj, Integer> costs = new HashMap<>();

        levels.put(start, 1);
        costs.put(start, start.roadFrom>0?cRoad:cLib);
        colors.put(start, Color.GRAY);
        q.add(start);

        while (! q.isEmpty()) {
            Adj u = q.poll();

            for (Adj v : adjacencies(g, u)) {
                if (!colors.containsKey(v)) {
                    System.out.println(String.format(
                            "node: %s, next: %s", u, v));
                    colors.put(v, Color.GRAY);
                    levels.put(v, levels.get(u) + 1);

                    final int cost = (v.roadFrom>0?cRoad:cLib);
                    costs.put(v, costs.get(u) + cost);

                    q.add(v);
                }
                else
                    System.out.println(String.format(
                            "ignore node: %s, next: %s", u, v));
/*
*/
            }
            colors.put(u, Color.BLACK);
        }

        System.out.println(levels);
        System.out.println(costs);
        return costs.values();
    }

    List<Adj> adjacencies(Graph g, Adj u) {
        List<Adj> paths = new ArrayList<>();
        for (int v : g.adjacencies(u.node)) {
            paths.add(new Adj(v, u.path));
            paths.add(new Adj(v, u));
        }

        //System.out.println(String.format(
        //        "node: %s, paths: %s", u, paths));
        return paths;
    }

    class Adj {
        private final int node;
        private final int roadFrom;
        private final String path;

        Adj(int node) {
            this.node = node;
            this.roadFrom = 0;
            this.path="N"+node;
        }
        Adj(int node, String fromPath) {
            this.node = node;
            this.roadFrom = 0;
            this.path=fromPath + "N" + node;
        }
        Adj(int node, Adj from) {
            this.node = node;
            this.roadFrom = from.node;
            this.path = from.path + "R"+roadFrom+node;
        }
        public String toString() {
            return path;
        }
        public int hashCode() {
            return  node + (roadFrom*10_000_00);
        }
        public boolean equals(Object o) {
            if (this==o)
                return true;
            if (!(o instanceof Adj))
                return false;

            Adj other = (Adj)o;
            return node==other.node && roadFrom == other.roadFrom
                    //&& path.equals(other.path)
                    ;
        }
    }
}