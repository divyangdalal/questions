package com.questions.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Binary tree search
 */
public class BinarySearch {

    private static final int NOT_FOUND = -1;

    public static void main(String[] args){
        Integer[] ints = new Integer[]{1, 5, 10, 18, 30, 31, 52, 40, 25, 60, 4, 3, 7, 20, 15, 11};
        List<Integer> list = Arrays.asList(ints);
        Collections.sort(list);
        System.out.println("Sorted array: " + Arrays.toString(list.toArray()));
        BinarySearch search = new BinarySearch();
        int value = 21;
        int result = search.binarySearch(list, 0, list.size()-1, value);
        System.out.println("Value location is : " + result);
    }

    public int binarySearch(List<Integer> list, int start, int end, int value){
        if(end < start){
            return NOT_FOUND;
        }
        int half = (end-start)/2;
        half = start + half;
        int middle = list.get(half);
        if(middle == value){
            return half;
        }else if(middle > value){
            return binarySearch(list,start, half-1, value);
        }else if(middle < value){
            return binarySearch(list, half+1, end, value);
        }
        return NOT_FOUND;
    }
}
