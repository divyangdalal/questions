package com.questions.crackingcode;

import com.questions.crackingcode.utils.LinkNode;
import com.questions.crackingcode.utils.LinkNodeWithRandom;

import java.util.*;

public class MiscTest {
    public static void main(String[] args){
        MiscTest test = new MiscTest();
        test.testPalindrome();
        test.testSearchSequence();
        test.findMaxProfit();
        test.findPalindromes();
        test.findValueInCircularArray();
        test.reverseList();
        test.findSumInSorted();
        test.copyLinkList();
        test.powerSet();
    }

    /**
     * Tests if the string is a palindrome
     */
    private void testPalindrome(){
        System.out.println("Is palindrome (no): " + isPalindrome("no"));
        System.out.println("Is palindrome: (madam) " + isPalindrome("madam"));
        System.out.println("Is palindrome: (nurses run) " + isPalindrome("nurses run"));
        System.out.println("Is palindrome: (racecar) " + isPalindrome("racecar"));
    }

    private boolean isPalindrome(String input){
        input = input.replaceAll("\\s+", "");
        //if its even then its not palindrome
        if(input.length()%2==0){
            return false;
        }
        for(int i=0; i<input.length()/2; i ++){
            System.out.print(input.charAt(i) + ":" + input.charAt(input.length()-1-i));
            if(input.charAt(i) != input.charAt(input.length()-1-i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Search sequence in a string
     */
    private void testSearchSequence(){
        System.out.println("Sequence (something: anytingsomethingnothing): " + Arrays.toString(searchSequence("something", "anytingsomethingnothing").toArray()));
        System.out.println("Sequence (something: sometingsomethinngothing): " + Arrays.toString(searchSequence("something", "anytingsomethinngothing").toArray()));
        System.out.println("Sequence (aaba: aabaaaaaabaaba): " + Arrays.toString(searchSequence("aaba", "aabaaaaaabaaba").toArray()));
        System.out.println("Sequence (AAAAA: AAAAAAAAAAAAAAAAAA): " + Arrays.toString(searchSequence("AAAAA", "AAAAAAAAAAAAAAAAAA").toArray()));
        System.out.println("Sequence (AAAAB: AAAAAAAAAAAAAAAAAB): " + Arrays.toString(searchSequence("AAAAB", "AAAAAAAAAAAAAAAAAB").toArray()));

    }

  /*  private int searchSequence(String seq, String input) {
        if (seq.length() > input.length()) {
            return -1;
        }
        int index = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == seq.charAt(index)) {
                if (index == seq.length() - 1) {
                    return i - index;
                }
                index++;
            } else {
                index = 0;
            }
        }
        return -1;
    }*/

    private List<Integer> searchSequence(String seq, String input) {
        List<Integer> indexes = new ArrayList<>();
        if (seq.length() > input.length()) {
            return indexes;
        }
        //0 1 2 3 4 5 6
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            Iterator<Integer> iter = indexes.iterator();
            while(iter.hasNext()){
                Integer index = iter.next();
                int currentIndex = i - index;
                //ignore already matched entries
                if(currentIndex >= seq.length()){
                    continue;
                }else if(currentChar != seq.charAt(currentIndex)){
                    iter.remove();
                }
            }
            if(currentChar == seq.charAt(0)){
                indexes.add(i);
            }
        }

        //clean up partial searches
        Iterator<Integer> iter = indexes.iterator();
        while(iter.hasNext()){
            Integer index = iter.next();
            if(index+seq.length() > input.length()){
                iter.remove();
            }
        }

        return indexes;
    }

    /**
     * Stock Buy Sell to Maximize Profit
     The cost of a stock on each day is given in an array, find the max profit that you can make by buying and selling in those days.
     For example, if the given array is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can earned by buying on day 0, selling on day 3.
     Again buy on day 4 and sell on day 6.
     */
    public void findMaxProfit(){
        int[] ints = new int[]{1,-4,5,6,-3,9,1,7,-2};
        System.out.println("Stock prices: " + Arrays.toString(ints));
        findMaxProfit(ints);
        ints = new int[]{100, 180, 260, 310, 40, 535, 625};
        System.out.println("Stock prices: " + Arrays.toString(ints));
        findMaxProfit(ints);
        ints = new int[]{100, 90, 80,70, 60, 50, 40, 30, 20, 10};
        System.out.println("Stock prices: " + Arrays.toString(ints));
        findMaxProfit(ints);
        ints = new int[]{10, 20, 30, 40, 50 , 60, 70, 80, 90, 100};
        System.out.println("Stock prices: " + Arrays.toString(ints));
        findMaxProfit(ints);
    }


    private void findMaxProfit(int[] price){
        int i = 0;
        while(i < price.length-1) {
            //find the low price before price change
            while ((i < price.length-1) && price[i] >= price[i+1]) {
                i++;
            }
            int lowIndex = i++;
            if (i == price.length) {
                break;
            }

            while ((i < price.length - 1) && price[i] <= price[i + 1]) {
                i++;
            }
            System.out.println("Low price: " + price[lowIndex] + " High price: " + price[i]);
        }
    }

    /**
     * Find all palindromes
     */
    public void findPalindromes(){
        findPalindromes("howareyoumadam");
        findPalindromes("anursesrunhowareyoumadam");

    }

    private void findPalindromes(String input){
        System.out.println("Sequence: " + input);
        if(input.isEmpty() || input.length()<=2){
            return;
        }

        int i = 1;
        do {
            int pre = i - 1;
            int post = i + 1;
            boolean palindrome = false;
            while(pre>=0 && (post<=input.length()-1) && input.charAt(pre)==input.charAt(post)){
                pre--;
                post++;
                palindrome = true;
            }
            if(palindrome){
                System.out.println(input.substring(pre+1, post));
            }

            i++;
        }while(i+1<input.length());
    }


    /**
     * Find value in circular array
     */
    public void findValueInCircularArray(){
        Integer[] ints = new Integer[]{6,7,8,9,10,1,2,3,4,5};
        System.out.println("Circular array: " + Arrays.toString(ints));
        System.out.println("Index of 10: " + findValueInCircularArray(ints, 10, 0, ints.length-1));
        System.out.println("Index of 15: " + findValueInCircularArray(ints, 15, 0, ints.length-1));

        ints = new Integer[]{12,16,17,20,22,27,30,34,60,3,4};
        System.out.println("Circular array: " + Arrays.toString(ints));
        System.out.println("Index of 4: " + findValueInCircularArray(ints, 4, 0, ints.length-1));
        System.out.println("Index of 25: " + findValueInCircularArray(ints, 25, 0, ints.length-1));

    }


    public int findValueInCircularArray(Integer[] ints, int value, int start, int end){
        //divide array
        //if a[0]<a[mid] or a[mid]<a[end]
        //if element is in sorted part or not - if yes - binary search
        //if not apply same rule to other part

        if(start>end){
            return -1;
        }

        int middle = (end-start)/2;
        middle = start + middle;
        if(ints[middle] == value){
            return middle;
        }else if(ints[start] < ints[middle]){
            if((ints[start] <= value) && (value <= ints[middle])) {
                //BinarySearch binarySearch = new BinarySearch();
                //return binarySearch.binarySearch(Arrays.asList(ints), start, middle, value);

                //if its in this sorted part search it
                end = middle-1;
            }else {
                start = middle+1;
            }
        }else if(ints[middle] < ints[end]){
            if((ints[middle] <= value) && (value <= ints[end])) {
                //BinarySearch binarySearch = new BinarySearch();
                //return binarySearch.binarySearch(Arrays.asList(ints), middle+1, end, value);
                start = middle + 1;
            }else{
                end=middle-1;
            }
        }
        return findValueInCircularArray(ints, value, start, end);
    }

    /**
     * Reverse list
     */
    public void reverseList(){
        LinkNode node1 = new LinkNode(1);
        LinkNode node2 = new LinkNode(2);
        LinkNode node3 = new LinkNode(3);
        LinkNode node4 = new LinkNode(4);
        LinkNode node5 = new LinkNode(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(null);

        LinkNode node = reverseList(node1);
        node.next = null;
        node = node5;
        while(node!=null){
            System.out.print(node.val + ",");
            node = node.next;
        }
    }

    public LinkNode reverseList(LinkNode head) {
        if(head.next!=null){
            LinkNode node = reverseList(head.next);
            node.next = head;
        }
        return head;

        /*
        com.questions.crackingcode.utils.LinkNode previous = head;
        com.questions.crackingcode.utils.LinkNode current = previous.next;
        previous.next = null;
        while(current!=null){
            com.questions.crackingcode.utils.LinkNode temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        return previous;*/
    }

    /**
     * Find sum in stored array
     */
    public void findSumInSorted(){
        int sum = 10;

        Integer[] ints = new Integer[]{1,2,3,4,5,6,7,8,9,10,15,20};
        findSumInSorted(ints, 10);
        findSumInSorted(ints, 30);
    }

    public void findSumInSorted(Integer[] ints, int sum){
        int i = 0, j= ints.length-1;
        System.out.println("Sorted array: " + Arrays.toString(ints));
        if(sum < ints[i]){
            System.out.println("Not found");
        }
        while(i<j) {
            while ((ints[j] + ints[i] > sum) || (ints[j]>sum)) {
                j--;
            }
            while(ints[j] + ints[i] < sum){
                i++;
            }
            //missed i=j condition
            if(ints[j] + ints[i] == sum && i!=j){
                System.out.println("[" + ints[i] + "+" + ints[j] + "]");
            }
            i++;
        }
    }

    /**
     * Copy list with random pointers
     */
    public void copyLinkList(){
        LinkNodeWithRandom link1 = new LinkNodeWithRandom(1);
        LinkNodeWithRandom link2 = new LinkNodeWithRandom(2);
        LinkNodeWithRandom link3 = new LinkNodeWithRandom(3);
        LinkNodeWithRandom link4 = new LinkNodeWithRandom(4);
        LinkNodeWithRandom link5 = new LinkNodeWithRandom(5);

        link1.setNext(link2);
        link1.setRandom(link3);

        link2.setNext(link3);
        link2.setRandom(link4);

        link3.setNext(link4);
        link3.setRandom(link5);

        link4.setNext(link5);
        link4.setRandom(link1);

        link5.setNext(null);
        link5.setRandom(link2);
        System.out.println("Original list");
        printLinkList(link1);

        copyLinkList(link1);
    }

    public void copyLinkList(LinkNodeWithRandom node){
        LinkNodeWithRandom start = node;

        //insert a copy in between all nodes
        while(node!=null){
            LinkNodeWithRandom copy = new LinkNodeWithRandom(node.val);
            LinkNodeWithRandom next = node.getNext();
            node.setNext(copy);
            copy.setNext(next);
            node = next;
        }

        node=start;
        while(node!=null){
            LinkNodeWithRandom random = node.getRandom();
            node.getNext().setRandom(random.getNext());
            node = node.getNext().getNext();
        }

        node=start;
        LinkNodeWithRandom copyStart = start.getNext();
        while(node!=null){
            LinkNodeWithRandom copy = node.getNext();
            LinkNodeWithRandom next = node.getNext()==null ? null : node.getNext().getNext();
            node.setNext(next);
            node = copy;
        }
        System.out.println("Copied list");
        printLinkList(copyStart);
    }

    public void printLinkList(LinkNodeWithRandom node){
        while(node!=null){
            System.out.print(node.val + ":" + node.getRandom().val + " ");
            node = node.getNext();
        }
    }

    /**
     * Find powerset
     */
    public void powerSet(){
        int[] array = new int[]{9,5,7,10};
        powerSet(array);
    }

    private void powerSet(int[] array){

        List<List<Integer>> list = new ArrayList<>();
        list.add(Collections.emptyList());

        for(int x=0; x<array.length; x++) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>();
                List<Integer> existing = list.get(i);
                temp.addAll(existing);
                temp.add(array[x]);

                list.add(temp);
            }
        }

        for(int i=0; i<list.size(); i++){
            System.out.print(Arrays.toString(list.get(i).toArray()));
        }

    }

}
