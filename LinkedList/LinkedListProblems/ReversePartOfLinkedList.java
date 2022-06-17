// *** PROBLEM DESCRIPTION ***
// Given the head of a singly linked list and two integers left and right where left <= right, 
// reverse the nodes of the list from position left to position right, and return the reversed list.

// EX: Input: head = [1,2,3,4,5], left = 2, right = 4
//     Output: [1,4,3,2,5]

// EX: Input: head = [5], left = 1, right = 1
//     Output: [5]

// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)


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
        
        // Create a dummy node for the cases where the left position is just 1, to avoid having to make more code just to handle that specific case 
        
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
        
        // Keep track of the node immediately to the left of the position so that it can be linked to the new first node of the newly reversed segment
        // of the linked list
        
        ListNode leftPrevious = temp;
        temp = temp.next;
        ListNode next = temp.next;
        ListNode leftNode = temp;
        
        
        // Reverse the segment of the list 
        while(i < right-1) {
            ListNode nextNext = next.next;
            
            next.next = temp; 
            temp = next;
            next = nextNext;
            i++;
        }
    
        leftNode.next = next;
        leftPrevious.next = temp;
        
        // Make sure to return the original head of the linked list if it has a dummy node due to the left position being equal to 1. Otherwise, return "head" 
        // (as it has not changed from its original value)
        if(left == 1) {
            return head.next;
        }
        return head;
    }
}
