package epi.hackathon;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestBinarySearch {

    @Test
    public void search_108() {
        int[] arr = new int[] {-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        assertEquals(3, search(arr, 0, arr.length-1, 108));
    }

    @Test
    public void search_285() {
        int[] arr = new int[] {-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        assertEquals(6, search(arr, 0, arr.length-1, 285));
    }

    private int search(int[] arr, int start, int end, int key) {
        if (end <= start) {
            if (arr[start] == key)
                return start;
            else
                return -1;
        }

        int mid = start + (end-start)/2;
        if (key <= arr[mid])
            return search(arr, start, mid, key);
        else
            return search(arr, mid+1, end, key);
    }
}
