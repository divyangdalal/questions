package com.questions.crackingcode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * This class solves stack related questions
 */
public class CCIStackTest {
    private Stack<String> stack1 = new Stack<>();
    private Stack<String> stack2 = new Stack<>();


    public static void main(String[] args){
        CCIStackTest test = new CCIStackTest();
        test.sort();
        test.oneArrayThreeStacks();
        test.operateShelter();
    }

    /**
     * Each stack has elements in fixed interval
     * Has wastage of space if stacks are uneven.
     */
    private void oneArrayThreeStacks(){
        int[] ints = new int[20];
        int noOfStacks = 3;
        int end1 = 0;
        int end2 = 1;
        int end3 = 2;
    }

    private int push(int[] ints, int end, int value){
        ints[end] = value;
        end = end + 3;
        return end;
    }

    private int pop(int[] ints, int end){
        if(end<3){
            ints[end] = -1;
        }else{
            ints[end] = -1;
            end = end - 3;
        }
        return end;
    }

    //=================================//
    private void singleStack(){
        Stack<Stack> stacks = new Stack<>();
    }

    private void singlePush(Stack<Stack> stacks, String value){
        //if empty put the first
        if(stacks.isEmpty()){
            Stack<String> stack = new Stack<>();
            stacks.push(stack);
        }
        Stack stack = stacks.peek();
        //if out of space add a new stack
        if(stack.size() == 10){
            Stack<String> newStack = new Stack<>();
            stacks.push(newStack);
        }
        stack = stacks.peek();
        stack.push(value);
    }

    private String singlePop(Stack<Stack> stacks){
        if(stacks.isEmpty()){
            return null;
        }
        Stack<String> stack = stacks.peek();
        String value = stack.pop();
        //if stack is empty remove it
        if(stack.isEmpty()){
            stacks.pop();
        }
        return value;
    }

    //=================================//

    private void add(String value){
        if(stack1.isEmpty()){
            for(int i=0; i<stack2.size(); i++){
                stack1.push(stack2.pop());
            }
        }
        stack1.push(value);
    }

    private String remove(){
        if(stack2.isEmpty()){
            for(int i=0; i<stack1.size(); i++){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    //=================================//

    private void sort(){
        Stack<Integer> stack = new Stack<>();
        stack.push(6);
        stack.push(1);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(10);
        stack.push(2);
        stack.push(8);
        sort(stack);

        System.out.println("Sorted stack: " + stack.toString());
    }


    /**
     * Pop the top element and compare it with all elements
     * if smaller, push it to temp stack
     * if bigger, push top element and top=value
     * This makes all bigger element on top of temp stack
     * empty temp stack back to original stack
     * Run it for all elements - O(n2)
     * @param stack
     */
    private void sort(Stack<Integer> stack){
        Stack<Integer> temp = new Stack<>();
        for(int i=stack.size(); i>0; i--){
            int top = stack.pop();
            for(int j=1; j<i; j++){
                int value = stack.pop();
                if(top < value){
                    temp.push(top);
                    top = value;
                }else{
                    temp.push(value);
                }
            }

            stack.push(top);

            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }
    }

    //=================================//
    public enum Type{
        CAT, DOG
    };

    public class Animal{
        Type type;
        String name;
        int age;

        public Animal(String name, Type type){
            this.name = name;
            this.type = type;
        }
    }

    private void operateShelter(){
        Shelter shelter =new Shelter();
        shelter.enqueue(new Animal("dog1", Type.DOG));
        shelter.enqueue(new Animal("dog2", Type.DOG));
        shelter.enqueue(new Animal("cat1", Type.CAT));
        shelter.enqueue(new Animal("dog3", Type.DOG));
        shelter.enqueue(new Animal("cat2", Type.CAT));
        shelter.enqueue(new Animal("cat3", Type.CAT));

        System.out.print("");
    }

    public class Shelter{
        LinkedList<Animal> animals = new LinkedList<>();

        public void enqueue(Animal animal){
            animals.add(animal);
        }

        public Animal dequeueAny(){
            return animals.removeFirst();
        }

        public Animal dequeueDog(){
            Iterator<Animal> iterator = animals.iterator();
            while(iterator.hasNext()){
                Animal animal = iterator.next();
                if(animal.type == Type.DOG) {
                    return animal;
                }
            }
            return null;
        }

        public Animal dequeueCat(){
            Iterator<Animal> iterator = animals.iterator();
            while(iterator.hasNext()){
                Animal animal = iterator.next();
                if(animal.type == Type.CAT) {
                    return animal;
                }
            }
            return null;
        }
    }


}
