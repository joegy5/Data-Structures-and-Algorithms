// PROBLEM DESCRIPTION:
// Given the root of a binary tree, return the preorder traversal of its nodes' values.
// A preorder traversal is where we look at the root node, then the left child node, then the right child node 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// RECURSIVE SOLUTION:

// INTUITION:
// we have our base case such that if the current node we are looking at is already null, we just return the resulting list because we have already checked the path to that node 
// Otherwise, we can add the current node and the recurse to its left and right children nodes 

class Solution {
    List<Integer> ans = new LinkedList<Integer>();
    
    public List<Integer> preorderTraversal(TreeNode node) {
        if(node == null) {
            return ans;
        }
        ans.add(node.val);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
        
        return ans;
    }
}

// ITERATIVE SOLUTION:

// NOTE:  I did not come up with this solution entirely on my own.

//INTUITION:
// First thing we do is add the current node we are looking at because preorder traversals involve looking at the root node first before looking at the child nodes 
// Each time we find a node that has a right child node, we store that node in our stack to keep track of it so that we can come back to it later and traverse to the right node 
// We then keep iterating left until the next left node is null. 
// Once the next node is null, we look at the stack for the nodes with right children, and set our current node equal to the most recent node with a right child by popping off a node from the stack 
// We then set our current node equal to the right child node 
// If the stack is already empty, that means that we already traversed through all the nodes and we can break and return the resulting list. 

class Solution {
    
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> ans = new LinkedList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        TreeNode temp = node;
        
        while(temp != null || !stack.isEmpty()) {
            System.out.println(temp.val);
            ans.add(temp.val);
            
            if(temp.right != null) {
                stack.push(temp);
            }
        
            if(temp.left != null) {
                temp = temp.left;
            }
            else {
                if(!stack.isEmpty()) {
                    temp = stack.pop();
                    temp = temp.right;
                }
                else {
                    break;
                }
            }
        }
    
        return ans;
    }
}


