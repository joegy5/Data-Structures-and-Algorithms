// *** PROBLEM DESCRIPTION *** 
//Given head, the head of a linked list, determine if the linked list has a cycle in it.

//There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

//Return true if there is a cycle in the linked list. Otherwise, return false.

 

//Example 1:


//Input: head = [3,2,0,-4], pos = 1
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

//Example 2:


//Input: head = [1,2], pos = 0
//Output: true
//Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

//Example 3:


//Input: head = [1], pos = -1
//Output: false
//Explanation: There is no cycle in the linked list.


// INTUITION:
// Use the classic tortoise and hare algorithm
// Create a fast ListNode pointer and slow ListNode pointer, with the slow pointer traversing one node at a time while the fast pointer traverses two nodes at a time 
// They are guaranteed to intersect once if there is a cycle, otherwise the fast pointer will reach the value null and we can just return false from there. 

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        
        while(fast != null && fast.next != null && slow != fast) {
            
            slow = slow.next;
            fast = fast.next.next;
            
        }
        
        return slow == fast;
    }
}
