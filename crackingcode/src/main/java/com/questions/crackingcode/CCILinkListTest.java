package com.questions.crackingcode;

import com.questions.crackingcode.utils.LinkNode;

/**
 * This class solves link list related questions
 */
public class CCILinkListTest {
    public static void main(String[] args){
        CCILinkListTest test = new CCILinkListTest();
        test.kLastElement();
        test.partitionList();
        test.addReverseLinkList();
        test.addLinkList();
        test.reverseLinkList();
    }

    /**
     * Find kth last element
     */
    public void kLastElement(){
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
        LinkNode k = kLastElement(3, node1);
        System.out.println("K from last is: " + k.val);

    }

    private LinkNode kLastElement(int kfromLast, LinkNode head){
        int count = 1;
        LinkNode start = head;
        LinkNode k = null;

        //iterate thr' the count
     /*   while(start!=null){
            start = start.next;
            count++;
            if(count==kfromLast){
                break;
            }
        }
        k = head;
        while(start!=null){
            start = start.next;
            k = k.next;
        }*/

        while(start!=null){
            //missed correct sequence for
            //eveything below
            if(k!=null){
                k = k.next;
            }
            if(count==kfromLast){
                k = head;
            }
            start = start.next;
            count++;
        }
        return k;
    }

    public void partitionList(){
        LinkNode node1 = new LinkNode(3);
        LinkNode node2 = new LinkNode(5);
        LinkNode node3 = new LinkNode(8);
        LinkNode node4 = new LinkNode(5);
        LinkNode node5 = new LinkNode(10);
        LinkNode node6 = new LinkNode(2);
        LinkNode node7 = new LinkNode(1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(null);
        System.out.print("Before partition: ");
        printList(node1);
        partitionList(5, node1);
        System.out.print("After partition: ");
        printList(node1);

    }

    /**
     * iterator thr' the list and find node with higher value
     * than pivot. Find a smaller value after that node and swap
     * @param partition
     * @param node
     */
    private void partitionList(int partition, LinkNode node){
        while(node!=null){
            if(node.val >= partition){
                LinkNode marker = node;
                while(marker!=null){
                    if(marker.val < partition){
                        swap(marker, node);
                        break;
                    }
                    marker = marker.next;
                }
            }
            node = node.next;
        }
    }

    private void swap(LinkNode to, LinkNode from){
        int temp = to.val;
        to.val = from.val;
        from.val = temp;
    }

    /**
     * Add values stored in link list in reverse
     */
    private void addReverseLinkList(){
        LinkNode l1 = createLinkList(new int[]{7,1,6});
        LinkNode l2= createLinkList(new int[]{5,9,2});
        int i = 1;
        int total = 0;
        while(l1!=null || l2!=null){
            int sum = 0;
            if(l1!=null)  sum = l1.val;
            if(l2!=null)  sum = sum + l2.val;
            //int sum = i * (l1.val + l2.val);
            sum = i * sum;
            total = total + sum;
            l1 = l1.next;
            l2 = l2.next;
            i = i * 10;
        }
        System.out.println("Sum is: " + total);
    }

    /**
     * Adds values in two link list
     */
    private void addLinkList() {
        LinkNode l1 = createLinkList(new int[]{6, 1, 7});
        LinkNode l2 = createLinkList(new int[]{2, 9, 5});
    }

    private int addLinkList(LinkNode l1, LinkNode l2, Integer i){
        if(l1.next==null && l2.next==null){
            i = 10;
            return (l1.val + l2.val);
        }
        int total = addLinkList(l1.next, l2.next, i);
        int sum = i * (l1.val + l2.val);
        i = i * 10;
        return sum;
    }

    /**
     * Reverse a link list
     */
    private void reverseLinkList(){
        LinkNode l1 = createLinkList(new int[]{1,2,3,4,5,6});
        LinkNode current = l1, next = null, nextToNext = null;

        while(current!=null){
            next = current.next;
            nextToNext = next == null ? null : next.next;
            next.next = current;
            current = next;
            next = nextToNext;
         }

    }

    /**
     * Prints a link list
     * @param node
     */
    private void printList(LinkNode node){
        while(node!=null){
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    private LinkNode createLinkList(int[] ints){
        LinkNode start= null;
        LinkNode prior = null;
        for(int i=0; i < ints.length; i++){
            LinkNode node = new LinkNode(ints[i]);
            if(prior!=null) {
                prior.next = node;
            }
            if(i==0){
                start = node;
            }
            prior = node;
        }
        return start;
    }


}
