package epi.hackathon;


import java.util.Arrays;

import static java.lang.Math.random;

public class Ch6ChooseSubArray {
    private final int[] input;
    private final int k;

    public Ch6ChooseSubArray(int[] input, int k) {
        this.input = input;
        this.k = k;
    }

    public int[] sub() {
        for (int i=0; i<k; i++) {
            final Double r = random() * (k-i);
            int index = r.intValue();
            swap(index, input.length - 1 - i);
        }
        return Arrays.copyOfRange(input, input.length-k, input.length);
    }

    private void swap(int index, int last) {
        if(index==last)
            return;

        int tmp = input[last];
        input[last] = input[index];
        input[index] = tmp;
    }
}
