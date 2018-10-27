package com.questions.sorting;

import java.util.Arrays;

/**
 * Quick sort
 */
public class QuickSort {
    public static void main(String[] args){
        Integer[] ints = new Integer[]{52, 5, 20, 18, 30, 31, 40, 25, 60, 4, 3, 7, 15, 11, 1};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        QuickSort sort = new QuickSort();
        sort.sort(ints, 0, ints.length-1);
        System.out.println("Sorted array: " + Arrays.toString(ints));

        ints = new Integer[]{3, 1, 4, 5, 7, 11, 15, 18, 20, 25, 30, 31, 40, 52, 60};
        System.out.println("\n\nUnsorted array: " + Arrays.toString(ints));
        sort.sort(ints, 0, ints.length-1);
        System.out.println("Sorted array: " + Arrays.toString(ints));
    }

    private void sort(Integer[] ints, int start, int end){
        if(start <= end){
            int index = partition(ints, start, end);
            sort(ints, start, index-1);
            sort(ints, index+1, end);
        }
    }

    /**
     * Select first element as pivot
     * iterate thr' second element to end
     * start counter points to higher than pivot
     * search and find a value lower than pivot and replace with start
     * @param ints
     * @param start
     * @param end
     * @return
     */
    private int partition(Integer[] ints, int start, int end){
        int pivot = ints[start];
        int pivotPosition = start;

        for(int i=start+1; i<=end; i++){
            //if value is less than pivot
            //replace it
            if(ints[i] <= pivot){
                start++;
                //replace with last known higher value
                replace(ints, i, start);
            }
        }
        replace(ints, start, pivotPosition);
        return start;
    }

    private void replace(Integer[] ints, int start, int end){
        int temp = ints[start];
        ints[start] = ints[end];
        ints[end] = temp;
    }

}
