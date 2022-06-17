// *** PROBLEM DESCRIPTION ***
// Given the head of a singly linked list and two integers left and right where left <= right, 
// reverse the nodes of the list from position left to position right, and return the reversed list.

// EX: Input: head = [1,2,3,4,5], left = 2, right = 4
//     Output: [1,4,3,2,5]

// EX: Input: head = [5], left = 1, right = 1
//     Output: [5]


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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head.next == null) {
            return head;
        }
        
        int i = 1;
        
        ListNode temp = head;
        
        if(left == 1) {
            ListNode dummy = new ListNode(0, head);
            head = dummy;
            temp = head;
            i--;
        }
        
        
        else {
            while(i < left-1) {
                temp = temp.next;
                i++;
            }       
        }
        
        ListNode leftPrevious = temp;
        temp = temp.next;
        ListNode next = temp.next;
        ListNode leftNode = temp;
        
        while(i < right-1) {
            ListNode nextNext = next.next;
            
            next.next = temp; 
            temp = next;
            next = nextNext;
            i++;
        }
    
        leftNode.next = next;
        leftPrevious.next = temp;
        
        if(left == 1) {
            return head.next;
        }
        return head;
    }
}
