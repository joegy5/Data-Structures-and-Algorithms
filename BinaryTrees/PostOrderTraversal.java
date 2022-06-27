// PROBLEM DESCRIPTION: Given the root of a binary tree, return the postorder traversal of its nodes' values.

// A postorder traversal is where we look at the left child node, then the right child node, then the actual root node last. 

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
    
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) {
            return ans;
        }
    
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        
        return ans;
    }
}

// ITERATIVE SOLUTION

// INTUITION: 



