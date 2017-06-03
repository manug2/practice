package extras.graphical;


import graphs.StringDFS;
import graphs.StringGraph;

public class LeisureTraveller {
    final StringGraph g;
    public LeisureTraveller() {
        g = new StringGraph();
    }

    public void add(String start, String destination) {
        g.add(start, destination);
    }

    public int max() {
        StringDFS dfs = new StringDFS(g);
        return dfs.findLongestSCC();
    }
}
