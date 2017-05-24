package epi.hackathon;


import org.junit.Test;

import static org.junit.Assert.*;

public class TestCh6DutchFlag {

    @Test public void should_arrange_3_elements_when_pivot_at_0() {
        int [] input = new int [] {2, 1, 3};
        int [] output = new Ch6DutchFlag().arrange(input, 0);
        assertArrayEquals(new int [] {1, 2, 3}, output);
    }

    @Test public void should_arrange_3_elements_when_pivot_at_2() {
        int [] input = new int [] {3, 1, 2};
        int [] output = new Ch6DutchFlag().arrange(input, 2);
        assertArrayEquals(new int [] {1, 2, 3}, output);
    }

    @Test public void should_arrange() {
        int [] input = new int [] {4, 5, 6, 6, 3, 1, 2, 9, 7, 8};
        //new int[] {1, 2, 3, 4, 5, 6, 6, 7, 8, 9}
        int [] output = new Ch6DutchFlag().arrange(input, 2);
        assertEquals(6, output[5]);
        assertEquals(6, output[6]);
        assertTrue(output[4] < 6);
        assertTrue(output[7] > 6);
    }
}
