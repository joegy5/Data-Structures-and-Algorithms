// PROBLEM DESCRIPTION: 
// Given the root of a binary tree, return the preorder traversal of its nodes' values.

// An inorder traversal is where we first visit the node left of the node we are currently looking at, then we look at the actual node we are on, then we look at the node to the right of the node we are currently looking at.


// RECURSIVE SOLUTION: 
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
class Solution {
    List<Integer> ans = new LinkedList<Integer>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) {
            return ans;
        }
        
        if(root.left != null) {
            inorderTraversal(root.left);
            
        }
        ans.add(root.val);
        inorderTraversal(root.right);
        return ans;
    }
}

// ITERATIVE SOLUTION 
// NOTE: I did not come up with this solution on my own. 




