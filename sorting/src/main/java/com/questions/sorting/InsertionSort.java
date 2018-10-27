package com.questions.sorting;

import java.util.Arrays;

/**
 * Insertion sort
 */
public class InsertionSort {
    public static void main(String[] args){
        Integer[] ints = new Integer[]{52, 5, 20, 18, 30, 31, 5, 40, 25, 60, 4, 3, 7, 15, 11, 1};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        InsertionSort sort = new InsertionSort();
        sort.sort(ints);

        ints = new Integer[]{3, 1, 4, 5, 5, 7, 11, 15, 18, 20, 25, 30, 31, 40, 52, 60};
        System.out.println("\n\nUnsorted array: " + Arrays.toString(ints));
        sort.sort(ints);
    }

    private void sort(Integer[] ints){
        for(int i=1; i<ints.length; i++) {
            sort(ints, i);
            System.out.println("Array: " + Arrays.toString(ints));
        }
    }

    private void sort(Integer[] ints, int index){
        int insertIndex = findInsertIndex(ints, ints[index], index);
        if(insertIndex != index){
            int temp = ints[index];
            move(ints, insertIndex, index);
            ints[insertIndex] = temp;
        }
    }

    private int findInsertIndex(Integer[] ints, int value, int index){
        for(int i = 0; i< index; i++){
            if(ints[i]>value){
                return i;
            }
        }
        return index;
    }

    private void move(Integer[] ints, int start, int end){
        if(start < end){
            ints[end] = ints[end-1];
            move(ints, start, --end);
        }
    }
}
