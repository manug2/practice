package hacker;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by maverick on 4/2/2017.
 */
public class TestInsertionSort {

    private void sort(int[] input) {
        new Sorting.InsertionSort().sort(input);
    }

    @Test
    public void should_sort_array() {
        int[] input = new int[]{5, 8, 1, 3, 7, 9, 2};
        sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 5, 7, 8, 9}, input);
    }

    @Test
    public void should_sort_array_of_0_elements() {
        int[] input = new int[0];
        sort(input);
        assertArrayEquals(new int[0], input);
    }

    @Test
    public void should_sort_array_of_1_element() {
        int[] input = new int[]{2};
        sort(input);
        assertArrayEquals(new int[]{2}, input);
    }

    @Test
    public void should_sort_array_of_2_element() {
        int[] input = new int[]{3, 2};
        sort(input);
        assertArrayEquals(new int[]{2, 3}, input);
    }

    @Test
    public void should_sort_array_of_2_sorted_element() {
        int[] input = new int[]{2, 3};
        sort(input);
        assertArrayEquals(new int[]{2, 3}, input);
    }

}
