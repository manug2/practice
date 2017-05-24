package epi.hackathon;

import org.junit.Test;

import static epi.hackathon.MinHeap.from;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestMinHeap {
    @Test public void should_sort() {
        int[] input = new int[]{2, 9, 1, 3, 10, 4, 5, 7};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 7, 9, 10}, new HeapSort().ascending(input));
    }

    @Test public void can_insert() {
        MinHeap h = from(new int[]{2, 1, 3, 4});
        h.extract_min();
        assertEquals(2, h.min());
        h.insert(1);
    }
    @Test public void works() {
        MinHeap h = from(new int[]{2, 9, 1, 3, 10, 4, 5, 7});
        assertEquals(1, h.min());
        assertEquals(1, h.extract_min());

        assertEquals(2, h.min());
        assertEquals(2, h.extract_min());
        assertEquals(3, h.extract_min());
        assertEquals(4, h.extract_min());
        assertEquals(5, h.extract_min());
        assertEquals(7, h.extract_min());
        assertEquals(9, h.extract_min());
        assertEquals(10, h.extract_min());

        assertArrayEquals(new int[0], h.getData());
    }
    @Test public void has_correct_min_after_2_extract() {
        MinHeap h = from(new int[]{2, 1, 3});
        h.extract_min();
        h.extract_min();
        assertEquals(3, h.min());
    }
    @Test public void extracts_2_min_correctly() {
        MinHeap h = from(new int[]{2, 1, 3});
        h.extract_min();
        assertEquals(2, h.extract_min());
    }
    @Test public void has_correct_min_after_extract_correct_min() {
        MinHeap h = from(new int[]{2, 1, 3});
        h.extract_min();
        assertEquals(2, h.min());
    }
    @Test public void extract_correct_min() {
        assertEquals(1, from(new int[]{2, 1, 3}).extract_min());
    }
    @Test public void has_correct_min() {
        assertEquals(1, from(new int[]{2, 1, 3}).min());
    }
    @Test public void should_build_heap_with_3_elements() {
        assertArrayEquals(new int[] {1, 2, 3}, from(new int[]{2, 1, 3}).getData());
    }

    @Test public void should_build_heap_with_1_element() {
        assertArrayEquals(new int[] {1}, from(new int[]{1}).getData());
    }
    @Test public void should_build_heap_with_2_elements() {
        assertArrayEquals(new int[] {1, 2}, from(new int[]{1, 2}).getData());
    }
    @Test public void should_build_heap_with_2_elements_in_opp_order() {
        assertArrayEquals(new int[] {1, 2}, from(new int[]{2, 1}).getData());
    }

}
