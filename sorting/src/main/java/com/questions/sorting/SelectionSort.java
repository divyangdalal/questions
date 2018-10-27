package com.questions.sorting;

import java.util.Arrays;

/**
 * Selection sort
 */
public class SelectionSort {
    public static void main(String[] args){
        Integer[] ints = new Integer[]{52, 5, 20, 18, 30, 31, 5, 40, 25, 60, 4, 3, 7, 15, 11, 1};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        SelectionSort sort = new SelectionSort();
        sort.sort1(ints);

        ints = new Integer[]{3, 1, 4, 5, 5, 7, 11, 15, 18, 20, 25, 30, 31, 40, 52, 60};
        System.out.println("\n\nUnsorted array: " + Arrays.toString(ints));
        sort.sort1(ints);
    }

    private void sort1(Integer[] ints){
        for(int i=0; i<ints.length; i++) {
            int smallestIndex = i;
            for(int x=i; x<ints.length; x++){
                if(ints[x] < ints[smallestIndex]){
                    smallestIndex = x;
                }
            }
            swap(ints, smallestIndex, i);
            System.out.println("Array - [" + i + "] : " + Arrays.toString(ints));
        }
    }

    private void sort(Integer[] ints){
        for(int i=0; i<ints.length; i++) {
            int smallestIndex = sort(ints, i, i);
            int smallestValue = ints[smallestIndex];
            ints[smallestIndex] = ints[i];
            ints[i] = smallestValue;
            System.out.println("Array - [" + i + "] : " + Arrays.toString(ints));
        }
    }


    private int sort(Integer[] ints, int index, int smallestIndex){
        if(index==ints.length){
            return smallestIndex;
        }
        if(ints[index]<ints[smallestIndex]){
            smallestIndex = index;
        }
        return sort(ints, ++index, smallestIndex);
    }

    private void swap(Integer[] ints, int start, int end){
        int temp = ints[start];
        ints[start] = ints[end];
        ints[end] = temp;
    }
}
