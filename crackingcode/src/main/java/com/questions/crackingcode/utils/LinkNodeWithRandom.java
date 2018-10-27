package com.questions.crackingcode.utils;

public class LinkNodeWithRandom {
    public int val;
    public LinkNodeWithRandom next;
    public LinkNodeWithRandom random;

    public LinkNodeWithRandom getRandom() {
        return random;
    }

    public void setRandom(LinkNodeWithRandom random) {
        this.random = random;
    }

    public LinkNodeWithRandom getNext() {
        return next;
    }

    public void setNext(LinkNodeWithRandom next) {
        this.next = next;
    }

    public LinkNodeWithRandom(int value) {
        this.val = value;
    }

    public LinkNodeWithRandom(LinkNodeWithRandom node) {
        this.val = node.val;
        this.next = node.next;
        this.random = node.random;
    }
}
