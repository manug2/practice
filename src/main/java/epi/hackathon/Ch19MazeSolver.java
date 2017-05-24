package epi.hackathon;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static epi.hackathon.Ch19MazeSolver.Color.WHITE;

public class Ch19MazeSolver {

    final Pixel start, end;
    final Ch19Maze maze;
    final Map<Pixel, Color> colors;
    private final Map<Pixel, Pixel> parents;

    public Ch19MazeSolver(Ch19Maze maze, int start_x, int start_y, int end_x, int end_y) {
        start = new Pixel(start_x, start_y);
        end   = new Pixel(end_x, end_y);
        this.maze = maze;

        colors = new HashMap<>(2 * maze.V.length);
        parents = new HashMap<>(2 * maze.V.length);
    }

    public String search() {
        Stack<Pixel> path = new Stack<>();
        if (search(start, path)) {
            return path.toString();
        }
        return "";
    }

    private boolean search(Pixel u, Stack<Pixel> path) {
        path.push(u);
        if (end.equals(u))
            return true;

        colors.put(u, Color.GRAY);
        for (Pixel v : maze.adjacencies(u)) {
            if (isWhite(v)) {
                parents.put(v, u);
                if (search(v, path))
                    return true;
            }
        }
        colors.put(u, Color.BLACK);
        path.pop();
        return false;
    }

    private boolean isWhite(Pixel u) {
        return !colors.containsKey(u) || WHITE == colors.get(u);
    }

    enum Color{WHITE, GRAY, BLACK }
}
