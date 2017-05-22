package epi.hackathon;


import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestCh19Maze {

    @Test public void should_find_path_in_4x4() {
        final Ch19Maze maze = new Ch19Maze(4);
        maze
                .add(0, 0, 1, 0)
                .add(1, 0, 1, 1)
                .add(1, 1, 2, 1)
                .add(2, 1, 2, 2)
                .add(2, 2, 2, 3);
        System.out.println(maze);

        final Ch19MazeSolver solver = new Ch19MazeSolver(maze, 0, 0, 2, 3);
        final String path = solver.search();
        assertEquals("[(0,0), (1,0), (1,1), (2,1), (2,2), (2,3)]", path);

        final Ch19MazeSolver solver1 = new Ch19MazeSolver(maze, 0, 0, 3, 3);
        assertEquals("", solver1.search());
    }

    @Test public void should_find_path_in_3x3() {
        final Ch19Maze maze = new Ch19Maze(3);
        maze
                .add(0, 0, 1, 0)
                .add(1, 0, 1, 1)
                .add(1, 1, 2, 1)
                .add(2, 1, 2, 2);
        System.out.println(maze);

        final Ch19MazeSolver solver = new Ch19MazeSolver(maze, 0, 0, 2, 2);

        final String path = solver.search();
        assertEquals("[(0,0), (1,0), (1,1), (2,1), (2,2)]", path);
    }

    @Test public void should_find_path_in_1x1() {
        final Ch19Maze maze = new Ch19Maze(1);
        System.out.println(maze);
        final Ch19MazeSolver solver = new Ch19MazeSolver(maze, 0, 0, 0, 0);

        final String path = solver.search();
        assertEquals("[(0,0)]", path);
    }

    @Test public void should_find_path_in_2x1() {
        final Ch19Maze maze = new Ch19Maze(2);
        maze.add(0, 0, 0, 1);
        System.out.println(maze);

        final Ch19MazeSolver solver = new Ch19MazeSolver(maze, 0, 0, 0, 1);

        final String path = solver.search();
        assertEquals("[(0,0), (0,1)]", path);
    }

    @Test public void should_find_path_in_1x2() {
        final Ch19Maze maze = new Ch19Maze(2);
        maze.add(0, 0, 1, 0);
        System.out.println(maze);

        final Ch19MazeSolver solver = new Ch19MazeSolver(maze, 0, 0, 1, 0);

        final String path = solver.search();
        assertEquals("[(0,0), (1,0)]", path);
    }
}
