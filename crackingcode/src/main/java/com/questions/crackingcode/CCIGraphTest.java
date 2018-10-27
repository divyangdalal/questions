package com.questions.crackingcode;

import com.questions.crackingcode.utils.TreeNode;

import java.util.*;

/**
 * This class solves graph and tree related questions
 */
public class CCIGraphTest {

    public static void main(String[] args){
        CCIGraphTest test = new CCIGraphTest();
        test.createBST();
        test.listPerLevel();
        test.isBST();
        test.bstSequence();
        test.checkSubtree();
        test.pathSum();
    }

    /**
     * Create BST from array
     */
    private void createBST(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        printTree(node);
    }

    /**
     * Creates a binary search tree from array
     * @param ints
     * @param start
     * @param end
     * @return
     */
    private TreeNode createBSTFromArray(Integer[] ints, int start, int end){
        if(start > end){
            return null;
        }
        if(start==end){
            return new TreeNode(ints[start]);
        }
        int middle = (end - start)/2;
        middle = start + middle;

        TreeNode node = new TreeNode(ints[middle]);
        node.left = createBSTFromArray(ints, start, middle-1);
        node.right = createBSTFromArray(ints, middle+1, end);
        return node;
    }

    /**
     * Prints tree in order
     * @param root
     */
    void printTree(TreeNode root){
        if(root==null){
            return;
        }
        printTree(root.left);
        System.out.print(root.val + " ");
        printTree(root.right);
    }

    /**
     * Create list per level
     */
    void listPerLevel(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        List<LinkedList<TreeNode>> list = new ArrayList<>();
        listPerLevel(node, 0, list);
        System.out.print(Arrays.toString(list.toArray()));
    }

    /**
     * Pre-order traversal and create link list
     * @param node
     * @param level
     * @param list
     */
    void listPerLevel(TreeNode node, int level, List<LinkedList<TreeNode>> list){
        if(node==null) {
            return;
        }

        LinkedList<TreeNode> levelList = null;
        if(list.size() == level){
            levelList = new LinkedList<TreeNode>();
            list.add(levelList);
        }else{
            levelList = list.get(level);
        }
        levelList.add(node);

        listPerLevel(node.left, level+1, list);
        listPerLevel(node.right, level+1, list);
    }

    /**
     * Check if bst is balanced. height between two subtree is not more than 1
     * @return
     */
    boolean checkBalanced(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        int i = balancedTree(node);
        return i!=-2;

    }

    int balancedTree(TreeNode root){
        if(root==null){
            return -1;
        }
        int leftDepth = balancedTree(root.left);
        int rightDepth = balancedTree(root.right);
        if(leftDepth == -2 || rightDepth == -2){
            return -2;
        }

        if(Math.abs(leftDepth - rightDepth) > 1){
            return -2;
        }else{
            //max length + 1 for this level
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    void isBST(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        System.out.println("Is BST: " + isBST(node));
    }

    /**
     * Check if binary tree is bst
     * @param node
     * @return
     */
    boolean isBST(TreeNode node){
        if(node==null){
            return true;
        }

        boolean leftbst = isBST(node.left);
        boolean rightbst = isBST(node.right);

        if(!rightbst || !leftbst){
            return false;
        }

        boolean bst = true;
        if(node.right!=null && node.val > node.right.val){
            bst=false;
        }

        if(node.left!=null && node.left.val > node.val){
            bst=false;
        }
        return bst;
    }


    /**
     * find next successor
     */
    void bstSuccessor(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        System.out.println("Next successor: " + bstSuccessor(node, node));
    }

    int bstSuccessor(TreeNode root, TreeNode node){
        if(node==null || node.parent==null){
            return -1;
        }

        /**
         * There are two cases which needs to be covered.
         * If node have right subtree - Return the leftmost node of the right subtree.
            If right node is a leaf, return leaf. OR find the min value in right subtree.
         */

        if(node.right!=null){
            return leftMost(node.right);
        }else{
            /**
             * If node does not have right subtree (i.e. node is a leaf) - the successor is one of the ancestor.
             * Travel up using parent pointer until you see a node which is left child of it’s parent.
             * If parent pointer is not given, to get the next node you have to use a stack to keep track of parent nodes.
             * You can implement an iterator called next() that returns the successor of current node (in-order traversal).
             */

            /**When parent is given
            while(node.parent!=null && node.parent.right == node){
                node = node.parent;
            }
            return node.parent.val;*/

            /*When parent is not given*/
            return fromRoot(root, node.val);
        }

    }

    int leftMost(TreeNode node){
        int value = node.val;
        while(node.left!=null){
            node = node.left;
            value = node.val;
        }
        return value;
    }

    int fromRoot(TreeNode node, int i){
        // Start from root and search for successor down the tree
        //save the node where the flow goes towards left.
        TreeNode succ = null;
        while (node != null)
        {
            if (i < node.val){
                succ = node;
                node = node.left;
            }
            else if (i > node.val)
                node = node.right;
            else
                break;
        }
        //return succ;

        int value;
        while(node!=null){
            if(i < node.val){
                node = node.left;
            }else if(i > node.val){
                node = node.right;
            }
        }


        if(i < node.val && node.left!=null && i < node.left.val){
            node = node.left;
            return fromRoot(node, i);
        }
        if(i > node.val && node.right!=null && i > node.right.val ){
            node = node.right;
            return fromRoot(node, i);
        }
        return node.val;
    }


    /**
     * BST sequence
     */
    void bstSequence(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode node = createBSTFromArray(ints, 0, ints.length-1);
        List<List<Integer>> lists = bstSequence(node);
        for(List<Integer> list : lists){
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    List<List<Integer>> bstSequence(TreeNode root){
        if(root==null){
            return Collections.emptyList();
        }

        List<List<Integer>> left = bstSequence(root.left);
        List<List<Integer>> right = bstSequence(root.right);
        List<List<Integer>> thisList = new ArrayList<>();
        if(left.isEmpty() && right.isEmpty()){
            List<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            thisList.add(temp);
            return thisList;
        }
        if(left.isEmpty()){
            for(List<Integer> rightList : right) {
                List<Integer> temp = new ArrayList<>();
                temp.add(root.val);
                temp.addAll(rightList);
                thisList.add(temp);
            }
            return thisList;
        }
        if(right.isEmpty()){
            for(List<Integer> leftList : left) {
                List<Integer> temp = new ArrayList<>();
                temp.add(root.val);
                temp.addAll(leftList);
                thisList.add(temp);
            }
            return thisList;
        }
        for(List<Integer> leftList : left){
            for(List<Integer> rightList : right){
                List<Integer> temp = new ArrayList<>();
                temp.add(root.val);
                temp.addAll(leftList);
                temp.addAll(rightList);
                thisList.add(temp);

                List<Integer> temp1 = new ArrayList<>();
                temp1.add(root.val);
                temp1.addAll(rightList);
                temp1.addAll(leftList);
                thisList.add(temp1);
            }
        }
        return thisList;

    }


    /**
     * check subtree
     */
    void checkSubtree(){
        Integer[] ints = new Integer[]{1, 5, 7, 10, 18, 30, 31, 40, 52, 60, 72, 85};
        TreeNode t1 = createBSTFromArray(ints, 0, ints.length-1);

        ints = new Integer[]{60, 72, 85};
        TreeNode t2 = createBSTFromArray(ints, 0, ints.length-1);
        System.out.println("Subset: " + checkTree(t1, t2));

        ints = new Integer[]{60, 72, 85, 90};
        TreeNode t3 = createBSTFromArray(ints, 0, ints.length-1);
        System.out.println("Subset: " + checkTree(t1, t3));

    }

    boolean checkTree(TreeNode t1, TreeNode t2){
        if(t1==null) {
            return false;
        }
        if((t1.val == t2.val) && checkSubtree(t1, t2)){
            return true;
        }

        boolean left = checkTree(t1.left, t2);
        if(left){
            return true;
        }

        boolean right = checkTree(t1.right, t2);
        return right;
    }

    boolean checkSubtree(TreeNode t1, TreeNode t2){
        //null, single node
        if(t1==null && t2 ==null){
            return true;
        }else if(t1==null || t2 ==null){
            return false;
        }

        boolean match = (t1.val == t2.val);
        if(!match){
            return false;
        }
        boolean left = checkSubtree(t1.left, t2.left);
        boolean right = checkSubtree(t1.right, t2.right);
        return left && right;
    }

    /**
     * Path of sum
     */
    void pathSum(){
        TreeNode t3 = new TreeNode(3);
        t3.left = new TreeNode(3);
        t3.right = new TreeNode(-2);

        TreeNode t5 = new TreeNode(5);
        TreeNode t2 = new TreeNode(2);
        t2.right = new TreeNode(1);
        t5.left = t3;
        t5.right = t2;

        TreeNode tN3 = new TreeNode(-3);
        tN3.right  = new TreeNode(11);

        TreeNode t10 = new TreeNode(10);
        t10.left = t5;
        t10.right = tN3;

        List<List<TreeNode>> paths = pathSum(t10, new ArrayList<>(), 8);
        for(List<TreeNode> path: paths){
            for(TreeNode node: path){
                System.out.print(node.val + " ");
            }
            System.out.println();
        }
    }

    public class Path{
        int sum;
        List<TreeNode> path;

        Path(int sum, List<TreeNode> path){
            this.sum = sum;
            this.path = path;
        }

        Path copy(){
            List<TreeNode> copy = new ArrayList<>();
            for(TreeNode node: path){
                copy.add(node);
            }
            return new Path(this.sum, copy);
        }
    }

    List<List<TreeNode>> pathSum(TreeNode node, List<Path> paths, int sum){
        if(node==null){
            return Collections.emptyList();
        }
        List<List<TreeNode>> finalPaths = new ArrayList<>();
        List<Path> newPaths = new ArrayList<>();
        //copy all paths
        //and add this node to all paths
        for(Path path: paths){
            Path copy = path.copy();
            copy.sum = path.sum + node.val;
            copy.path.add(node);
            newPaths.add(copy);
            //if sum is equal, save this path
            if(copy.sum == sum){
                finalPaths.add(copy.path);
            }
        }

        List<TreeNode> newPath = new ArrayList<>();
        newPath.add(node);
        if(node.val == sum){
            finalPaths.add(newPath);
        }

        //add node as a new path
        newPaths.add(new Path(node.val, newPath));
        List<List<TreeNode>> left = pathSum(node.left, newPaths, sum);
        List<List<TreeNode>> right = pathSum(node.right, newPaths, sum);
        finalPaths.addAll(left);
        finalPaths.addAll(right);
        return finalPaths;
    }



}
