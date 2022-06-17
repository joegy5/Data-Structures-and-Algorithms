// *** PROBLEM DESCRIPTION ***
// Given the head of a singly linked list, reverse the list, and return the reversed list.

// EX: Input: head = [1,2,3,4,5]
//     Output: [5,4,3,2,1]

// EX: Input: head = [1,2]
//     Output: [2,1]

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        
        if(head == null) {
            return null;
        }
        if(head.next == null) {
            return head;
        }
        ListNode temp = head;
        ListNode next = head.next;
        
        while(next.next != null) {
            ListNode nextNext = next.next;
            next.next = temp; 
            temp = next;
            next = nextNext;
        }
    
        head.next = null;
        next.next = temp;
        return next;
    }
}
