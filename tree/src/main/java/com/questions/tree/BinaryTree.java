package com.questions.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Binary tree related questions
 */
public class BinaryTree {
    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        Node node = tree.new Node(15);
        node.insert(5);
        node.insert(50);
        node.insert(1);
        node.insert(6);
        node.insert(10);
        node.insert(14);
        node.insert(22);
        node.insert(20);
        node.insert(17);
        node.insert(25);
        node.printInOrder();

        tree.convert(node);
        int sum = node.size(node);
        node.height(node);

        System.out.println("\nSearching 2: " +node.search(2));
        System.out.println("Searching 15: " + node.search(15));
        System.out.println("Searching 50: " + node.search(50));

    }

    public class Node{
        int data;
        Node left, right;

        public Node(int data){
            this.data = data;
        }

        public void insert(int value){
            if(value < data){
                if(left == null){
                    left = new Node(value);
                }else{
                    left.insert(value);
                }
            }else{
                if(right == null){
                    right = new Node(value);
                }else{
                    right.insert(value);
                }
            }
        }


        public boolean search(int value){
            if(data == value){
                return true;
            }else if(value < data){
                if(left == null){
                    return false;
                }
                return left.search(value);
            }else{
                if(right == null){
                    return false;
                }
                return right.search(value);
            }
        }

        /**
         * inorder -> left - node - right
         * preorder ->  node - left - right
         * postorder -> left - right - node
         */
        public void printInOrder(){
            if(left !=null){
                left.printInOrder();
            }
            System.out.print(data + " ");
            if(right !=null){
                right.printInOrder();
            }
        }


        public int size(Node node){
            if(node == null){
                return 0;
            }
            int left = size(node.left);
            int right = size(node.right);
            return left + right + 1;
        }

        public int height(Node node){
            if(node == null){
                return 0;
            }
            int left = height(node.left);
            int right = height(node.right);
            return 1 + Math.max(left,right);
        }

        /**
         * Root to leaf sum - assuming multiple paths can have the sum
         * @param node
         * @param sum
         * @return
         */
        public boolean rootToLeafSum(Node node, int sum, List<List<Node>> list){
            if(node == null){
                return false;
            }

            sum = sum - node.data;
            if(sum == 0  && node.left==null && node.right==null){
                List<Node> newList = new ArrayList<>();
                newList.add(node);
                list.add(newList);
                return true;
            }
            boolean left = rootToLeafSum(node.left, sum, list);
            boolean right = rootToLeafSum(node.right, sum, list);
            if(left || right){
                //add this node to all list
            }
            return (left || right);
        }
    }

    /**
     * Create BST from a preorder and inorder list
     */
    public void createBST(){
        int[] preorder = new int[]{5,2,1,3,7,6};
        int[] inorder = new int[]{1,2,3,5,6,7};
        Node root = createBST(preorder, inorder, 0, 0, 0);
    }

    /**
     * start with pre-order and find the same element in in-order. Left of in-order index
     * right of that index is left and right subtree respectively
     *
     */
    public Node createBST(int[] preorder, int[] inorder, int preIndex, int start, int end){
        if(start > end){
            return null;
        }
        int value = preorder[preIndex++];
        Node node = new Node(value);
        int inIndex = searchInorder(inorder, value);

        //Important = if start and end are same, leaf node
        if(start==end){
            return node;
        }
        node.left = createBST(preorder, inorder, preIndex, start, inIndex-1);
        node.right = createBST(preorder, inorder, preIndex, inIndex+1, end);
        return node;
    }

    private int searchInorder(int[] inorder, int value){
        for(int i=0; i<inorder.length; i++){
            if(inorder[i]==value){
                return i;
            }
        }
        return -1;
    }


    /**
     * convert binary tree into doubly link link in-place
     */
    private Node convert(Node node){
        if(node==null){
            return null;
        }

        Node left = convert(node.left);
        Node right = convert(node.right);

        node.left = left;
        node.right = right;

        if(left!=null){
            left.right = node;
            left.left = right;
        }
        if(right!=null){
            right.left = node;
            right.right = left;
        }

        return (right==null) ?  node : right;

    }

}
