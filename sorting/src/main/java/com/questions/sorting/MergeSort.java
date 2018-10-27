package com.questions.sorting;

import java.util.Arrays;

/**
 * Merge sort
 */
public class MergeSort {
    public static void main(String[] args){
        Integer[] ints = new Integer[]{52, 5, 20, 18, 30, 31, 5, 40, 25, 60, 4, 3, 7, 15, 11, 1};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        MergeSort sort = new MergeSort();
        Integer[] copy = new Integer[ints.length];
        sort.sort(ints,0, ints.length-1);
        System.out.println("Sorted array: " + Arrays.toString(ints));

        ints = new Integer[]{3, 1, 4, 5, 5, 7, 11, 15, 18, 20, 25, 30, 31, 40, 52, 60};
        System.out.println("\n\nUnsorted array: " + Arrays.toString(ints));
        sort.sort(ints,0, ints.length-1);
        System.out.println("Sorted array: " + Arrays.toString(ints));
    }

    private void sort(Integer[] ints, int start, int end){
        if(start == end) {
            return;
        }
        int middle = (start + end)/2;
        sort(ints, start, middle);
        sort(ints,middle+1, end);
        Integer[] copy = merge(ints, start, end);

        //copy it back to origin array
        for(int i = start; i<=end; i++){
            ints[i] = copy[i];
        }
    }

    /**
     * merges two arrays
     * @param ints
     * @param start
     * @param end
     * @return
     */
    private Integer[] merge(Integer[] ints, int start, int end){
        Integer[] copy = new Integer[ints.length];
        int middle = (start + end)/2;
        int mid = middle +1;
        int copyStart = start;
        while(start<=middle && mid<=end) {
            if (ints[start] < ints[mid]) {
                copy[copyStart] = ints[start];
                start++;
            } else {
                copy[copyStart] = ints[mid];
                mid++;
            }
            copyStart++;
        }

        while(start<=middle){
            copy[copyStart] = ints[start];
            start++;
            copyStart++;
        }
        while(mid<=end) {
            copy[copyStart] = ints[mid];
            mid++;
            copyStart++;
        }
        return copy;
    }

}
