package hacker;


import java.util.Arrays;
import java.util.Scanner;


public class Sorting {

    public static void main(String[] args) {
        int[] input = readInput();
        int qs = new QuickSort().sort(Arrays.copyOf(input, input.length)).getShifts();
        int is = new InsertionSort().sort(Arrays.copyOf(input, input.length)).getShifts();
        System.out.println(is-qs);
    }

    interface Sort {
        Sort sort(int[] arr);
        int getShifts();
    }

    static class CountingSort implements Sort {
        int countSortShifts = 0;

        public int getShifts() {
            return countSortShifts;
        }

        public Sort sort(int[] arr) {
            countSortShifts=0;
            sort_i(arr, 0, arr.length-1);
            return this;
        }
        private void sort_i(int[] arr, int start, int end) {
            int x[] = count(arr);
            for (int i=1;i<x.length; i++)
                x[i] += x[i-1];

            int[] sorted = new int[arr.length];
            for (int i=arr.length; i>start; i--) {
                int j = arr[i-1];
                sorted[x[j]-1] = j;
                x[j] -= 1;
            }

            copy(sorted, arr);
        }

        private int[] count(int[] arr) {
            int x[] = new int[100];
            for (int i=0;i<arr.length;i++)
                x[arr[i]]+=1;
            return x;
        }

        private void copy(int[] src, int[] dest) {
            for (int i=0; i<src.length;i++)
                dest[i] = src[i];
        }
    }

    static class InsertionSort implements Sort {
        int insertionSortShifts = 0;

        public int getShifts() {
            return insertionSortShifts;
        }

        public Sort sort(int[] arr) {
            insertionSortShifts=0;
            sort_i(arr, 0, arr.length-1);
            return this;
        }
        private void sort_i(int[] arr, int start, int end) {
            for (int j=start+1; j<=end; j++){
                int key = arr[j];
                int i=j-1;
                while (i>=0 && arr[i]>key) {
                    insertionSortShifts++;
                    arr[i + 1] = arr[i];
                    i--;
                }
                arr[i+1] = key;
            }
        }
    }

    static class QuickSort implements Sort {
        int quickSortShifts = 0;

        public int getShifts() {
            return quickSortShifts;
        }
        public Sort sort(int[] arr) {
            quickSortShifts=0;
            sort_i(arr, 0, arr.length-1);
            return this;
        }
        private void sort_i(int[] arr, int start, int end) {
            if (start < end) {
                int p = partition(arr, start, end);
                sort_i(arr, start, p - 1);
                sort_i(arr, p + 1, end);
            }
        }

        private int partition(int[] arr, int start, int end) {
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

        private void swap(int[] arr, int i, int j) {
            quickSortShifts++;
            if (i == j)
                return;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static int[] readInput() {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] input = new int[len];

        for (int i = 0; i < len; i++)
            input[i] = in.nextInt();

        return input;
    }

    private static void printArray(int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            if (i > start)
                System.out.print(" ");
            System.out.print(arr[i]);
        }
        System.out.println("");
    }

}
