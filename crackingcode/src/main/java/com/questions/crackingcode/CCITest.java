package com.questions.crackingcode;

/**
 * This class string related questions
 */
public class CCITest {
    public static void main(String[] args){
        CCITest test = new CCITest();
        test.oneAwayTest();
        test.testStringCompression();
        test.zeroMatrix();
    }

    /**
     * One away test
     */
    public void oneAwayTest(){
        String s1 = "pale", s2 = "ple";
        System.out.println(s1 + ":" + s2 + ": " + oneAway(s1, s2));

        s1 = "pales";
        s2 = "pale";
        System.out.println(s1 + ":" + s2 + ": " + oneAway(s1, s2));

        s1 = "pale";
        s2 = "bale";
        System.out.println(s1 + ":" + s2 + ": " + oneAway(s1, s2));

        s1 = "pale";
        s2 = "pale";
        System.out.println(s1 + ":" + s2 + ": " + oneAway(s1, s2));

        s1 = "pale";
        s2 = "bake";
        System.out.println(s1 + ":" + s2 + ": " + oneAway(s1, s2));
      }

    private boolean oneAway(String str1, String str2){
        //validations
        int diff = Math.abs(str1.length() - str2.length());
        if(diff>1){
            System.out.println("Both string length diff is: " + diff);
            return false;
        }

        //missed same string
        if(str1.equals(str2)){
            System.out.println("Same strings");
            return false;
        }


        int i=0, j=0;
        int unequal = 0;
        while(i<str1.length() && j<str2.length()){
            if(str1.charAt(i) == str2.charAt(j)){
                i++;
                j++;
            }else{
                unequal++;
                if(unequal>1){
                    System.out.println("More than one unequal.");
                    return false;
                }
                if(str1.length()>str2.length()){
                    i++;
                }else if(str2.length()>str1.length()){
                    j++;
                }else{
                    i++;
                    j++;
                }
            }
        }

        //missed remaining
        while(i<str1.length()){
            i++;
            unequal++;
        }

        while(j<str2.length()){
            j++;
            unequal++;
        }

        if(unequal>1){
            System.out.println("More than one unequal.");
            return false;
        }
        return true;
    }

    /**
     * Compress string
     */
    private void testStringCompression(){
        System.out.println("Input: aabcccccaaa Compressed: " + stringCompression("aabcccccaaa"));
        System.out.println("Input: abcdef Compressed: " + stringCompression("abcdef"));
        System.out.println("Input: aabccd Compressed: " + stringCompression("aabccd"));
        System.out.println("Input: a Compressed: " + stringCompression("a"));
    }

    private String stringCompression(String input){
        //validations
        if(input.isEmpty() || input.length()==1){
            return input;
        }
        StringBuilder builder = new StringBuilder();
        int count = 1;
        int moreCount = 0;
        for(int i=1; i<input.length(); i++){
            if(input.charAt(i) == input.charAt(i-1)){
                count++;
                if(count>1){
                    moreCount++;
                }
            }else{
                builder.append(input.charAt(i-1)).append(count);
                count = 1;
            }

            //last character
            if(i == input.length()-1){
                builder.append(input.charAt(i)).append(count);
            }

        }
        //none of the chars are repeated more than once
        //so compression not possible;
        if(moreCount==0){
            return input;
        }
        return builder.toString();
    }

    private void zeroMatrix(){
        int[][] matrix = new int[3][4];
        for(int row=0; row< matrix.length; row++){
            for(int col=0; col< matrix[row].length; col++){
                int i = row;
                i++;
                int j = col;
                j++;
                matrix[row][col]= i+j;
            }
        }
        matrix[2][2] = 0;
        printMatrix(matrix);
        this.traverseMatrix(matrix);
        System.out.println("=============");
        printMatrix(matrix);
    }

    private void traverseMatrix(int[][] matrix){
        int i=0, j=0;
        for(int row=0; row< matrix.length; row++){
            for(int col=0; col< matrix[row].length; col++){
                if(matrix[row][col]==0){
                    i=row;
                    j=col;
                }
            }
        }
        //missed putting this outside
        //of for loop
        setZero(matrix, i, j);
    }

    private void setZero(int[][] matrix, int row, int col){
        for(int i=0; i< matrix[row].length; i++){
            matrix[row][i]=0;
        }

        for(int i=0; i< matrix.length; i++){
            matrix[i][col]=0;
        }
    }

    private void printMatrix(int[][] matrix){
        for(int row=0; row< matrix.length; row++){
            for(int col=0; col< matrix[row].length; col++){
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

}
