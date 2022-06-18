// PROBLEM DESCRIPTION:
// Given the head of a singly linked list, return true if the linked list is a palindrome

// EX: Input: head = [1,2,2,1]
//     Output: true

// EX: Input: head = [1,2]
//     Output: false


// TIME COMPLEXITY: O(n)
// SPACE COMPLEXITY: O(1)

// *** NOTE *** : I did not come up with this solution on my own. The added comments are my understanding of the solution 


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
    public boolean isPalindrome(ListNode head) {
        // Take care of the corner cases where the list is less than 3 elements long
        
        if(head == null || head.next == null) {
            return true;
        }
        if(head.next.next == null) {
            return head.val == head.next.val;
        }
        
        
        // Find the middle of the linked list using two pointers, a fast and slow ListNode that traverse the linked list.
        // We want the slow pointer to end up on the second half of the linked list to reverse the second half 
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // REVERSE THE SECOND HALF OF THE LIST to be able to traverse the list with two ListNode pointers that compare values. 
        
        ListNode slowNext = slow.next;
        ListNode slowNextNext = slow.next.next;
       
        ListNode temp = slow;
        
        while(slowNext != null) {
            slowNext.next = slow;
            slow = slowNext;
            slowNext = slowNextNext;
            
            if(slowNextNext != null) {
                slowNextNext = slowNextNext.next;
            }
        }
        temp.next = null;
        fast = head;
        
        // Compare each value of each current pointer. If ANY of them are different, it means the original list does not read the same from left to right as it does from right to left, meaning that it is not a palindrome. 
        
        while(slow != null) {
            if(slow.val != fast.val) {
                
                return false;
            }
        
            fast = fast.next;
            slow = slow.next;
        }
    // Return true if all the values are equal
        return true;
    }
}
