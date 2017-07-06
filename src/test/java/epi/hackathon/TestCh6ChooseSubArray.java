package epi.hackathon;


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCh6ChooseSubArray {

    @Test public void should_get_sub_array_of_2() {
        final int[] input = new int[]{10, 11, 12, 13, 14};
        final int len_sub = 2;
        int[] sub = new Ch6ChooseSubArray(input, len_sub).sub();
        assertEquals(len_sub, sub.length);
    }

    @Test public void should_get_sub_array_of_1() {
        final int[] input = new int[]{10, 11, 12, 13, 14};
        final int len_sub = 1;
        int[] sub = new Ch6ChooseSubArray(input, len_sub).sub();
        assertEquals(len_sub, sub.length);
    }

    @Test public void should_get_sub_array_of_2_of_2() {
        final int[] input = new int[]{100, 101};
        final int len_sub = 2;
        int[] sub = new Ch6ChooseSubArray(input, len_sub).sub();
        assertEquals(len_sub, sub.length);
        assertTrue(
                Arrays.equals(new int[] {100, 101}, sub)
                || Arrays.equals(new int[] {101, 100}, sub)
        );
    }

    @Test public void should_get_sub_array_of_1_of_1() {
        final int[] input = new int[]{6};
        final int len_sub = 1;
        int[] sub = new Ch6ChooseSubArray(input, len_sub).sub();
        assertEquals(len_sub, sub.length);
        assertArrayEquals(new int[] {6}, sub);
    }

}
