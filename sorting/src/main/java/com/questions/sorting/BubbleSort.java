package com.questions.sorting;

import java.util.Arrays;

/**
 * Bubble sort
 */
public class BubbleSort {
    public static void main(String[] args){
        Integer[] ints = new Integer[]{52, 5, 20, 18, 30, 31, 5, 40, 25, 60, 4, 3, 7, 15, 11, 1};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        BubbleSort sort = new BubbleSort();
        sort.sort1(ints);

        ints = new Integer[]{3, 1, 4, 5, 5, 7, 11, 15, 18, 20, 25, 30, 31, 40, 52, 60};
        System.out.println("Unsorted array: " + Arrays.toString(ints));
        sort.sort1(ints);
    }

    private void sort1(Integer[] ints){
        for(int i=ints.length-1; i>=0; i--) {
            boolean swapped = false;
            for(int j=0; j<i; j++) {
                if (ints[j] > ints[j + 1]) {
                    swapped = true;
                    replace(ints, j, j + 1);
                }
            }
            if(!swapped){
                break;
            }
            System.out.println("Array - [" + i + "] : " + Arrays.toString(ints));
        }
    }


    private void sort(Integer[] ints){
        for(int i=0; i<ints.length; i++) {
            boolean swapped = sort(ints, 0, ints.length-i);
            System.out.println("Array - [" + i + "] : " + Arrays.toString(ints));
            if(!swapped){
                break;
            }
        }
    }


    private boolean sort(Integer[] ints, int index, int end){
        boolean swapped = false;
        if(index==end-1){
            return swapped;
        }
        if(ints[index] > ints[index+1]){
            replace(ints, index, index+1);
            swapped = true;
        }
        boolean returnValue = sort(ints, ++index, end);
        return swapped || returnValue;
    }

    private void replace(Integer[] ints, int start, int end){
        int temp = ints[start];
        ints[start] = ints[end];
        ints[end] = temp;
    }

}
