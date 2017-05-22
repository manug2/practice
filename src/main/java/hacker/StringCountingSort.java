package hacker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringCountingSort {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new FileInputStream("src/test/resources/counting.txt"));
        int len = in.nextInt();
        int[] indices = new int[len];
        String[] strings = new String[len];

        for (int i = 0; i < len-1; i++) {
            if (i>162170 && i%100000==0)
                System.out.println(i);
            indices[i] = in.nextInt();
            strings[i] = in.next();
        }

        int[] count = count(indices);
        count_less_than(count);

        String[] sorted_strings = sort(indices, strings, count);
        printArray(sorted_strings);
    }

    private static String[] sort(int[] arr, String[] strings, int[] x) {
        String[] sorted_strings = new String[strings.length];
        int half = arr.length/2;
        for (int i=arr.length; i>0; i--) {
            int j = arr[i-1];
            sorted_strings[x[j]-1] = (i <= half ? "-" : strings[i-1]);
            x[j] -= 1;
        }

        return sorted_strings;
    }

    private static int[] count(int[] arr) {
        int x[] = new int[100];
        for (int i=0;i<arr.length;i++)
            x[arr[i]]+=1;
        return x;
    }

    private static void count_less_than(int[] x) {
        for (int i=1;i<x.length; i++)
            x[i] += x[i-1];
    }

    private static void printArray(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (i % 100 == 0)
                System.out.println("");
            else
                System.out.print(" ");
            System.out.print(String.format(" %s", strings[i]));
        }
    }

    private static void printArray(int[] indices) {
        if (indices.length>0)
            System.out.print(indices[0]);
        for (int i = 1; i < indices.length; i++)
            System.out.print(String.format(" %s", indices[i]));
    }

}