package hacker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class FindNthStatistic {

    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = readInput();
        //4960
        int index = find(arr, 0, arr.length-1, arr.length/2);
        System.out.println(String.format("Answer: %s->%s", index, arr[index]));

        int i = arr.length/2 - 10;
        if (i<0) i=0;
        Arrays.sort(arr);
        for (;i<arr.length && i<(arr.length/2 + 10); i++)
            System.out.println(String.format("%s->%s", i, arr[i]));
    }

    private static int find(int[] arr, int start, int end, int statistic) {
        if (start>=end)
            return end;
        int q = partition (arr, start, end);
        int k = q-start;

        if (k == statistic)
            return q;
        else if (k < statistic)
            return find(arr, q+1, end, statistic-k-1);
        else
            return find(arr, start, q-1, statistic);
    }

    private static int partition(int[] arr, int start, int end) {
        int i = start - 1;
        int k = end;
        int key = arr[k];
        for (int j = start; j < end; j++) {
            if (arr[j] <= key) {
                i++;
                swap(arr, i, j);
            }
        }
        i++;
        swap(arr, i, k);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] readInput() throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream("src/test/resources/find_median.txt"));
        int len = in.nextInt();
        int[] input = new int[len];

        for (int i = 0; i < len; i++)
            input[i] = in.nextInt();

        return input;
    }

}