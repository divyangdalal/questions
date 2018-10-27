package com.questions.crackingcode;

import java.util.Arrays;
import java.util.List;

/**
 * Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
 * All the children sit in a line and each of them has a rating score according to his or her performance in the class.
 * Alice wants to give at least 1 candy to each child. If two children sit next to each other, then the one with the
 * higher rating must get more candies. Alice wants to minimize the total number of candies she must buy.
 *
 * For example, assume her students' ratings are [4, 6, 4, 5, 6, 2]. She gives the students candy in the following
 * minimal amounts: [1, 2, 1, 2, 3, 1]. She must buy a minimum of 10 candies.
 */
public class AliceCandy {

    public static void main(String[] strings){
        AliceCandy candy = new AliceCandy();

        Integer[] ints = new Integer[]{1,2,5,6,1,4,6,7,8,9,2,1,9};
        List<Integer> ranks  = Arrays.asList(ints);
        candy.giveCandies1(ranks);

        ints = new Integer[]{6,5,4,3,2,1,9,7,5,3,9,9};
        ranks  = Arrays.asList(ints);
        candy.giveCandies1(ranks);

        ints = new Integer[]{9,8,7,1,9,7,5,2,3,4,1};
        ranks  = Arrays.asList(ints);
        candy.giveCandies1(ranks);

        ints = new Integer[]{2,4,2,6,1,7,8,9,2,1};
        ranks  = Arrays.asList(ints);
        candy.giveCandies1(ranks);

        ints = new Integer[]{1,2,2};
        ranks  = Arrays.asList(ints);
        candy.giveCandies1(ranks);
    }


    public int giveCandies1(List<Integer> ranks){
        System.out.println("Ranks are:  " + Arrays.toString(ranks.toArray()));
        Integer[] candies = new Integer[ranks.size()];
        int totalCandies = 0;
        int candy = 1;
        for(int i=0; i<ranks.size(); i++){

            //calculate sequence
            int greaterCandy = greaterThan(i, ranks);
            if(greaterCandy !=1){
                candies[i] = greaterCandy;
            }

            //for first element
            if(i-1 < 0){
                setValue(i, candies, candy);
                totalCandies = totalCandies + candies[i];
                continue;
            }

            //if current is greater than prior, increase
            //if same, same value
            //if less, reset value
            if(ranks.get(i) > ranks.get(i-1)){
                candy++;
                setValue(i, candies, candy);
            }else if(ranks.get(i) == ranks.get(i-1)){
                candies[i] = candy;
            }else{
                candy = 1;
                setValue(i, candies, candy);
            }
            totalCandies = totalCandies + candies[i];
        }
        System.out.println("Candies are:" + Arrays.toString(candies));
        System.out.println("Total Candies: " + totalCandies);
        return totalCandies;
    }

    /**
     * If there is a value set and is greater use it
     * @param index
     * @param candies
     * @param candy
     */
    public void setValue(int index, Integer[] candies, int candy){
        if(candies[index]!=null){
            if(candies[index] < candy){
                candies[index] = candy;
            }
        }else{
            candies[index]= candy;
        }
    }


    public int greaterThan(int index, List<Integer> ranks){
        int candy=1;
        while(index+1 < ranks.size() &&
                ranks.get(index) > ranks.get(index+1)){
            candy++;
            index++;
        }
        return candy;
    }
}
