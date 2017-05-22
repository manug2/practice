package epi.hackathon;


import java.util.Arrays;

public class Ch6DutchFlag {
    public int[] arrange(int[] input, int index) {
        int smaller=0, equal=0, larger=input.length-1;

        final int pivot = input[index];

        while (equal <= larger) {
            if (input[equal] < pivot) {
                swap(input, smaller++, equal++);
            } else if (input[equal] == pivot) {
                ++equal;
            } else {
                swap(input, larger--, equal);
            }
        }

        return input;
    }

    private void swap(int[] input, int i, int i1) {
        int temp = input[i];
        input[i] = input[i1];
        input[i1] = temp;
    }

    public int[] arrangeBruteForce(int[] input, int index) {
        int smaller=0, larger=input.length-1;

        final int pivot = input[index];

        final int[] output = new int[input.length];

        for (int i=0; i< input.length; i++) {
            if (input[i] < pivot) {
                output[smaller++] = input[i];
            } else if (input[i] > pivot) {
                output[larger--] = input[i];
            }
        }
        System.out.println(Arrays.toString(input));
        System.out.println(Arrays.toString(output));
        for (int i=0; i< input.length; i++) {
            if (input[i] == pivot) {
                output[smaller++] = input[i];
            }
        }

        return output;
    }
}
