package hacker;

import graphs.Graph;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLibtraryRoads {

    @Test public final void should_calc_cost_3_lib_2_roads() {
        Graph g = new Graph(3).undirected();
        g.add(1, 2);
        g.add(3, 1);
        g.add(2, 3);

        Assert.assertEquals(4, new LibtraryRoads(2, 1).cost(g, 1));
    }
}
