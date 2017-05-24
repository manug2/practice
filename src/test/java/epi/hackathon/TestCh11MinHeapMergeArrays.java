package epi.hackathon;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class TestCh11MinHeapMergeArrays {

    @Test public void merge_2_1_element_arrays() {
        TestFile f1 = new TestFile(new int [] {1, 5, 9});
        TestFile f2 = new TestFile(new int [] {3, 4, 10});

        int[] output = new int[f1.data.length + f2.data.length];
        MinHeap h = new MinHeap(output.length);
        while (f1.hasNext())
            h.insert(f1.next());
        while (f2.hasNext())
            h.insert(f2.next());

        int index = 0;
        while (index < output.length) {
            output[index++] = h.extract_min();
        }

        assertArrayEquals(new int[] {1, 3, 4, 5, 9, 10}, output);
    }

    class TestFile {
        final int [] data;
        int index = 0;
        TestFile(int[] data) {
            this.data = data;
        }
        boolean hasNext() {
            return index < data.length;
        }
        final int next() {
            if (index == data.length)
                throw new RuntimeException();
            return data[index++];
        }
    }

}
