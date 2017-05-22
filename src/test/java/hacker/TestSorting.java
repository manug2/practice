package hacker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class TestSorting {

    @Parameterized.Parameters(name = "{0}-{1}")
    public static List<Object[]> parameters() {
        ArrayList<Object[]> params = new ArrayList<Object[]>();
        /*
*/
        params.add(new Object[] {new Sorting.QuickSort(), 8,
                new int[]{1, 3, 9, 8, 2, 7, 5}, new int[]{1, 2, 3, 5, 7, 8, 9}});
        params.add(new Object[] {new Sorting.InsertionSort(), 9,
                new int[]{1, 3, 9, 8, 2, 7, 5}, new int[]{1, 2, 3, 5, 7, 8, 9}});

        params.add(new Object[] {new Sorting.QuickSort(), 10,
                new int[]{5, 8, 1, 3, 7, 9, 2}, new int[]{1, 2, 3, 5, 7, 8, 9}});
        params.add(new Object[] {new Sorting.InsertionSort(), 10,
                new int[]{5, 8, 1, 3, 7, 9, 2}, new int[]{1, 2, 3, 5, 7, 8, 9}});

        params.add(new Object[] {new Sorting.InsertionSort(), 0,
                new int[]{2, 3}, new int[]{2, 3}});

        params.add(new Object[] {new Sorting.QuickSort(), 2,
                new int[]{2, 3}, new int[]{2, 3}});


        params.add(new Object[] {new Sorting.QuickSort(), 1,
                new int[]{3, 2}, new int[]{2, 3}});

        params.add(new Object[] {new Sorting.InsertionSort(), 1,
                new int[]{3, 2}, new int[]{2, 3}});

        params.add(new Object[] {new Sorting.QuickSort(), 0,
                new int[]{}, new int[]{}});
        params.add(new Object[] {new Sorting.InsertionSort(), 0,
                new int[]{}, new int[]{}});

        params.add(new Object[] {new Sorting.QuickSort(), 0,
                new int[]{1}, new int[]{1}});
        params.add(new Object[] {new Sorting.InsertionSort(), 0,
                new int[]{1}, new int[]{1}});
        /*
*/

        return params;
    }

    private final int expectedShifts;
    private final Sorting.Sort sorter;

    private final int[] input;
    private final int[] output;

    public TestSorting(Sorting.Sort sorter, int expectedShifts, int[] input, int[] output) {
        this.sorter = sorter;
        this.expectedShifts = expectedShifts;
        this.input = input;
        this.output = output;
    }

    @Test
    public void should_sort_array() {
        sorter.sort(input);
        assertArrayEquals(output, input);
        assertEquals(expectedShifts, sorter.getShifts());
    }

}
