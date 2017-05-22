package hacker;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by maverick on 4/2/2017.
 */
public class TestQuickSort {

    private int sort(int[] input) {
        Sorting.Sort sorter = new Sorting.QuickSort();
        sorter.sort(input);
        return sorter.getShifts();
    }

    @Test
    public void should_sort_array_with_expected_shifts() {
        int[] input = new int[]{1, 3, 9, 8, 2, 7, 5};
        int shifts = sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 5, 7, 8, 9}, input);
        assertEquals(8, shifts);
    }

    @Test
    public void should_sort_array() {
        int[] input = new int[]{5, 8, 1, 3, 7, 9, 2};
        int shifts = sort(input);
        assertArrayEquals(new int[]{1, 2, 3, 5, 7, 8, 9}, input);
        assertEquals(10, shifts);
    }

    @Test
    public void should_sort_array_of_0_elements() {
        int[] input = new int[0];
        int shifts = sort(input);
        assertArrayEquals(new int[0], input);
        assertEquals(0, shifts);
    }

    @Test
    public void should_sort_array_of_1_element() {
        int[] input = new int[]{2};
        int shifts = sort(input);
        assertArrayEquals(new int[]{2}, input);
        assertEquals(0, shifts);
    }

    @Test
    public void should_sort_array_of_2_element() {
        int[] input = new int[]{3, 2};
        int shifts = sort(input);
        assertArrayEquals(new int[]{2, 3}, input);
        assertEquals(1, shifts);
    }

    @Test
    public void should_sort_array_of_2_sorted_element() {
        int[] input = new int[]{2, 3};
        int shifts = sort(input);
        assertArrayEquals(new int[]{2, 3}, input);
        assertEquals(2, shifts);
    }

}
