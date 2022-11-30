package com.arpit.geeksforgeeks;

import static org.junit.jupiter.api.Assertions.*;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * Given a singly linked list: A0→A1→...→An-2→An-1, reorder it to: A0→An-1→A1→An-2→A2→An-3→...
 * For example: Given 1->2->3->4->5 its reorder is 1->5->2->4->3.
 * <p>
 * Note: It is recommended do this in-place without altering the node's values.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * LinkedList: 1->2->3
 * Output: 1 3 2
 * Explanation:
 * Here n=3, so the correct
 * order is A0→A2→A1
 * Example 2:
 * <p>
 * Input:
 * Explanation: 1->7->3->4
 * Output: 1 4 7 3
 * Explanation:
 * Here n=4, so the correct
 * order is A0→A3→A1→A2
 */
public class ProblemOfTheDay20221130 {
    Node reorderlist(Node head) {
        //Algorithm
        //1. Find the middle of the list and split the list in between
        //2. Reverse the second part of the list
        //3. Merge the list
        Node second = reverseList(splitList(head));
        Node curr = head;
        while (curr != null && second != null) {
            Node currNext = curr.next;
            Node secondNext = second.next;
            curr.next = second;
            second.next = currNext;
            curr = currNext;
            second = secondNext;
        }

        return head;
    }

    Node splitList(Node head) {
        if (head == null) {
            return head;
        }
        Node i = head, j = head.next;
        while (j != null && j.next != null) {
            i = i.next;
            j = j.next.next;
        }
        Node result = i.next;
        i.next = null;
        return result;
    }

    Node reverseList(Node head) {
        Node curr = head;
        Node prev = null, next;
        if (head != null) {
            while (curr != null) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
        }
        return prev;
    }

    @Data
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }
    
    @Test
    public void test(){
        Node n = new Node(1);
        n.next = new Node(2);
        n.next.next = new Node(3);
        Node result = reorderlist(n);
        assertEquals(result.data,1);
        assertEquals(result.next.data,3);
        assertEquals(result.next.next.data,2);
    }

    @Test
    public void test1(){
        Node n = new Node(1);
        n.next = new Node(7);
        n.next.next = new Node(3);
        n.next.next.next = new Node(4);
        
        Node result = reorderlist(n);
        assertEquals(result.data,1);
        assertEquals(result.next.data,4);
        assertEquals(result.next.next.data,7);
        assertEquals(result.next.next.next.data,3);
    }


}
