package epi.hackathon;


import org.junit.Test;

import static epi.hackathon.Ch8MergeSortedList.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestCh8MergedSortedLists {

    private Ch8MergeSortedList merger = new Ch8MergeSortedList();

    @Test public void should_merge() {
        MyList ml = merger.merge(fromReverseOf(new int[] {1, 2, 3}), fromReverseOf(new int[] {3, 4, 6}));
        assertEquals(ml, from(new int [] {1, 2, 3, 3, 4, 6}));
    }

    @Test public void should_merge_when_second_list_is_empty() {
        MyList ml = merger.merge(fromReverseOf(new int[] {1, 2, 3}), fromReverseOf(new int[0]));
        assertEquals(ml, from(new int [] {1, 2, 3}));
    }

    @Test public void should_merge_when_first_list_is_empty() {
        MyList ml = merger.merge(fromReverseOf(new int[0]), fromReverseOf(new int[] {1, 2, 3}));
        assertEquals(ml, from(new int [] {1, 2, 3}));
    }

    @Test public void list_equals_works() {
        assertEquals(from(new int [] {1, 2, 3}), from(new int [] {1, 2, 3}));
    }
    @Test public void list_equals_works_0() {
        assertEquals(from(new int [0]), from(new int [0]));
    }
    @Test public void list_equals_fails_appropriately() {
        assertNotEquals(from(new int [] {1, 2}), from(new int [] {1, 2, 3}));
    }
    @Test public void list_equals_fails_appropriately2() {
        assertNotEquals(from(new int [] {1, 2, 3}), from(new int [] {2, 3}));
    }

}
